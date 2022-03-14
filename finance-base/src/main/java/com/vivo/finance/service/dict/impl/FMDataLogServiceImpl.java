package com.vivo.finance.service.dict.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vivo.finance.dto.query.dict.DataLogQueryDto;
import com.vivo.finance.entity.base.FMDataLog;
import com.vivo.finance.mapper.dict.FMDataLogMapper;
import com.vivo.finance.service.dict.FMDataLogService;
import com.vivo.finance.utils.wrapper.WrapperQueryHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FMDataLogServiceImpl extends ServiceImpl<FMDataLogMapper, FMDataLog> implements FMDataLogService {
	@Autowired
	private FMDataLogMapper logMapper;

	/**
	 * 添加数据日志
	 */
	@Override
	public void addDataLog(String tableName, String dataId, String dataContent ) {
		String versionNumber = "0";
		String dataVersion = logMapper.queryMaxDataVer(tableName, dataId);
		if(dataVersion != null ) {
			versionNumber = String.valueOf(Integer.parseInt(dataVersion)+1);
		}
		FMDataLog log = new FMDataLog();
		log.setDataTable(tableName);
		log.setDataId(dataId);
		log.setDataContent(dataContent);
		log.setVer(versionNumber);
		this.save(log);
	}

	public Page<FMDataLog> getPageByCondition(Page<FMDataLog> page, DataLogQueryDto queryDto){
		QueryWrapper<FMDataLog> wrapper = WrapperQueryHelp.getQueryWrapper(queryDto);
		Page<FMDataLog> result = logMapper.selectPage(page, wrapper);
		return result;
	}
}
