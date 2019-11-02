package com.mage.crm.dao;

import com.mage.crm.vo.CustomerDevPlan;

import java.util.List;

public interface CustomerDevPlanDao {

    List<CustomerDevPlan> queryCusDevPlans(Integer saleChanceId);

    int insertCusDevPlans(CustomerDevPlan customerDevPlan);

    int updateCusDevPlans(CustomerDevPlan customerDevPlan);

    int delete(Integer id);
}
