package com.darcytech.training.core.node.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.domain.Specifications;
import org.springframework.transaction.annotation.Transactional;

import com.darcytech.training.core.base.BaseJpaRepository;
import com.darcytech.training.core.node.model.ActionRecord;
import com.darcytech.training.core.node.model.ActionRecord_;

import static com.darcytech.training.core.base.EnhancedJpaRepository.eq;
import static com.darcytech.training.core.base.EnhancedJpaRepository.gt;
import static com.darcytech.training.core.node.dao.ActionRecordDaos.marketingOrderResultExists;

@Transactional(readOnly = true)
public interface ActionRecordDao extends BaseJpaRepository<ActionRecord, Long> {

    default List<ActionRecord> findByJobId(int jobId, String type, String jobType, LocalDateTime fromCreateTime, Long pageEndId) {
        EntityManager em = getEntityManager();

        boolean joinByPhone = jobType.equals("phone");
        Specifications<ActionRecord> eqFromTime = Specifications.where(eq(ActionRecord_.createTime, fromCreateTime)).and(gt(ActionRecord_.id, pageEndId == null ? 0 : pageEndId));
        Specifications<ActionRecord> aa = Specifications.where(eq(ActionRecord_.jobId, jobId)).and(eqFromTime.or(gt(ActionRecord_.createTime, fromCreateTime)));
        if (type.equals("paid")) {
            aa = aa.and(marketingOrderResultExists(true, joinByPhone));
        } else if (type.equals("create")) {
            aa = aa.and(marketingOrderResultExists(false, joinByPhone)).and(Specifications.not(marketingOrderResultExists(true, joinByPhone)));
        }
        return findAll(aa);
    }

}
