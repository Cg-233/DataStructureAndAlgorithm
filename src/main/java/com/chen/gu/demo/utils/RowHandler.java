package com.chen.gu.demo.utils;

import com.alibaba.excel.context.AnalysisContext;

/**
 * @author enchen
 * @date 2018/7/5
 */
public interface RowHandler<T> {

    void handleData(T row, AnalysisContext analysisContext);
}
