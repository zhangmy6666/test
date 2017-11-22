package cn.zmy.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
            httpConn.setRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
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
}
