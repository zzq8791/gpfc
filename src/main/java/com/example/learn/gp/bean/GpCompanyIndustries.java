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
 * @since 2023-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GpCompanyIndustries extends Model<GpCompanyIndustries> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 地区代码
     */
    @TableField("areaCode")
    private String areaCode;



    @TableField("gp_stockCode_name")
    private String gp_stockCode_name;

    @TableField("gp_stockCode")
    private String gp_stockCode;

    /**
     * 行业代码
     */
    @TableField("stockCode")
    private String stockCode;

    /**
     * 行业来源恒生 :hsi 国证 :cni 申万 :sw 申万2021版 :sw_2021
     */
    private String source;

    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
