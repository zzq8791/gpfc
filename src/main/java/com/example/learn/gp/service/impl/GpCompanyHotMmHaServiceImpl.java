package com.example.learn.gp.service.impl;

import cn.hutool.core.date.DateBetween;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.learn.gp.bean.GpCompanyHotMmHa;
import com.example.learn.gp.dao.GpCompanyHotMmHaDao;
import com.example.learn.gp.service.BaseServiceImpl;
import com.example.learn.gp.service.GpCompanyHotMmHaService;
import com.example.learn.gp.vo.HlhtIntersectionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzq
 * @since 2022-09-12
 */
@Service
public class GpCompanyHotMmHaServiceImpl  extends BaseServiceImpl<GpCompanyHotMmHaDao, GpCompanyHotMmHa> implements GpCompanyHotMmHaService {

    @Autowired(required = false)
    GpCompanyHotMmHaDao gpCompanyHotMmHaDao;

    // 獲取最近七天数据，不包含节假期，统计排名前十，交集最多的数据
    @Override
    public List<HlhtIntersectionVo> getIntersection(){
        LocalDate date = LocalDate.now();
        List<LocalDate> gzr = getGZR(date, 7);
        // 获取每天前十数据，根据天数分组，获取交集
      List<HlhtIntersectionVo> vos = new ArrayList<>();
        LambdaQueryWrapper<GpCompanyHotMmHa> ew = Wrappers.lambdaQuery(GpCompanyHotMmHa.class);
        ew.in(GpCompanyHotMmHa :: getCreateTime,gzr);
        List<GpCompanyHotMmHa> gpCompanyHotMmHas = gpCompanyHotMmHaDao.selectList(ew);
        Map<LocalDateTime, List<GpCompanyHotMmHa>> dateHotMmhas = gpCompanyHotMmHas.stream().collect(Collectors.groupingBy(GpCompanyHotMmHa::getCreateTime));


        List<GpCompanyHotMmHa> has = new ArrayList<>();
        // 统计一个代码在当前时间段内出现次数最多的
        dateHotMmhas.forEach((k,v)->{
            List<GpCompanyHotMmHa> collect = v.stream().sorted(Comparator.comparing(GpCompanyHotMmHa::getMmShCapRcD1)).limit(10).collect(Collectors.toList());
            has.addAll(collect);
        });

        Map<String, Long> resMap = has.stream().collect(Collectors.groupingBy(GpCompanyHotMmHa :: getStockCode, Collectors.counting()));

        System.out.println(JSON.toJSONString(resMap));


        return vos;
    }

    @Override
    public List<GpCompanyHotMmHa> getLastFifDays(String day) {
        LambdaQueryWrapper<GpCompanyHotMmHa> ew = Wrappers.lambdaQuery(GpCompanyHotMmHa.class);
        ew.lt(GpCompanyHotMmHa::getLastUpdateDate, day);
        return gpCompanyHotMmHaDao.selectList(ew);
    }

    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println(date.getDayOfWeek().getValue());
        System.out.println(date.getDayOfWeek());
        System.out.println(date.getDayOfMonth());

        DateBetween dateBetween = DateBetween.create(new Date(), new Date());
        System.out.println(dateBetween);

        List<LocalDate> lastThreeDay = new ArrayList<>();
        System.out.println(LocalDate.now().minusDays(3));
        while (date.isAfter(LocalDate.now().minusDays(3))){
            date = date.minusDays(1);
            lastThreeDay.add(date);
        }
        // 便利日期，发现存在周末，
        System.out.println(lastThreeDay);
        LocalDate date11 = LocalDate.now();
        List<LocalDate> gzr = getGZR(date11, 7);
        System.out.println(gzr);

    }

    private static List<LocalDate> getGZR( LocalDate date,int daySize){
        List<LocalDate> lastSevenDay = new ArrayList<>();
        // 便利日期，发现日期存在周末，向前推几天
        int dayOffset  = 0;
        while (date.isAfter(LocalDate.now().minusDays(daySize))){
            date = date.minusDays(1);
            int day = date.getDayOfWeek().getValue();
            if(day == DayOfWeek.SATURDAY.getValue() || day == DayOfWeek.SUNDAY.getValue()){
                dayOffset++;
            }else {
                lastSevenDay.add(date);
            }
        }
        LocalDate tempDate = date;
        while (date.isAfter(tempDate.minusDays(dayOffset))){
            date = date.minusDays(1);
            int day = date.getDayOfWeek().getValue();
            if(day == DayOfWeek.SATURDAY.getValue() || day == DayOfWeek.SUNDAY.getValue()){
               continue;
            }
            lastSevenDay.add(date);
        }
        return  lastSevenDay;
    }

    @Autowired(required=false)
    GpCompanyHotMmHaDao dao;

    @Override
    public GpCompanyHotMmHa byId(Integer id) {
        GpCompanyHotMmHa gpCompanyHotMmHa = dao.selectById(id);
        return gpCompanyHotMmHa;
    }
}
