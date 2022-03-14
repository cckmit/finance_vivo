package com.vivo.finance.mapper.dict;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vivo.finance.entity.base.FMDataLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FMDataLogMapper extends BaseMapper<FMDataLog>{
	/**
	 * 通过表名及数据Id获取最大版本
	 * @param tableName
	 * @param dataId
	 * @return
	 */
	String queryMaxDataVer(@Param("tableName") String tableName, @Param("dataId") String dataId);
	
}
