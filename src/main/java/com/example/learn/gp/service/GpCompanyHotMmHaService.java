package com.example.learn.gp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.learn.gp.bean.GpCompanyHotMmHa;
import com.example.learn.gp.vo.HlhtIntersectionVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzq
 * @since 2022-09-12
 */
public interface GpCompanyHotMmHaService   extends IService<GpCompanyHotMmHa> {


    GpCompanyHotMmHa byId(Integer id);

    List<HlhtIntersectionVo> getIntersection();

    List<GpCompanyHotMmHa> getLastFifDays(String day);
}
