package com.vivo.finance.service.dict;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vivo.finance.dto.query.dict.DataLogQueryDto;
import com.vivo.finance.entity.base.FMDataLog;

public interface FMDataLogService extends IService<FMDataLog> {

	/**
	 * 添加数据日志
	 * @param tableName
	 * @param dataId
	 * @param dataContent
	 */
	void addDataLog(String tableName, String dataId, String dataContent);


	/**
	 * @Description: 分页查询
	 * @author zyk
	 * @date 2022/2/17 15:56
	 * @param page
	 * @param queryDto
	 */
	Page<FMDataLog> getPageByCondition(Page<FMDataLog> page, DataLogQueryDto queryDto);


}
