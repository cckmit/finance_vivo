package com.vivo.finance.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/** ID 默认为采用雪花算法，ID 的字段类型为String */
@Data
/*MP开启链式拼接*/
@Accessors(chain = true)
public class BasicEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 逻辑删除状态 1:已删除，0:未删除，查询与更新时会自动带入条件delFlag=0
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;

    /**
     * 创建人 调用MP 的新增方法时，创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建时间 调用MP 的新增方法时，不需要传入创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    //@OrderBy(sort = Short.MIN_VALUE)
    private Date createTime;

    /**
     * 更新人 调用MP 的新增方法时，不需要传入变更人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

    /**
     * 更新时间 调用MP 的变更方法时，不需要传入变更时间
     */
    @TableField(fill = FieldFill.UPDATE)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 版本号 调用Mp 的新增方法时， 版本号默认为1，
     * 乐观锁插件
     * 调用MP的 变更方法时， 如果如果传入的 ver与数据库的ver 不相等则变更失败。
     * 变更时默认+1
     */
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer ver;

}
