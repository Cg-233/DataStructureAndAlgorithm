package com.chen.gu.demo.utils;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author wb-gc
 * @version 1.0
 * @date 2019/11/12 3:46 下午
 */
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("现在是");
    }
}
