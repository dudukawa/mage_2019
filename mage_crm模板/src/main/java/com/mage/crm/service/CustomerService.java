package com.mage.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.mage.crm.dao.CustomerDao;
import com.mage.crm.query.CustomerQuery;
import com.mage.crm.util.AssertUtil;
import com.mage.crm.vo.Customer;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    @Resource
    private CustomerDao customerDao;

    public List<Customer> queryAllCustomers() {
        return customerDao.queryAllCustomers();
    }

    public Map<String, Object> queryCustomersByParams(CustomerQuery customerQuery) {
        PageHelper.startPage(customerQuery.getPage(),customerQuery.getRows());
        List<Customer> customerList = customerDao.queryCustomersByParams(customerQuery);
        PageInfo<Customer> customerPageInfo = new PageInfo<Customer>(customerList);
        Map<String,Object> map  = new HashMap<>();
        map.put("total",customerPageInfo.getTotal());
        map.put("rows",customerPageInfo.getList());
        return map;
    }

    public void insert(Customer customer) {
        checkParams(customer);
        customer.setCreateDate(new Date());
        customer.setUpdateDate(new Date());
        customer.setIsValid(1);
        customer.setState(0);
        //设置时间戳客户编号
        customer.setKhno("KH"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        customerDao.insert(customer);
    }
    public void checkParams(Customer customer){
        AssertUtil.isTrue(customer.getName()==null,"客户名称不能为空");
        AssertUtil.isTrue(customer.getPostCode()==null,"邮政编码不能为空");
        AssertUtil.isTrue(customer.getPhone()==null,"联系电话不能为空");
        AssertUtil.isTrue(customer.getFax()==null,"传真不能为空");
        AssertUtil.isTrue(customer.getWebSite()==null,"网站不能为空");
        AssertUtil.isTrue(customer.getAddress()==null,"地址不能为空");
        AssertUtil.isTrue(customer.getFr()==null,"法人不能为空");
        AssertUtil.isTrue(customer.getNyye()==null,"年营业额不能为空");
        AssertUtil.isTrue(customer.getKhyh()==null,"开户银行不能为空");
        AssertUtil.isTrue(customer.getKhzh()==null,"开户账号不能为空");
    }

    public void update(Customer customer) {
        checkParams(customer);
        AssertUtil.isTrue(customerDao.update(customer)<1,"客户信息更新失败");
    }

    public void delete(Integer[] id) {
        AssertUtil.isTrue(customerDao.delete(id)<id.length,"删除客户信息失败！");
    }

    public Customer queryCustomersById(Integer id) {
        return null;
    }
}
