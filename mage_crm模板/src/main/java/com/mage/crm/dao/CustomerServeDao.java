package com.mage.crm.dao;

import com.mage.crm.dto.ServeTypeDto;
import com.mage.crm.query.CustomerServeQuery;
import com.mage.crm.vo.CustomerServe;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CustomerServeDao {

    @Insert("insert into t_customer_serve" +
            "(serve_type,customer,service_request,update_date,create_date,is_valid,state,overview,create_people,assign_time)" +
            "values(#{serveType},#{customer},#{serviceRequest},#{updateDate},#{createDate},#{isValid},#{state},#{overview},#{createPeople},#{assignTime})")
    int insert(CustomerServe customerServe);

    List<CustomerServe> queryCustomerServesByParams(CustomerServeQuery customerServeQuery);

    int update(CustomerServe customerServe);

    List<ServeTypeDto> queryCustomerServeType();
}
