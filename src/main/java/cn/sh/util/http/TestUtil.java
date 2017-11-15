package cn.sh.util.http;

import java.io.IOException;

public class TestUtil {
	
	public static void main(String[] args) throws IOException {
		MyHttpUtils my=new MyHttpUtils();
		String url="http://172.16.2.23:10086/minishop-api/exchange_rate_list";
		HttpRespons response=my.sendGet(url);
		System.out.println(response.getContent());
	}

}
