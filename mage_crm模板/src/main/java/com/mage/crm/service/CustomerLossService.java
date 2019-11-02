package com.mage.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mage.crm.dao.CustomerLossDao;
import com.mage.crm.query.CustomerLossQuery;
import com.mage.crm.vo.CustomerLoss;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerLossService {
    @Resource
    private CustomerLossDao customerLossDao;

    public Map<String, Object> queryCustomerLossesByParams(CustomerLossQuery customerLossQuery) {
        PageHelper.startPage(customerLossQuery.getPage(),customerLossQuery.getRows());
        List<CustomerLoss> customerLossList = customerLossDao.queryCustomerLossesByParams(customerLossQuery);
        PageInfo<CustomerLoss> customerLossPageInfo = new PageInfo<>(customerLossList);
        Map<String,Object> map = new HashMap<>();
        map.put("total",customerLossPageInfo.getTotal());
        map.put("rows",customerLossPageInfo.getList());
        return map;
    }
}
