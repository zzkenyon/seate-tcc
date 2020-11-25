package com.pd.tcc.samples.persistence.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/11/25 11:29
 */
@Mapper
public interface AccountMapper {

    @Update("UPDATE t_account SET frozen = #{amount} WHERE uid = #{uid}")
    void setFrozen(@Param("uid") int uid,@Param("amount") double amount);


    @Update("update t_account set balance = balance+frozen,frozen = 0 where uid = #{uid}")
    void addCommit(int uid);

    @Update("update t_account set balance = balance-frozen,frozen = 0 where uid = #{uid}")
    void deductCommit(int uid);

    @Update("UPDATE t_account SET frozen = 0 WHERE uid = #{uid}}")
    void rollback(int uid);

}
