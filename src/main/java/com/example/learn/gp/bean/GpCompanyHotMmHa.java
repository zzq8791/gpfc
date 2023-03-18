package com.example.learn.gp.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonProperty;
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
 * @since 2022-09-12
 */
@Data
@TableName("gp_company_hot_mm_ha")
@EqualsAndHashCode(callSuper = false)
public class GpCompanyHotMmHa extends Model<GpCompanyHotMmHa> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 代码
     */
    @TableField("stockCode")
    private String stockCode;
    /**
     * 持股数量
     */
    @JsonProperty(value="mm_sh")
    private String mmSh;

    /**
     * 持股占比
     */
    private String mmShCapR;

    /**
     * 持股金额
     */
    private String mmSha;

    /**
     * 股价涨跌幅
     */
    private String spc;

    /**
     * 过去1个交易日持仓变化占成交量比例
     */
    private String mmShcVR;

    /**
     * 过去1个交易日持仓占股本变化比例
     */
    private String mmShCapRcD1;

    /**
     * 过去5个交易日持仓占股本变化比例
     */
    private String mmShCapRcD5;

    /**
     * 过去20个交易日持仓占股本变化比例
     */
    private String mmShCapRcD20;

    /**
     * 过去60个交易日持仓占股本变化比例
     */
    private String mmShCapRcD60;

    /**
     * 过去120个交易日持仓占股本变化比例
     */
    private String mmShCapRcD120;

    /**
     * 过去1个交易日净买入额
     */
    private String mmShNbaD1;

    /**
     * 过去5个交易日净买入额
     */
    private String mmShNbaD5;

    /**
     * 过去20个交易日净买入额
     */
    private String mmShNbaD20;

    /**
     * 过去60个交易日净买入额
     */
    private String mmShNbaD60;

    /**
     * 过去120个交易日净买入额
     */
    private String mmShNbaD120;

    /**
     * 最近更新时间
     */
    private String lastUpdateDate;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
