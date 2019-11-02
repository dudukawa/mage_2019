package com.mage.crm.controller;

import com.mage.crm.base.BaseController;
import com.mage.crm.model.MessageModel;
import com.mage.crm.query.SaleChanceQuery;
import com.mage.crm.service.SaleChanceService;
import com.mage.crm.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/sale_chance")
public class SaleChanceController extends BaseController {

    @Resource
    private SaleChanceService  saleChanceService;

    @RequestMapping("/index/{id}")
    public String index(@PathVariable("id") String id){
        if("1".equals(id)){
            return "sale_chance";
        }
        if ("2".equals(id)) {
            return "cus_dev_plan";
        }else{
            return "error";
        }
    }

    @ResponseBody
    @RequestMapping("/querySaleChancesByParams")
    public Map<String,Object> querySaleChancesByParams(SaleChanceQuery saleChanceQuery){
        return saleChanceService.querySaleChancesByParams(saleChanceQuery);
    }

    @ResponseBody
    @RequestMapping("/update")
    public MessageModel update(SaleChance saleChance){
        saleChanceService.update(saleChance);
        return createMessageModel("修改成功！");
    }

    @ResponseBody
    @RequestMapping("/insert")
    public MessageModel insert(SaleChance saleChance){
        saleChanceService.insert(saleChance);
        return createMessageModel("营销机会添加成功！");
    }

    /**
     * 删除营销计划记录的时候，应该同时删除营销具体计划表(可能多条)的字段，
     * 后期再补充
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public MessageModel delete(Integer[] id){
        saleChanceService.delete(id);
        return createMessageModel("删除成功！");
    }

    /*
     * 开发计划修改开发状态成功或失败
     */
    @ResponseBody
    @RequestMapping("/updateSaleChanceDevResultById")
    public MessageModel updateSaleChanceDevResultById(Integer saleChanceId,Integer devResult){
        saleChanceService.updateSaleChanceDevResultById(saleChanceId,devResult);
        return createMessageModel("操作成功");
    }
}
