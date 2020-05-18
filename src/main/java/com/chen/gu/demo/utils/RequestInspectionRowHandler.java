package com.chen.gu.demo.utils;

import java.util.HashMap;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;

import com.chen.gu.demo.eat.pojo.InspectionItemExcelVO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import org.springframework.util.ObjectUtils;

@Data
public class RequestInspectionRowHandler implements RowHandler {

    private int headerRow = 0;

    private int endRow = Integer.MAX_VALUE;

    private int totalRow = 0;

    private List<InspectionItemExcelVO> excelDataList = Lists.newArrayList();

    private HashMap<Integer, List<String>> errMsgMap = Maps.newHashMap();

    private Boolean validateFlag = Boolean.TRUE;

    @Override
    public void handleData(Object row, AnalysisContext analysisContext) {
        InspectionItemExcelVO importBO = (InspectionItemExcelVO)row;
        //isAllEmpty排除excel中墨迹数据
        if (!ObjectUtils.isEmpty(importBO)) {
            totalRow = totalRow + 1;
            if (analysisContext.getCurrentRowNum() == headerRow) {
                //对表头进行校验
                //addErrMsg(checkSheetHeader(importBO), headerRow);
            }
            if (analysisContext.getCurrentRowNum() > headerRow && analysisContext.getCurrentRowNum() <= endRow) {
                //对表数据进行校验
                //addErrMsg(checkImportData(importBO), analysisContext.getCurrentRowNum());
                excelDataList.add(importBO);
            }
        }
    }
}