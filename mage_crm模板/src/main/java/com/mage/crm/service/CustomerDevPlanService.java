package com.mage.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mage.crm.dao.CustomerDevPlanDao;
import com.mage.crm.dao.SaleChanceDao;
import com.mage.crm.query.CustomerDevPlanQuery;
import com.mage.crm.util.AssertUtil;
import com.mage.crm.vo.CustomerDevPlan;
import com.mage.crm.vo.SaleChance;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerDevPlanService {

    @Resource
    private CustomerDevPlanDao customerDevPlanDao;

    @Resource
    private SaleChanceDao saleChanceDao;

    public Map<String, Object> queryCusDevPlans(CustomerDevPlanQuery customerDevPlanQuery) {
        PageHelper.startPage(customerDevPlanQuery.getPage(),customerDevPlanQuery.getRows());
        List<CustomerDevPlan> customerDevPlanList = customerDevPlanDao.queryCusDevPlans(customerDevPlanQuery.getSaleChanceId());
        PageInfo<CustomerDevPlan> customerDevPlanPageInfo = new PageInfo<CustomerDevPlan>(customerDevPlanList);
        Map<String,Object> map = new HashMap<>();
        map.put("total",customerDevPlanPageInfo.getTotal());
        map.put("rows",customerDevPlanPageInfo.getList());
        return map;
    }

    public void insertCusDevPlans(CustomerDevPlan customerDevPlan) {
        //判断营销机会是否存在，以及营销机会是否已经成功或者失败，如果是则不允许修改。
        SaleChance saleChance = saleChanceDao.querySaleChancesById(customerDevPlan.getSaleChanceId());
        AssertUtil.isTrue(saleChance==null,"营销机会不存在");
        AssertUtil.isTrue(saleChance.getDevResult()==3,"营销机会已失败，不能进行操作！");
        AssertUtil.isTrue(saleChance.getDevResult()==2,"营销机会已成功，不能进行操作！");
        //设置创建时间，更新时间和是否有效
        customerDevPlan.setIsValid(1);
        customerDevPlan.setCreateDate(new Date());
        customerDevPlan.setUpdateDate(new Date());
        AssertUtil.isTrue(customerDevPlanDao.insertCusDevPlans(customerDevPlan)<1,"添加失败");
        //修改了客户开发计划表，要修改表salChance的开发结果，如果之前开发结果是0，那就要修改成1，开发中
        Integer devResult = saleChanceDao.querySaleChancesById(customerDevPlan.getSaleChanceId()).getDevResult();
        System.out.println(devResult+"==============");
        if(devResult==0){
            int count = saleChanceDao.updateDevResult(customerDevPlan.getSaleChanceId(), 1);
            AssertUtil.isTrue(count<1,"添加开发计划失败");
        }
    }

    public void updateCusDevPlans(CustomerDevPlan customerDevPlan) {
        customerDevPlanDao.updateCusDevPlans(customerDevPlan);
    }

    public void delete(Integer id) {
        AssertUtil.isTrue(customerDevPlanDao.delete(id)<1,"客户开发计划删除失败！");

    }
}
