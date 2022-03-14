package com.vivo.finance.utils.excel;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.CollectionUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zyk
 * @Description
 * @date 2022年03月11日
 */
@Slf4j
public class EasyExcelUtils {


    private static Sheet initSheet;

    static {
        initSheet = new Sheet(1, 0);
        initSheet.setSheetName("sheet");
        // 设置自适应宽度
        initSheet.setAutoWidth(Boolean.TRUE);
    }

    /**
     * 导入
     * 少于1000行数据 默认样式
     * @param filePath 文件绝对路径
     * @return
     */
    public static List<Object> readLessThan1000Row(String filePath) {
        return readLessThan1000RowBySheet(filePath, null);
    }

    /**
     * 导入
     * 少于1000行数据，带样式的
     * @param filePath 文件绝对路径
     * @param sheet
     * @return
     */
    public static List<Object> readLessThan1000RowBySheet(String filePath, Sheet sheet) {
        return null;
    }

    /**
     * 导入
     * 大于1000行数据 默认样式
     * @param filePath
     * @return
     */
    public static List<Object> readMoreThan1000Row(String filePath) {
        return readMoreThan1000RowBySheet(filePath, null);
    }

    /**
     * 导入
     * 大于1000行数据 自定义样式
     * @param filePath
     * @param sheet
     * @return
     */
    public static List<Object> readMoreThan1000RowBySheet(String filePath, Sheet sheet) {
        return null;
    }

    /**
     * 导出单个sheet
     * @param response
     * @param dataList
     * @param sheet
     * @param fileName
     * @throws UnsupportedEncodingException
     */
    public static void writeExcelOneSheet(HttpServletResponse response, List dataList, Sheet sheet, String fileName) {
        if (CollectionUtils.isEmpty(dataList)) {
            throw new RuntimeException("导出的表格数据为空!");
        }
        // 如果sheet为空，则使用默认的
        if (null == sheet) {
            sheet = initSheet;
        }
        try {
            response.reset();
            response.setCharacterEncoding("gb2312");
            response.setHeader("Content-Disposition", "attachment; filename=" +fileName + new SimpleDateFormat("yyyyMMdd").format(new Date())+".xlsx");
            ServletOutputStream out = response.getOutputStream();
            // 写法1
            String fileNam2 = "E:\\project\\"+fileName+".xlsx";
            // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
            EasyExcel.write(out,dataList.get(0).getClass()).sheet(fileName).doWrite(dataList);
            //以下方式，数据量较大时， 会抛出异常
//            EasyExcel.write(out,dataList.get(0).getClass()).registerWriteHandler(new WriteHandler()).sheet(fileName).doWrite(dataList);
            out.flush();
        } catch (IOException e) {
            log.error("导出失败，失败原因：{}", e);
        }
    }

    public static void writeTableByDataListAndHead(HttpServletResponse response, List<List<Object>> dataList, List<List<String>> head, String fileName) {
        try {
            response.reset();
            response.setCharacterEncoding("gb2312");
            response.setHeader("Content-Disposition", "attachment; filename=" +fileName + new SimpleDateFormat("yyyyMMdd").format(new Date())+".xlsx");
            ServletOutputStream out = response.getOutputStream();
            EasyExcel.write(out).head(head).sheet(fileName).doWrite(dataList);
            out.flush();
        } catch (IOException e) {
            log.error("导出失败，失败原因：{}", e);
        }
    }

    /**
     * @Author lockie
     * @Description 导出excel 支持一张表导出多个sheet
     * @Param OutputStream 输出流
     * Map<String, List>  sheetName和每个sheet的数据
     * ExcelTypeEnum 要导出的excel的类型 有ExcelTypeEnum.xls 和有ExcelTypeEnum.xlsx
     * @Date 上午12:16 2019/1/31
     */
    public static void writeExcelMutilSheet(HttpServletResponse response, Map<String, List<? extends BaseRowModel>> dataList, String fileName) throws UnsupportedEncodingException {
        if (CollectionUtils.isEmpty(dataList)) {
            throw new RuntimeException("导出的表格数据为空!");
        }
        try {
            response.reset();
            response.setCharacterEncoding("gb2312");
            response.setHeader("Content-Disposition", "attachment; filename=" +fileName + new SimpleDateFormat("yyyyMMdd").format(new Date()));
            ServletOutputStream out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
            // 设置多个sheet
            setMutilSheet(dataList, writer);
            writer.finish();
            out.flush();
        } catch (IOException e) {
            log.error("导出异常", e);
        }
    }


    /**
     * @Author lockie
     * @Description //setSheet数据
     * @Date 上午12:39 2019/1/31
     */
    private static void setMutilSheet(Map<String, List<? extends BaseRowModel>> dataList, ExcelWriter writer) {
        int sheetNum = 1;
        for (Map.Entry<String, List<? extends BaseRowModel>> stringListEntry : dataList.entrySet()) {
            Sheet sheet = new Sheet(sheetNum, 0, stringListEntry.getValue().get(0).getClass());
            sheet.setSheetName(stringListEntry.getKey());
            writer.write(stringListEntry.getValue(), sheet);
            sheetNum++;
        }
    }


    /**
     * 导出监听
     */
    @Data
    public static class ExcelListener extends AnalysisEventListener {
        private List<Object> datas = new ArrayList<>();

        /**
         * 逐行解析
         * @param object 当前行的数据
         * @param analysisContext
         */
        @Override
        public void invoke(Object object, AnalysisContext analysisContext) {
            if (object != null) {
                datas.add(object);
            }
        }


        /**
         * 解析完所有数据后会调用该方法
         * @param analysisContext
         */
        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        }
    }


}
