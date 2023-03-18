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
public class GpCompanyHyIndustry extends Model<GpCompanyHyIndustry> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    /**
     * 行业代码
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
     * 行业分类等级
     */
    private String level;

    /**
     * 行业来源
     */
    private String source;

    /**
     * 货币
     */
    private String currency;

    @TableField("delistedDate")
    private String delistedDate;

    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
