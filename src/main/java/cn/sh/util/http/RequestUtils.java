package cn.sh.util.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestUtils {
	public static Logger logger = LoggerFactory.getLogger(RequestUtils.class);
	public static String httpRequestInvoke(String requestUrl,String requestInfo) {
		return httpRequestInvoke(requestUrl,requestInfo,null);
	}
	public static String httpRequestInvoke(String requestUrl,String requestInfo,Properties properties) {
		Properties connectionProperties = getConnectionProperties(properties);
		String charsetName = (String)connectionProperties.get("charsetName"); 
		URL url = null;
		DataOutputStream dataOutputStream = null;
		InputStream inStrm = null;
		HttpURLConnection httpUrlConnection = null;
		try {
			url = new URL(requestUrl);
			httpUrlConnection = setURLConnectionProperties(url,connectionProperties);
			dataOutputStream = getDataOutputStream(httpUrlConnection);
			dataOutputStream.write(requestInfo.getBytes(charsetName));
			dataOutputStream.flush();
			dataOutputStream.close();
			inStrm = httpUrlConnection.getInputStream();
			String info = readInfoFromServer(inStrm);
			info=unicodeToString(info);
			return info;
		} catch (MalformedURLException e) {
			logger.error("URL连接异常", e);
		} catch (ProtocolException e) {
			logger.error("协议异常", e);
		} catch (IOException e) {
			logger.error("IO异常", e);
		} finally {
			try {
				if (dataOutputStream != null) {
					dataOutputStream.close();
				}
			} catch (IOException e) {
				logger.error("IO异常", e);
			}
			try {
				if (inStrm != null)
				{
					inStrm.close();
				}
			} catch (IOException e) {
				logger.error("IO异常", e);
			}
			try {
				if (httpUrlConnection != null) {
					httpUrlConnection.disconnect();
				}
			} catch (Exception e) {
				logger.error("关闭连接异常", e);
			}
		}
		  return null;
	}
	private static HttpURLConnection setURLConnectionProperties(URL url,Properties connectionProperties)
			throws IOException, ProtocolException{
		    boolean doOutput =  ((Boolean)connectionProperties.get("doOutput")).booleanValue();
		    boolean doInput =  ((Boolean)connectionProperties.get("doInput")).booleanValue();
		    boolean useCaches =  ((Boolean)connectionProperties.get("useCaches")).booleanValue();
		    String contentType = (String)connectionProperties.get("contentType"); 
		    String requestMethod = (String)connectionProperties.get("requestMethod"); 
			HttpURLConnection httpUrlConnection = null;
			URLConnection rulConnection = url.openConnection();
			httpUrlConnection = (HttpURLConnection) rulConnection;
			httpUrlConnection.setDoOutput(doOutput);
			httpUrlConnection.setDoInput(doInput);
			httpUrlConnection.setUseCaches(useCaches);
			httpUrlConnection.setRequestProperty("Content-type", contentType);
			httpUrlConnection.setRequestMethod(requestMethod);
			try {
				httpUrlConnection.connect();
				httpUrlConnection.setConnectTimeout(1);
				httpUrlConnection.setReadTimeout(1);
			} catch (ConnectException e) {
				logger.error("连接异常", e);
			}
		    return httpUrlConnection;
	}

	private static DataOutputStream  getDataOutputStream (
			HttpURLConnection httpUrlConnection) throws IOException
	{
			return new DataOutputStream(httpUrlConnection.getOutputStream());
	}
	
	
	private static String readInfoFromServer(InputStream inStrm) throws IOException {
			String response = "";
			try
			{
				BufferedReader br =new BufferedReader(new InputStreamReader(inStrm));
				String readLine =null;
				while((readLine =br.readLine()) !=null){
					response = response + readLine;
				}
				br.close();
			} catch (Exception e) {
				logger.error("服务器异常",e);
			}
			return response;
	}
	private static Properties getConnectionProperties(Properties properties) {
		Properties connectionProperties = getDefaultConnectionProperties();
		if(properties == null) {
			return connectionProperties;
		}
		connectionProperties.putAll(properties);
		return connectionProperties;
	}
	private static Properties getDefaultConnectionProperties() {
		Properties connectionProperties = new Properties();
		connectionProperties.put("charsetName", "UTF-8");
     	connectionProperties.put("doOutput", true);
     	connectionProperties.put("doInput", true);
     	connectionProperties.put("useCaches", false);
     	connectionProperties.put("contentType", "application/json;charset=UTF-8");
     	connectionProperties.put("requestMethod", "POST");
     	return connectionProperties;
	}
	/**
	 * Unicode转中文
	 * @param str
	 * @return
	 */
	public static String decodeUnicode(String theString) {
		if(theString != null) {
			if(theString.startsWith("\\u")) {
			    char aChar;
			    int len = theString.length();
			    StringBuffer outBuffer = new StringBuffer(len);
			    for (int x = 0; x < len;) {
				     aChar = theString.charAt(x++);
				     if (aChar == '\\') {
					      aChar = theString.charAt(x++);
					      if (aChar == 'u') {
					          int value = 0;
					          for (int i = 0; i < 4; i++) {
					        	  	aChar = theString.charAt(x++);
							        switch (aChar) {
								        case '0':
								        case '1':
								        case '2':
								        case '3':
								        case '4':
								        case '5':
								        case '6':      
								        case '7':      
								        case '8':      
								        case '9':      
								        	value = (value << 4) + aChar - '0';      
								        	break;      
								        case 'a':      
								        case 'b':      
								        case 'c':      
								        case 'd':      
								        case 'e':      
								        case 'f':      
								        	value = (value << 4) + 10 + aChar - 'a';      
								        	break;      
								        case 'A':      
								        case 'B':      
								        case 'C':      
								        case 'D':      
								        case 'E':      
								        case 'F':      
								        	value = (value << 4) + 10 + aChar - 'A';      
								        	break;      
								        default:      
								        	throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");      
							        }
					          } 
					          outBuffer.append((char) value);
					        } else {   
						         if (aChar == 't') {      
						        	 aChar = '\t';
						         } else if (aChar == 'r') {
						        	 aChar = '\r';
						         } else if (aChar == 'n') {    
						        	 aChar = '\n';
						         } else if (aChar == 'f') {     
						        	 aChar = '\f';
						         }
						         outBuffer.append(aChar);      
					        }
				       } else {
				    	   outBuffer.append(aChar);      
				       }
			    }
			    return outBuffer.toString();
			} else {
				return theString;
			}
		} else {
			return null;
		}
	} 
	
	/** 把十六进制Unicode编码字符串转换为中文字符串
	* @param str
	* @return
	*/
	public static String unicodeToString(String str) {
		if(str != null){
			Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
			Matcher matcher = pattern.matcher(str);
			char ch;
			while (matcher.find()) {
				ch = (char) Integer.parseInt(matcher.group(2), 16);
				str = str.replace(matcher.group(1), ch + "");
			}
			return str;
		}
		return null;
	}
}

