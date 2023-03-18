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
 * @since 2022-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GpCompanyInfo extends Model<GpCompanyInfo> {

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
     * 地区代码
     */
    @TableField("areaCode")
    private String areaCode;

    /**
     * 市场
     */
    private String market;

    /**
     * 财报类型
     */
    @TableField("fsType")
    private String fsType;

    /**
     * 互联互通
     */
    @TableField("mutualMarkets")
    private String mutualMarkets;

    /**
     * 上市时间
     */
    @TableField("ipoDate")
    private String ipoDate;

    /**
     * 退市时间
     */
    @TableField("delistedDate")
    private String delistedDate;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
