package com.example.learn.core.filter;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

public class XHttpInputMessage implements HttpInputMessage {
	
	private HttpHeaders headers;
	  
	private InputStream body;
	 
    public XHttpInputMessage(HttpInputMessage httpInputMessage, String encode) throws IOException {
        this.headers = httpInputMessage.getHeaders();
        this.body = encode(httpInputMessage.getBody(), encode);
    }
 
    private InputStream encode(InputStream body, String encode) {
        //省略对流进行编码的操作
        return body;
    }
 
    @Override
    public InputStream getBody() {
        return body;
    }
 
    @Override
    public HttpHeaders getHeaders() {
        return this.headers;
    }


}
