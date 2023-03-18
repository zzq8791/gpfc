package com.example.learn.gp.vo;

import lombok.Data;

/**
 * Unknown
 *
 * @author : 你的名字
 * @date : 2022-09-13 18:36
 **/
@Data
public class GpCompanyHotMmHaVo {


    private String stockCode;
    private String mm_sh ;//	;//Number	持股数量
    private String mm_sh_cap_r;//	;//Number	持股占比
    private String  mm_sha	;//;//Number	持股金额
    private String  spc;//	;//Number	股价涨跌幅
    private String  mm_shc_v_r	;//;//Number	过去1个交易日持仓变化占成交量比例
    private String   mm_sh_cap_rc_d1;//	;//Number	过去1个交易日持仓占股本变化比例
    private String   mm_sh_cap_rc_d5	;//Number	过去5个交易日持仓占股本变化比例
    private String   mm_sh_cap_rc_d20	;//Number	过去20个交易日持仓占股本变化比例
    private String    mm_sh_cap_rc_d60	;//Number	过去60个交易日持仓占股本变化比例
    private String    mm_sh_cap_rc_d120	;//Number	过去120个交易日持仓占股本变化比例
    private String  mm_sh_nba_d1	;//Number	过去1个交易日净买入额
    private String   mm_sh_nba_d5	;//Number	过去5个交易日净买入额
    private String mm_sh_nba_d20	;//Number	过去20个交易日净买入额
    private String mm_sh_nba_d60	;//Number	过去60个交易日净买入额
    private String  mm_sh_nba_d120	;//Number	过去120个交易日净买入额
    private String last_update_date	;//Date	最近更新时间

}
