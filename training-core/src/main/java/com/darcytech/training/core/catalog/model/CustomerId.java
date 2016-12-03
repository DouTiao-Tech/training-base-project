package com.darcytech.training.core.catalog.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import com.google.common.base.Objects;

@Embeddable
public class CustomerId implements Serializable {

    private Long userId;

    private String nick;

    @SuppressWarnings("unused")
    private CustomerId() {
        // used by hibernate or jackson
    }

    public CustomerId(Long userId, String nick) {
        this.userId = userId;
        this.nick = nick;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CustomerId))
            return false;
        CustomerId that = (CustomerId) o;
        return Objects.equal(getUserId(), that.getUserId()) &&
                Objects.equal(getNick(), that.getNick());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getUserId(), getNick());
    }

}
