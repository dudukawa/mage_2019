package com.mage.crm.service;

import com.mage.crm.dao.ReportDao;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class ReportService {
    @Resource
    private ReportDao reportDao;


}
