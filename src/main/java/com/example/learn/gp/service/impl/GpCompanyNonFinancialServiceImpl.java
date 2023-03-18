package com.example.learn.gp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.learn.gp.bean.GpCompanyNonFinancial;
import com.example.learn.gp.dao.GpCompanyNonFinancialDao;
import com.example.learn.gp.service.BaseServiceImpl;
import com.example.learn.gp.service.GpCompanyNonFinancialService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzq
 * @since 2023-02-22
 */
@Service
public class GpCompanyNonFinancialServiceImpl extends BaseServiceImpl<GpCompanyNonFinancialDao, GpCompanyNonFinancial> implements GpCompanyNonFinancialService {

    @Override
    public void saveNonFinancial(List<GpCompanyNonFinancial> gpCompanyNonFinancials) {
        this.saveBatch(gpCompanyNonFinancials);
    }

    @Override
    public List<GpCompanyNonFinancial> getLastFifDays(String day) {
        LambdaQueryWrapper<GpCompanyNonFinancial> ew = Wrappers.lambdaQuery(GpCompanyNonFinancial.class);
        ew.lt(GpCompanyNonFinancial::getDate, day);
        return this.list(ew);
    }
}
