package com.chen.gu.demo.utils;

/**
 * 
 * @ClassName: BasePageParameter
 * @Description:基础类 前台table插件基础参数 其他查询条件可集成该类
 * @author shanfu.lth
 * @date 2018年3月19日
 *
 */
public class ResponseBasePageBean {
	/**
	 * 当前页
	 */
	private int pageNo;
	/**
	 * 默认20
	 */
	private int pageSize = 20;
	private int totalCount;
	private int limit;
	private int offset;
	private Object list;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public Object getList() {
		return list;
	}

	public void setList(Object list) {
		this.list = list;
	}
    
}