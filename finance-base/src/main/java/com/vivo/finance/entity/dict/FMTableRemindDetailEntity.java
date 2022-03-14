package com.vivo.finance.entity.dict;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.vivo.finance.entity.BasicEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
*(TABLE_REMIND_DETAIL)
*@auther zhangyukui
*@version 1.0 2021-11-16
*/
@Data
@TableName("scf.FM_TABLE_REMIND_DETAIL")
public class FMTableRemindDetailEntity {

    /** 版本号 */
    private static final long serialVersionUID = 2932037721791583131L;

    /** 主键ID */
    @TableId
    private String id;

    /** 列ID */
    private String clownId;

    /** 角色名 */
    private String roleName;

    /** 表名 */
    private String tableName;

    /** 是否通知 */
    private String isRemind;

    /** 是否编辑 */
    private String isEdit;

    /** 是否查看 */
    private String isViews;

    /** 最后更新人 */
    private String updateUser;

    /** 最后时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

}
