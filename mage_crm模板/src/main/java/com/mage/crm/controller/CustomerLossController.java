package com.mage.crm.controller;

import com.mage.crm.base.BaseController;
import com.mage.crm.query.CustomerLossQuery;
import com.mage.crm.service.CustomerLossService;
import com.mage.crm.vo.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("customer_loss")
public class CustomerLossController extends BaseController {
    @Resource
    private CustomerLossService customerLossService;

    //视图管理器
    @RequestMapping("index")
    public String index(){
        return "customer_loss";
    }


    @RequestMapping("queryCustomerLossesByParams")
    @ResponseBody
    public Map<String,Object> queryCustomerLossesByParams(CustomerLossQuery customerLossQuery){
        return customerLossService.queryCustomerLossesByParams(customerLossQuery);
    }

}
