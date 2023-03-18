package com.example.learn.gp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.learn.gp.bean.GpCompanyNonFinancial;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzq
 * @since 2023-02-22
 */
public interface GpCompanyNonFinancialService extends IService<GpCompanyNonFinancial> {

    void saveNonFinancial(List<GpCompanyNonFinancial> gpCompanyNonFinancials );

    List<GpCompanyNonFinancial> getLastFifDays(String day);

}
