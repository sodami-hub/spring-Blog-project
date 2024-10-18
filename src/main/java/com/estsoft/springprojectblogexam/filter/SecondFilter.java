package com.estsoft.springprojectblogexam.filter;

import jakarta.servlet.*;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class SecondFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("SecondFilter init()");
    }

    @Override
    public void destroy() {
        System.out.println("SecondFilter Destroy()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("SecondFilter doFilter Request");
    /*

        //서블릿 응답 객체를 래퍼 클래스로 포장
        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) servletResponse);

        filterChain.doFilter(servletRequest,responseWrapper);

        // 클라이언트에게 응답하기 전 모두 대문자로 변환
        String responseString = responseWrapper.getOutputStream().toString().toUpperCase();

        // 대문자로 변환한 값을 응답
        servletResponse.getOutputStream().write(responseString.getBytes(StandardCharsets.UTF_8));
        servletResponse.flushBuffer();
    */
        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("SecondFilter doFilter() response");
    }
/*

    private static class ResponseWrapper extends HttpServletResponseWrapper {

        ByteArrayOutputStream byteArrayOutputStream;
        ResponseBodyServletOutputStream responseBodyServletOutputStream;

        public ResponseWrapper(HttpServletResponse response) {
            super(response);
            byteArrayOutputStream = new ByteArrayOutputStream();
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            if (responseBodyServletOutputStream == null) {
                responseBodyServletOutputStream = new ResponseBodyServletOutputStream(byteArrayOutputStream);
            }
            return responseBodyServletOutputStream;
        }

        // response body를 String으로 변환하여 리턴
        public String getOutputString() {
            return byteArrayOutputStream.toString();
        }
    }

    */
/**
     * 서블릿의 response body에서 데이터를 가져오기 위한 클래스
     *//*

    private static class ResponseBodyServletOutputStream extends ServletOutputStream {

        private final DataOutputStream outputStream;

        public ResponseBodyServletOutputStream(OutputStream output) {
            this.outputStream = new DataOutputStream(output);
        }

        @Override
        public void write(int b) throws IOException {
            outputStream.write(b);
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener listener) {
        }
    }
*/

}
