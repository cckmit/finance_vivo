package com.vivo.finance.mapper.check;


import com.vivo.finance.dto.query.check.DuplicateCheckVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 * @Author zyk
 * @since 2022-02-14
 */
@Mapper
public interface DuplicateCheckMapper {
	
	/* 根据表明字段名查询校验重复，主键ID*/
	Long duplicateCheckCountSql(DuplicateCheckVo duplicateCheckVo);

	/* 根据表明字段名查询校验重复*/
	Long duplicateCheckCountSqlNoDataId(DuplicateCheckVo duplicateCheckVo);
}
