package com.mage.crm.dao;

import com.mage.crm.vo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    User queryUserByName(String userName);

    User queryUserById(String id);

    //修改密码
    int updatePwd(@Param("userPwd") String userPwd,@Param("id") String id);

    //查询所有用户分配者(角色名称为客户经理)
    @Select("SELECT\n" +
            "\tu.true_name 'trueName'\n" +
            "FROM\n" +
            "\tt_user u\n" +
            "\tLEFT JOIN t_user_role ur ON u.id = ur.user_id\n" +
            "\tLEFT JOIN t_role r ON ur.role_id = r.id \n" +
            "WHERE\n" +
            "\tr.role_name = '客户经理' \n" +
            "\tAND u.is_valid = 1 \n" +
            "\tAND r.is_valid = 1 \n" +
            "\tAND ur.is_valid =1")
    List<User> queryAllCustomerManager();
}
