package cn.sh.util.md5;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	private static final String HEX_NUMS_STR = "0123456789ABCDEF";

	/**
	 * 将16进制字符串转换成字节数组
	 * 
	 * @param hex
	 * @return
	 */
	public static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] hexChars = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (HEX_NUMS_STR.indexOf(hexChars[pos]) << 4 | HEX_NUMS_STR
					.indexOf(hexChars[pos + 1]));
		}
		return result;
	}

	/**
	 * 将指定byte数组转换成16进制字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byteToHexString(byte[] b) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			hexString.append(hex.toUpperCase());
		}
		return hexString.toString();
	}
	
	/**
	 * 获得加密后的16进制形式口令,口令长度为32位
	 * 
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String getMD5(String password,String saltString)
		throws NoSuchAlgorithmException,
		UnsupportedEncodingException {
		StringBuffer buffer=new StringBuffer();
		buffer.append(password);
		if(saltString!=null||"".equals(saltString)) {
			buffer.append(saltString);
		}
		//声明消息摘要对象
		MessageDigest md = null;
		//创建消息摘要
		md = MessageDigest.getInstance("MD5");
		//将口令的数据传给消息摘要对象
		md.update((buffer.toString()).getBytes("UTF-8"));
		//获得消息摘要的字节数组
		byte[] digest = md.digest();
		//将字节数组格式加密后的口令转化为16进制字符串格式的口令
		return byteToHexString(digest);
	}
	
	/**
	 * 获得加密后的16进制形式口令,口令长度为32位
	 * 
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String getMD5(String password)
		throws NoSuchAlgorithmException,
		UnsupportedEncodingException {
		return getMD5(password,null);
	}
	
}
