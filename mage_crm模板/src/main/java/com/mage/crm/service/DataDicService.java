package com.mage.crm.service;

import com.github.pagehelper.StringUtil;
import com.mage.crm.dao.DataDicDao;
import com.mage.crm.util.AssertUtil;
import com.mage.crm.vo.DataDic;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DataDicService {
    @Resource
    private DataDicDao dataDicDao;

    public List<DataDic> queryDataDicValueByDataDicName(String dataDicName) {
        AssertUtil.isTrue(StringUtil.isEmpty(dataDicName),"参数异常");
        return dataDicDao.queryDataDicValueByDataDicName(dataDicName);
    }
}
