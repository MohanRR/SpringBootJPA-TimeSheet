package com.personal.response;

public class Response {

	private Object object;
	
	private String message;
	
	private Integer statusCode;
	
	public Response(){
		
	}
	
	public Response(Object object, String message, Integer statusCode){
		this.object = object;
		this.message = message;
		this.statusCode = statusCode;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
}
