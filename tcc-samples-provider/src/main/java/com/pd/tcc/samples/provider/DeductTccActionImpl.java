package com.pd.tcc.samples.provider;

import com.pd.tcc.samples.action.DeductTccAction;
import com.pd.tcc.samples.persistence.mapper.AccountMapper;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * The type Tcc action one.
 *
 * @author zhangsen
 */
@Service(version = "1.0",timeout = 300000)
@Slf4j
@Component
public class DeductTccActionImpl implements DeductTccAction {
    @Resource
    private AccountMapper accountMapper;

    @Override
    public boolean prepare(BusinessActionContext actionContext, int uid, double amount) {
        log.info("preparing...xid = " + RootContext.getXID());
        accountMapper.setFrozen(uid,amount);
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        int uid = (int)actionContext.getActionContext("uid");
        log.info("committing...xid = " + xid);
        accountMapper.deductCommit(uid);
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        int uid = (int)actionContext.getActionContext("uid");
        log.info("rollbacking...xid = " + xid);
        accountMapper.rollback(uid);
        return true;
    }
}
