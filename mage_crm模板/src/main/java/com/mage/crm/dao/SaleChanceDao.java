package com.mage.crm.dao;

import com.mage.crm.query.SaleChanceQuery;
import com.mage.crm.vo.SaleChance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleChanceDao {
    List<SaleChance> querySaleChancesByParams(SaleChanceQuery saleChanceQuery);

    int update(SaleChance saleChance);

    int insert(SaleChance saleChance);

    int delete(Integer[] id);

    SaleChance querySaleChancesById(Integer id);

    int updateDevResult(@Param("saleChanceId") Integer saleChanceId, @Param("devResult") Integer devResult);
}
