package cn.redcdn.jds.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class HttpUtil
{
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    
    public static String sendGet(String url)
    {
        String result = "";
        BufferedReader in = null;
        try
        {
            URL U = new URL(url);
            URLConnection connection = U.openConnection();
            connection.connect();
            connection.setReadTimeout(30000);
            connection.setConnectTimeout(30000);
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null)
            {
                result += line;
            }
            in.close();
        }
        catch (Exception e)
        {
            logger.error("Exception", e);
        }
        finally
        {
            if (in != null)
            {
                try 
                {
                    in.close();
                } 
                catch (IOException e) 
                {
                	logger.error("Exception", e);
                } 
            }
        }
        
        return result;
    }

    public static String sendPost(String url, String param)
    {
        BufferedReader in = null;
        try
        {
            URL httpurl = new URL(url);
            HttpURLConnection httpConn = (HttpURLConnection) httpurl.openConnection();
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setReadTimeout(30000);
            httpConn.setConnectTimeout(30000);
            httpConn.setRequestProperty("Content-type", "text/plain; charset=utf-8");
            PrintWriter out = new PrintWriter(new OutputStreamWriter(httpConn.getOutputStream(),"utf-8"));
            out.print(param);
            out.flush();
            out.close();
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = in.readLine()) != null)
            {
            	sb.append(line);
            }
            
            return sb.toString();
        }
        catch (Exception e)
        {
        	logger.error("Exception", e);
        }
        finally
        {
            if (in != null)
            {
                try 
                {
                    in.close();
                } 
                catch (IOException e) 
                {
                	logger.error("Exception", e);
                } 
            }
        }
        
        return "";
    }
    
    public static String sendPostForm(String url, String param)
    {
        BufferedReader in = null;
        try
        {
            URL httpurl = new URL(url);
            HttpURLConnection httpConn = (HttpURLConnection) httpurl.openConnection();
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setReadTimeout(30000);
            httpConn.setConnectTimeout(30000);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(httpConn.getOutputStream(),"utf-8"));
            out.print(param);
            out.flush();
            out.close();
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = in.readLine()) != null)
            {
            	sb.append(line);
            }
            
            return sb.toString();
        }
        catch (Exception e)
        {
        	logger.error("Exception", e);
        }
        finally
        {
            if (in != null)
            {
                try 
                {
                    in.close();
                } 
                catch (IOException e) 
                {
                	logger.error("Exception", e);
                } 
            }
        }
        
        return "";
    }
    
    
    /** 
     * 发送HttpPost请求 
     *  
     * @param strURL 
     *            服务地址 
     * @param params 
     *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/> 
     * @return 成功:返回json字符串<br/> 
     */  
    public static String postJson(String strURL, String params) {  

        logger.info("postJson:" + strURL + "params:" + params);
        try {  
            URL url = new URL(strURL);// 创建连接  
            HttpURLConnection connection = (HttpURLConnection) url  
                    .openConnection();  
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setUseCaches(false);  
            connection.setInstanceFollowRedirects(true);  
            connection.setRequestMethod("POST"); // 设置请求方式  
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式  
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式  
            connection.connect();  
            OutputStreamWriter out = new OutputStreamWriter(  
                    connection.getOutputStream(), "UTF-8"); // utf-8编码  
            out.append(params);  
            out.flush();  
            out.close();  
            // 读取响应  
            int length = connection.getContentLength();// 获取长度  
            InputStream is = connection.getInputStream();  
            if (length != -1) {  
                byte[] data = new byte[length];  
                byte[] temp = new byte[512];  
                int readLen = 0;  
                int destPos = 0;  
                while ((readLen = is.read(temp)) > 0) {  
                    System.arraycopy(temp, 0, data, destPos, readLen);  
                    destPos += readLen;  
                }  
                String result = new String(data, "UTF-8"); 
                return result;  
            }  
        } catch (IOException e) {  
        }  
        return ""; 
    }  
	
	/**
	 * 上传文件
	 * @param file
	 * @param fileUploadUrl
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static JSONObject postFile(JSONObject paramObj, String fileName, File file, String fileUploadUrl) {			
		JSONObject resJsonObject = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		logger.info("url:{},params：{}", fileUploadUrl, paramObj.toJSONString());
		
		try {			
			HttpPost httppost = new HttpPost(fileUploadUrl);
			FileBody bin = new FileBody(file);
			StringBody comment = new StringBody("A binary file of some kind",
					ContentType.TEXT_PLAIN);
			MultipartEntityBuilder buider = MultipartEntityBuilder.create()
					.addPart(fileName, bin).addPart("comment", comment);
			if (paramObj != null) {
				for (Entry<String, Object> entry : paramObj.entrySet()) {
					buider.addTextBody(entry.getKey(), String.valueOf(entry.getValue()));
				}
			}
			HttpEntity reqEntity = buider.build();
			httppost.setEntity(reqEntity);
			
			logger.info("executing request " + httppost.getRequestLine());
			
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					String resString = inputStream2String(resEntity
							.getContent());
					
					logger.info(resString);
					System.out.println(resString);
					
					resJsonObject = JSONObject.parseObject(resString);
				}
				EntityUtils.consume(resEntity);
			} finally {
				response.close();
			}
		} 
		catch (ClientProtocolException e) {
			logger.error("ClientProtocolException", e);
		} 
		catch (IOException e) {
			logger.error("IOException", e);
		} 
		finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error("IOException", e);
			}			
		}
		return resJsonObject;
	}
	
	/**
	 * 输入流转到String
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public static String inputStream2String(InputStream in) throws IOException {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}
	
}
