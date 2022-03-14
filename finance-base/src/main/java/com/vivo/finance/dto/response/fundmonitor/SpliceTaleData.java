package com.vivo.finance.dto.response.fundmonitor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zyk
 * @Description
 * @date 2022年03月10日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpliceTaleData {

    private List<String> columnList;

    private List<List<Object>>  dataList;

    public List<List<String>> buildSimpleHead(){
        List<List<String>> result = new ArrayList<>();
        for (String column : columnList) {
            List<String> list = new ArrayList<>();
            list.add(column);
            result.add(list);
        }
        return result;
    }

}
