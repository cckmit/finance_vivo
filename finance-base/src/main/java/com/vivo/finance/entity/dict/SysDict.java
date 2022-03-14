package com.vivo.finance.entity.dict;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vivo.finance.entity.BasicEntity;
import lombok.Data;


@Data
@TableName(value = "scf.FM_DICT")
public class SysDict extends BasicEntity {

    private static final long serialVersionUID = 1L;
    /**
     * id采用雪花算法
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    
    /**
     * [预留字段，暂时无用]
     * 字典类型,0 string,1 number类型,2 boolean
     * 前端js对stirng类型和number类型 boolean 类型敏感，需要区分。在select 标签匹配的时候会用到
     * 默认为string类型
     */
    private Integer type;
    
    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 描述
     */
    private String description;


}
