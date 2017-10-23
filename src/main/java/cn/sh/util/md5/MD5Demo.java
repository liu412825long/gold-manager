package cn.sh.util.md5;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class MD5Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String password=MD5Util.getEncryptedPwd("123456");
			System.out.println(password);
			boolean result=MD5Util.validPassword("1234562", "88F97B73BC9ECB95D53EA1E2A53D1C5F04581BDBF0C23E26B8A432CA");
			System.out.println(result);
			System.out.println(password.length());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
