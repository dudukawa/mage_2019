package com.mage.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.mage.crm.base.CrmConstant;
import com.mage.crm.dao.CustomerServeDao;
import com.mage.crm.dto.ServeTypeDto;
import com.mage.crm.query.CustomerServeQuery;
import com.mage.crm.util.AssertUtil;
import com.mage.crm.util.CookieUtil;
import com.mage.crm.vo.CustomerServe;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServeService {
    @Resource
    private CustomerServeDao customerServeDao;

    public void insert(CustomerServe customerServe) {
        checkParams(customerServe);
        customerServe.setCreateDate(new Date());
        customerServe.setUpdateDate(new Date());
        customerServe.setIsValid(1);
        customerServe.setState("1");
        System.out.println(customerServe.getCreatePeople());
        AssertUtil.isTrue(customerServeDao.insert(customerServe)<1,"添加失败");
    }
    public void checkParams(CustomerServe customerServe){
        AssertUtil.isTrue(customerServe.getServeType()==null,"服务类型不能为空");
        AssertUtil.isTrue(customerServe.getCustomer()==null,"客户不能为空");
        AssertUtil.isTrue(customerServe.getServiceRequest()==null,"服务请求不能为空");
    }

    public Map<String,Object> queryCustomerServesByParams(CustomerServeQuery customerServeQuery) {
        PageHelper.startPage(customerServeQuery.getPage(),customerServeQuery.getRows());
        List<CustomerServe> customerServeList = customerServeDao.queryCustomerServesByParams(customerServeQuery);
        PageInfo<CustomerServe> customerServePageInfo = new PageInfo<CustomerServe>(customerServeList);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("total",customerServePageInfo.getTotal());
        map.put("rows",customerServePageInfo.getList());
        return map;
    }

    public void update(CustomerServe customerServe,HttpServletRequest request) {
        checkParams(customerServe);
        customerServe.setUpdateDate(new Date());
        if(customerServe.getState().equals("2")){
            //当state为2，说明已创建，但是还未分配
            //设置分配人
            customerServe.setAssigner(CookieUtil.getCookieValue(request,"trueName"));
            //设置服务处理时间
            customerServe.setAssignTime(new Date());
        }else if(customerServe.getState().equals("3")) {
            //未处理
            AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServiceProce()), "处理内容不能为空");
            //设置处理时间
            customerServe.setServiceProceTime(new Date());
        }else if(customerServe.getState().equals("4")){
            //未反馈
            AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServiceProceResult()),"处理结果不能为空");
            AssertUtil.isTrue(StringUtils.isBlank(customerServe.getMyd()),"处理满意度不能为空");
            customerServe.setState("5");
        }
        AssertUtil.isTrue(customerServeDao.update(customerServe)<1, CrmConstant.OPS_FAILED_MSG);
    }

    public Map<String, Object> queryCustomerServeType() {
        List<ServeTypeDto> serveTypeDtoList = customerServeDao.queryCustomerServeType();
        Map<String,Object> map = new HashMap<>();
        for (ServeTypeDto serveTypeDto: serveTypeDtoList) {
            System.out.println(serveTypeDto.getName()+"dfbfd"+serveTypeDto.getValue());
        }
        map.put("code",300);
        String[] types;
        ServeTypeDto[] datas;
        if(serveTypeDtoList!=null && serveTypeDtoList.size()>0){
            types = new String[serveTypeDtoList.size()];
            datas = new ServeTypeDto[serveTypeDtoList.size()];
            for(int i=0;i<serveTypeDtoList.size();i++){
                types[i] = serveTypeDtoList.get(i).getName();
                datas[i]=serveTypeDtoList.get(i);
            }
            map.put("code",200);
            map.put("types",types);
            map.put("datas",datas);
        }
        return map;
    }
}
