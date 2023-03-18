package com.example.learn.gp.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zzq
 * @since 2023-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GpCompanyNonFinancial extends Model<GpCompanyNonFinancial> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 公司code
     */
    @TableField("stockCode")
    private String stockCode;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 股票收益率
     */
    private String ey;

    /**
     * 股息率
     */
    private String dyr;

    /**
     * 股价
     */
    private String sp;

    /**
     * 股价涨跌幅
     */
    private String spc;

    /**
     * 成交量
     */
    private String tv;

    /**
     * 成交金额
     */
    private String ta;

    /**
     * 换手率
     */
    @TableField("to_r")
    private String to_r;

    /**
     * 前复权
     */
    @TableField("fc_rights")
    private String fc_rights;

    /**
     * 后复权
     */
    @TableField("bc_rights")
    private String bc_rights;

    /**
     * 股东人数
     */
    private String shn;

    /**
     * 	市值
     */
    private String mc;

    /**
     * 流通市值
     */
    private String cmc;

    /**
     * 自由流通市值
     */
    private String ecmc;

    /**
     * 人均自由流通市值
     */
    @TableField("ecmc_psh")
    private String ecmc_psh;

    /**
     * 融资余额
     */
    private String fb;

    /**
     * 融券余额
     */
    private String sb;

    /**
     * 陆股通持仓股数
     */
    @TableField("ha_sh")
    private String ha_sh;

    /**
     * 陆股通持仓金额
     */
    @TableField("ha_shm")
    private String ha_shm;

    /**
     * 	最近更新时间
     */
    private String date;

    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
