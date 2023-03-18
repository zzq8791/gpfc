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
 * @since 2023-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GpCompanyHyIndustryHotMmHa extends Model<GpCompanyHyIndustryHotMmHa> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 公司名称
     */
    @TableField("stockCode")
    private String stockCode;

    /**
     * 持股金额
     */
    @TableField("mm_sha")
    private String mm_sha;

    /**
     * 股价涨跌幅
     */
    private String spc;

    /**
     * 持股金额占市值比例
     */
    @TableField("mm_sha_mc_r")
    private String mm_sha_mc_r;

    /**
     * 过去1个交易日持股金额占市值变化
     */
    @TableField("mm_sha_mc_rc_d1")
    private String mm_sha_mc_rc_d1;

    /**
     * 	过去5个交易日持股金额占市值变化
     */
    @TableField("mm_sha_mc_rc_d5")
    private String mm_sha_mc_rc_d5;

    /**
     * 过去60个交易日持股金额占市值变化
     */
    @TableField("mm_sha_mc_rc_d20")
    private String mm_sha_mc_rc_d20;

    /**
     * 过去60个交易日持仓占股本变化比例
     */
    @TableField("mm_sha_mc_rc_d60")
    private String mm_sha_mc_rc_d60;

    /**
     * 	过去120个交易日持股金额占市值变化
     */
    @TableField("mm_sha_mc_rc_d120")
    private String mm_sha_mc_rc_d120;

    /**
     * 过去1个交易日净买入金额
     */
    @TableField("mm_sh_nba_d1")
    private String mm_sh_nba_d1;

    /**
     * 过去5个交易日净买入金额
     */
    @TableField("mm_sh_nba_d5")
    private String mm_sh_nba_d5;

    /**
     * 过去20个交易日净买入金额
     */
    @TableField("mm_sh_nba_d20")
    private String mm_sh_nba_d20;

    /**
     * 过去60个交易日净买入金额
     */
    @TableField("mm_sh_nba_d60")
    private String mm_sh_nba_d60;

    /**
     * 过去120个交易日净买入金额
     */
    @TableField("mm_sh_nba_d120")
    private String mm_sh_nba_d120;

    /**
     * 	最近更新时间
     */
    @TableField("last_update_date")
    private String last_update_date;

    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
