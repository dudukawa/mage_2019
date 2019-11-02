package com.mage.crm.dao;

import com.mage.crm.query.CustomerLossQuery;
import com.mage.crm.vo.CustomerLoss;

import java.util.List;

public interface CustomerLossDao {

    public List<CustomerLoss> queryCustomerLossesByParams(CustomerLossQuery customerLossQuery);

}
