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
 * @since 2023-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GpCompanyApiCnIndex extends Model<GpCompanyApiCnIndex> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 	指数名称
     */
    private String name;

    /**
     * 指数代码
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
     * 指数来源
中证 :csi
国证 :cni
恒生 :hsi
美指 :usi
理杏仁 :lxri
     */
    private String source;

    /**
     * 类型
规模 :size
行业 :sector
风格 :style
主题 :thematic
策略 :strategy
     */
    private String series;

    /**
     * 发布时间
     */
    @TableField("launchDate")
    private String launchDate;

    /**
     * 调样频率
年度 :annually
半年 :semi-annually
季度 :quarterly
月度 :monthly
不定期 :irregularly
定期 :aperiodically
     */
    @TableField("rebalancingFrequency")
    private String rebalancingFrequency;

    /**
     * 计算方式
派氏加权 :paasche
分级靠档加权 :grading_weighted
股息率加权 :dividend_grading
等权 :equal
自由流通市值加权 :free_float_cap
     */
    @TableField("caculationMethod")
    private String caculationMethod;

    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
