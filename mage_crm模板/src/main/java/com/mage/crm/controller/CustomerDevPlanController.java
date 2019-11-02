package com.mage.crm.controller;

import com.mage.crm.base.BaseController;
import com.mage.crm.model.MessageModel;
import com.mage.crm.query.CustomerDevPlanQuery;
import com.mage.crm.service.CustomerDevPlanService;
import com.mage.crm.service.SaleChanceService;
import com.mage.crm.vo.CustomerDevPlan;
import com.mage.crm.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 客户开发计划控制器
 */
@Controller
@RequestMapping("/cus_dev_plan")
public class CustomerDevPlanController extends BaseController {
    @Resource
    private CustomerDevPlanService customerDevPlanService;
    @Resource
    private SaleChanceService saleChanceService;

    @RequestMapping("/index")
    public String index(Integer id, Model model){
        SaleChance saleChance = saleChanceService.querySaleChancesById(id);
        model.addAttribute("saleChance",saleChance);
        return "cus_dev_plan_detail";
    }

    /**
     * 展示edatagrid计划列表
     */
    @RequestMapping("/queryCusDevPlans")
    @ResponseBody
    public Map<String,Object> queryCusDevPlans(CustomerDevPlanQuery customerDevPlanQuery){
        return customerDevPlanService.queryCusDevPlans(customerDevPlanQuery);
    }


    /*
    * 插入计划
    */
    @RequestMapping("/insertCusDevPlans")
    @ResponseBody
    public void insertCusDevPlans(CustomerDevPlan customerDevPlan){
        customerDevPlanService.insertCusDevPlans(customerDevPlan);
    }

    /**
     * 修改计划
     */
    @RequestMapping("/updateCusDevPlans")
    @ResponseBody
    public void updateCusDevPlans(CustomerDevPlan customerDevPlan){
        customerDevPlanService.updateCusDevPlans(customerDevPlan);
    }

    /**
     * 删除计划
     */
    @RequestMapping("/delete")
    @ResponseBody
    public MessageModel delete(Integer id){
        customerDevPlanService.delete(id);
        return createMessageModel("删除成功");
    }
}
