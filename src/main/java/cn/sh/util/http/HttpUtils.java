package cn.sh.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;



import net.sf.json.JSONObject;

public class HttpUtils {

	private final static String HTTP_ACCEPT = "accept";
	private final static String HTTP_CONNECT = "connection";
	private final static String HTTP_USERAGENT = "user-agent";
	private final static String HTTP_CONTENT_TYPE = "Content-Type";
	private final static String ENCODING = "utf-8";
	private final static String HTTP_ACCEPT_VALUE = "*/*";
	private final static String HTTP_CONNECT_VALUE = "Keep-Alive";
	private final static String HTTP_USERAGENT_VALUE = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)";

	
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param, Integer iTimeout) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url
					+ (param == null || "".equals(param) ? "" : "?" + param);
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setReadTimeout(iTimeout == null ? 10000 : iTimeout);
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			try {
				return "{\"status\":1}";
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			// LOGGER.error("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param POST_URL
	 * @param content
	 * @return
	 */
	public static String readContentPost(String url, String content, String ContentType) {
		String result = "";

		try {
			URL urlstr = new URL(url);
			URLConnection con = urlstr.openConnection();
			// 设置通用的请求属性
			con.setRequestProperty(HTTP_ACCEPT, HTTP_ACCEPT_VALUE);
			con.setRequestProperty(HTTP_CONNECT, HTTP_CONNECT_VALUE);
			con.setRequestProperty(HTTP_USERAGENT, HTTP_USERAGENT_VALUE);
			// 发送POST请求必须设置如下两行
			con.setDoOutput(true);
			con.setDoInput(true);
			if (ContentType != null && ContentType.length() > 0) {
				con.setRequestProperty(HTTP_CONTENT_TYPE, ContentType);
			}

			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream(), ENCODING);

			out.write(content);
			out.flush();
			out.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), ENCODING));
			String line = "";
			for (line = br.readLine(); line != null; line = br.readLine()) {
				result += line;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param POST_URL
	 * @param content
	 * @return
	 */
	public static String readContentPostTest(String url, String content, String ContentType) {
		String result = "";
		try {
			URL urlstr = new URL(url);
			URLConnection con = urlstr.openConnection();
			// 设置通用的请求属性
			con.setRequestProperty(HTTP_ACCEPT, HTTP_ACCEPT_VALUE);
			con.setRequestProperty(HTTP_CONNECT, HTTP_CONNECT_VALUE);
			con.setRequestProperty(HTTP_USERAGENT, HTTP_USERAGENT_VALUE);
			// 发送POST请求必须设置如下两行
			con.setDoOutput(true);
			con.setDoInput(true);
			if (ContentType != null && ContentType.length() > 0) {
				con.setRequestProperty(HTTP_CONTENT_TYPE, ContentType);
			}
			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream(), ENCODING);
			out.write(content);
			out.flush();
			out.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), ENCODING));
			String line = "";
			for (line = br.readLine(); line != null; line = br.readLine()) {
				result += line;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @author hexin
	 * @param POST_URL
	 * @param content
	 * @return
	 */
	public static String readContentPost(HttpServletRequest request, String url, String ContentType)
			throws MalformedURLException, IOException, NullPointerException {
		String content = JSONObject.fromObject(request.getAttribute("params")).toString();
		return readContentPost(url, content, ContentType);
	}

/*	@Test
	public void TEST_readContentPost() {
		String obj = "{\"param\":{\"password\":\"e6529f857d1732755fd16e60b31aba1126f04d2e30cd5c08769345b0e94134e1\",\"goods_list\":[{\"price_unit\":\"USD\",\"declared_price\":11.61,\"cny_name\":\"Nautica 诺帝卡新生婴儿女宝宝包屁衣5件套\",\"name\":\"Nautica 诺帝卡新生婴儿女宝宝包屁衣5件套\",\"count\":1,\"upc\":\"\",\"sku_id\":\"\",\"category\":null,\"brand\":null,\"spec\":\"[Size : 3-6 Months,Color : Dusty Pink]\"}],\"ware_house_id\":\"ORB\",\"value_added\":null,\"merchant_order_no\":\"\",\"oversea_express_no\":\"9374889674090028362377\",\"username\":\"liyan@55haitao.com\"}}";
		String line = readContentPost("https://api.yi-ex.com/createInventory", obj, "application/json; charset=utf-8");
		System.out.println(line);
	}*/
	

}
