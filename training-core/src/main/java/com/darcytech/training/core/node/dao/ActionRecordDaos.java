package com.darcytech.training.core.node.dao;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;

import com.darcytech.training.core.node.model.ActionRecord;
import com.darcytech.training.core.node.model.ActionRecord_;
import com.darcytech.training.core.node.model.MarketingOrderResult;
import com.darcytech.training.core.node.model.MarketingOrderResult_;

import static com.darcytech.training.core.node.model.MarketingOrderResult_.*;

public abstract class ActionRecordDaos {

    static Specification<ActionRecord> marketingOrderResultExists(Boolean paid, boolean byPhone) {
        return (mainRoot, mainQuery, cb) -> {
            Subquery<Integer> subQuery = mainQuery.subquery(Integer.class);
            subQuery.select(cb.literal(1));
            Root<MarketingOrderResult> subRoot = subQuery.from(MarketingOrderResult.class);

            Predicate joinJobId = cb.equal(mainRoot.get(ActionRecord_.jobId), subRoot.get(jobId));
            Predicate joinKey = cb.equal(mainRoot.get(ActionRecord_.buyerNick), subRoot.get(buyerNick));
            if (byPhone) {
                joinKey = cb.equal(mainRoot.get(ActionRecord_.buyerMobile), subRoot.get(buyerMobile));
            }
            if (paid == null) {
                subQuery.where(joinJobId, joinKey);
            } else {
                subQuery.where(joinJobId, joinKey, cb.equal(subRoot.get(MarketingOrderResult_.paid), paid));
            }
            return cb.exists(subQuery);
        };
    }

}
