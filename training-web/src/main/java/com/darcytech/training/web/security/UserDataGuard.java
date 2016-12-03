package com.darcytech.training.web.security;

import javax.annotation.PostConstruct;

import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.hibernate.event.spi.PreDeleteEvent;
import org.hibernate.event.spi.PreDeleteEventListener;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.darcytech.training.core.base.UserModel;

import static org.hibernate.event.spi.EventType.*;

/**
 * 不直接在 UserData 这个接口上直接加上 @EventListeners 的原因是，core 里面的代码不光是给 web 用的。
 * <p/>
 * 另外一种做法是在 application.properties 中加入以下属性
 * <p/>
 * spring.jpa.properties.hibernate.ejb.event.post-load
 * spring.jpa.properties.hibernate.ejb.event.pre-update
 * spring.jpa.properties.hibernate.ejb.event.pre-insert
 * spring.jpa.properties.hibernate.ejb.event.pre-delete
 * <p/>
 * 也可以参考 hibernate 3.x 时代的 org.hibernate.secure.JACCSecurityListener, HibernateIntegrator.
 */
@Configuration
public class UserDataGuard implements PostLoadEventListener, PreUpdateEventListener,
        PreInsertEventListener, PreDeleteEventListener {

    @Autowired
    private HibernateEntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void register() {
        EventListenerRegistry listenerRegistry = ((SessionFactoryImpl) entityManagerFactory
                .getSessionFactory()).getServiceRegistry().getService(EventListenerRegistry.class);
        listenerRegistry.appendListeners(POST_LOAD, this);
        listenerRegistry.appendListeners(PRE_UPDATE, this);
        listenerRegistry.appendListeners(PRE_INSERT, this);
        listenerRegistry.appendListeners(PRE_DELETE, this);
    }

    private void guard(Object entity) {
        if (entity instanceof UserModel) {
            UserModel data = (UserModel) entity;
            SecurityContext context = SecurityContextHolder.getContext();
            long currentUserId = getCurrentUserId(context);
            if (currentUserId != data.getUserId()) {
                throw new SecurityException(currentUserId + " try to read/update " + data.getUserId() + "'s data");
            }
        }
    }

    private long getCurrentUserId(SecurityContext context) {
        return 0;
    }

    @Override
    public void onPostLoad(PostLoadEvent event) {
        guard(event.getEntity());
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        guard(event.getEntity());
        return false;
    }

    @Override
    public boolean onPreDelete(PreDeleteEvent event) {
        guard(event.getEntity());
        return false;
    }

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        guard(event.getEntity());
        return false;
    }

}
