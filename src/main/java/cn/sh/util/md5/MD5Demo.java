package cn.sh.util.md5;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class MD5Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String password=MD5Util.getMD5("123456","");
			System.out.println(password);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
