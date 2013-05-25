package com.mod.backend;

import org.apache.commons.lang.StringUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.io.IOException;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created with IntelliJ IDEA.
 * User: mod
 * Date: 13-5-19
 * Time: 下午3:24
 * To change this template use File | Settings | File Templates.
 */
public class HttpGetter {
    /**
     * http请求获取rss数据
     * @param url
     * @param lastModified
     * @return
     * @throws IOException
     */
    public HttpResult getBinary(String url,String lastModified,String eTag) throws IOException {
        System.out.println(url+"*****"+lastModified+"******"+eTag);
        HttpResponse response=null;

//        HttpHost httpHost = new HttpHost("150.36.40.27", 808);
//        HttpParams httpParams = new BasicHttpParams();
//        httpParams.setParameter(ConnRouteParams.DEFAULT_PROXY, httpHost);
//        // 创建HttpClient实例
//        HttpClient client = new DefaultHttpClient(httpParams);
        HttpClient client = new DefaultHttpClient();

        //发送参数
        HttpGet get = new HttpGet(url);
        get.addHeader("Pragma", "No-cache");
        get.addHeader("Cache-Control", "no-cache");
        if(StringUtils.isNotBlank(lastModified)){
            get.addHeader(HttpHeaders.IF_MODIFIED_SINCE, lastModified);

        }
        if(StringUtils.isNotBlank(eTag)){
            get.addHeader(HttpHeaders.IF_NONE_MATCH, eTag);
        }

        // 执行get请求，得到返回体
        try{
            response = client.execute(get);
        }catch(NoHttpResponseException e){
            System.out.println(url+":: The target server failed to respond ");
            return null;
        }

        Header lastModifiedHeader = response.getFirstHeader(HttpHeaders.LAST_MODIFIED);
        Header eTagHeader = response.getFirstHeader(HttpHeaders.ETAG);

        // 判断是否正常返回
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_MODIFIED){
                System.out.println("feed don't update since" +lastModified);
            }else{
                System.out.println("http get feed erroe ,Server returned HTTP error code::"+response.getStatusLine());
            }
            return null;
        }

        // 解析数据
        HttpEntity entity=response.getEntity();
//        System.out.println("getContentEncoding"+response.getEntity().getContentEncoding().getValue()+"*****");
        String data = EntityUtils.toString(entity);

//        System.out.println(url+":::"+data.length());
//        byte[] content = null;
//        HttpEntity resEntity = response.getEntity();
//        if (entity != null) {
//            content = EntityUtils.toByteArray(resEntity);
//        }

        //生成结果
        HttpResult result=new HttpResult();
        DateTime timeEnd=DateTime.now();
//        duration=timeEnd.timeStart
        result.setContent(data);
        String lastModifiedResponse=null;
        String eTagResponse=null;
        if(lastModifiedHeader!=null&&StringUtils.isNotBlank(lastModifiedHeader.getValue())){
            lastModifiedResponse=lastModifiedHeader.getValue();
            System.out.println("lastModifiedResponse::"+lastModifiedResponse);
            result.setLastModifiedSince(lastModifiedResponse);
        }
        if(eTagHeader!=null&&StringUtils.isNotBlank(eTagHeader.getValue())){
            eTagResponse=eTagHeader.getValue();
            result.seteTag(eTagResponse);
        }
        return result;

    }
}
