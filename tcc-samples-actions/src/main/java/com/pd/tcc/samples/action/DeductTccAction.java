package com.pd.tcc.samples.action;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author zhaozhengkang
 */
@LocalTCC
public interface DeductTccAction {

    @TwoPhaseBusinessAction(name = "DeductTccAction")
    boolean prepare(BusinessActionContext actionContext,
                    @BusinessActionContextParameter(paramName = "uid") int uid,
                    @BusinessActionContextParameter(paramName = "amount")double amount);

    boolean commit(BusinessActionContext actionContext);

    boolean rollback(BusinessActionContext actionContext);
}
