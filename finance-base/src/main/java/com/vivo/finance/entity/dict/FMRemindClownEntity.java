package com.vivo.finance.entity.dict;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
*(REMIND_CLOWN)
*@auther zhangyukui
*@version 1.0 2021-11-16
*/
@Data
@TableName("scf.FM_REMIND_CLOWN")
public class FMRemindClownEntity implements Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -6454644284466487668L;

    /** 主键 */
    private Integer id;

    /** 表名 */
    private String tableName;

    /** 表说明 */
    private String tableExplain;

    /** 列名 */
    private String columsName;

    /** 字段说明 */
    private String columsExplain;


}
