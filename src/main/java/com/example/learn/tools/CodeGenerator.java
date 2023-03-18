package com.example.learn.tools;

/**
 * @Author: Ian
 * @Date: 2019/4/4
 */
public class CodeGenerator {

    public static void main(String[] args) {
        GeneratorServiceEntity generatorServiceEntity = new GeneratorServiceEntity();
        //需要生成代码的表名
//        String[] tables = "classify_config,department,disease,problem_chain_classify,problem_chain_info,standard_base,standard_chati,standard_diagnoise,standard_handle,standard_report,standard_wenzhen,vmr_case_summary,vmr_chati_detail,vmr_diagnosis_basis_level,vmr_diagnosis_basis_record,vmr_diagnosis_detail,vmr_disease_case,vmr_disease_template,vmr_handle_detail,vmr_report_detail,vmr_scene,vmr_scene_classify,vmr_templet_detail,vmr_wenzhen_detail".split(",");
        
//        String[] tables = "disease_sample,disease,question_classify,question_classify_diagnosis_treatment,sample_scene,scene,disease_type,standand_question,disease_question_relationship,sample_question_score,diagnosis,diagnosis_support_type,handle_disease,standand_sub_question,standand_type".split(",");
//        String[] tables = "dept_dict,department_dict,vmr_disease_case_scene".split(",");
//        String[] tables = "kb_time_table,kb_time_table_student".split(",");
        	String[] tables = "gp_company_industries".split(",");
            generatorServiceEntity.generateCode(tables);
    }
}
