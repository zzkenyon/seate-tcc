package com.pd.tcc.samples.action;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.util.List;

/**
 * The interface Tcc action two.
 * @author zhaozhengkang
 */
@LocalTCC
public interface AddTccAction {

    @TwoPhaseBusinessAction(name = "AddTccAction")
    boolean prepare(BusinessActionContext actionContext,
                    @BusinessActionContextParameter(paramName = "uid") int uid,
                    @BusinessActionContextParameter(paramName = "amount")double amount);

    boolean commit(BusinessActionContext actionContext);

    boolean rollback(BusinessActionContext actionContext);

}
