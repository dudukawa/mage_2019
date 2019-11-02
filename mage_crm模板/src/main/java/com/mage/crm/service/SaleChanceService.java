package com.mage.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mage.crm.dao.SaleChanceDao;
import com.mage.crm.query.SaleChanceQuery;
import com.mage.crm.util.AssertUtil;
import com.mage.crm.vo.SaleChance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleChanceService {
    @Resource
    private SaleChanceDao saleChanceDao;

    public Map<String, Object> querySaleChancesByParams(SaleChanceQuery saleChanceQuery) {
        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(saleChanceQuery.getPage(),saleChanceQuery.getRows());
        List<SaleChance> saleChances = saleChanceDao.querySaleChancesByParams(saleChanceQuery);
        PageInfo<SaleChance> saleChancePageInfo = new PageInfo<>(saleChances);
        List<SaleChance> list = saleChancePageInfo.getList();
        map.put("total",saleChancePageInfo.getTotal());
        map.put("rows",list);
        return map;
    }

    //更新
    public void update(SaleChance saleChance) {
        checkParams(saleChance.getCgjl(),saleChance.getLinkMan(),saleChance.getLinkPhone());
        if(StringUtils.isBlank(saleChance.getAssignMan())){
            saleChance.setState(0);
        }else{
            saleChance.setState(1);
        }
        saleChance.setUpdateDate(new Date());
        AssertUtil.isTrue(saleChanceDao.update(saleChance)<1,"更新失败");
    }
    //非空校验
    public void checkParams(String cgjl,String linkMan,String linkPhone){
        AssertUtil.isTrue(StringUtils.isBlank(cgjl),"成功几率不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(linkMan),"联系人不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(linkPhone),"联系电话不能为空");
    }

    public void insert(SaleChance saleChance) {
        //如果有其中一个为空，则会抛出异常
        checkParams(saleChance.getCgjl(),saleChance.getLinkMan(),saleChance.getLinkPhone());
        //设置有效
        saleChance.setIsValid(1);
        saleChance.setCreateDate(new Date());
        saleChance.setUpdateDate(new Date());
        saleChance.setDevResult(0);//未开发状态
        //设置状态，有无分配，分配人为空
        if(StringUtils.isBlank(saleChance.getAssignMan())){
            saleChance.setState(0);
        }else {
            saleChance.setState(1);
        }
        AssertUtil.isTrue(saleChanceDao.insert(saleChance)<1,"添加失败");
    }

    public void delete(Integer[] id) {
        AssertUtil.isTrue(saleChanceDao.delete(id)<id.length,"删除失败");
    }

    public SaleChance querySaleChancesById(Integer id) {
        return saleChanceDao.querySaleChancesById(id);
    }

    public void updateSaleChanceDevResultById(Integer saleChanceId, Integer devResult) {
        AssertUtil.isTrue(saleChanceDao.updateDevResult(saleChanceId,devResult)<1,"执行失败");
    }
}
