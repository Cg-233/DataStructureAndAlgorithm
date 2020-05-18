package com.chen.gu.demo.utils;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
* @ClassName: ResponsBaseBean  
* @Description: 基础类
* @author shanfu.lth  
* @date 2018年3月19日  
*  
* @param <T>
 */
public class ResponseBaseBean<T> {

	private List<String> msgList;
	
	/**
	 * 请求是否成功
	 */
	@JsonProperty("success")
	private boolean success = Boolean.TRUE;

	/**
	 * 错误码  默认是200 代表成功
	 */
	@JsonProperty("code")
	private String code = "200";

	/**
	 * 消息
	 */
	@JsonProperty("message")
	private String message = "";

	/**
	 * 数据
	 */
	@JsonProperty("data")
	private T data;


	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		this.addErrorMsgToList(message);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@SuppressWarnings("unchecked")
	public void setPageData(int pageNo,int pageSize,int totalCount,Object data) {
		ResponseBasePageBean response = new ResponseBasePageBean();
		response.setPageNo(pageNo);
		response.setPageSize(pageSize);
		response.setTotalCount(totalCount);
		response.setList(data);
		this.data = (T)response;
	}
	
	public List<String> getMsgList() {
		return msgList;
	}

	public void setMsgList(List<String> msgList) {
		this.msgList = msgList;
	}

	public void addErrorMsgToList(String errorMsg) {
		if (msgList == null) {
			msgList = new ArrayList<>();
		}
		msgList.add(errorMsg);
	}

	public static <T> ResponseBaseBean<T> of(T data) {
		ResponseBaseBean<T> responseBaseBean = new ResponseBaseBean<T>();
		responseBaseBean.data = data;
		return responseBaseBean;
	}
}