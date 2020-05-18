package com.chen.gu.demo.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wb-xtt308801 on 2019/4/26.
 */
@Data
public class RequestInstanceExcelListener extends AnalysisEventListener {

    private static final Logger logger = LoggerFactory.getLogger(RequestInstanceExcelListener.class);

    private RowHandler rowHandler;

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        if (analysisContext.getCurrentSheet().getSheetNo() != 1) {
            return;
        }
        rowHandler.handleData(o, analysisContext);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
