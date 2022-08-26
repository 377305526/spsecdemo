package cn.com.reformer.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	/* 16进制字符*/
	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/* 指定算法为MD5的MessageDigest */
	private static MessageDigest messageDigest = null;
	private MessageDigest _messageDigest;

	public MD5Util() {
		try {
			_messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/* 初始化messageDigest的加密算法为MD5 */
	static {
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public static String getFileMD5String(File file) throws IOException {
		String ret = "";
		FileInputStream in = null;
		FileChannel ch = null;
		try {
			in = new FileInputStream(file);
			ch = in.getChannel();
			ByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
					file.length());
			messageDigest.update(byteBuffer);
			ret = bytesToHex(messageDigest.digest());
		} finally {
			if (in != null)
				in.close();
			if (ch != null)
				ch.close();
		}
		return ret;
	}

	public static void main (String args[]){
		System.out.println(getMD5String("admin"));

	}

	public static String getMD5String(String str) {
		return getMD5String(str.getBytes());
	}

	public String getMD5StringPri(byte[] bytes) {
		_messageDigest.update(bytes);
		return bytesToHexPri(_messageDigest.digest());
	}


	public static String getMD5String(byte[] bytes) {
		messageDigest.update(bytes);
		return bytesToHex(messageDigest.digest());
	}

	public static boolean checkPassword(String pwd, String md5) {
		return getMD5String(pwd).equalsIgnoreCase(md5);
	}

	public static boolean checkPassword(char[] pwd, String md5) {
		return checkPassword(new String(pwd), md5);

	}

	public static boolean checkFileMD5(File file, String md5)
			throws IOException {
		return getFileMD5String(file).equalsIgnoreCase(md5);

	}

	public static String bytesToHex(byte bytes[]) {
		return bytesToHex(bytes, 0, bytes.length);

	}

	public String bytesToHexPri(byte bytes[]) {
		return bytesToHex(bytes, 0, bytes.length);

	}

	public static String bytesToHex(byte bytes[], int start, int end) {
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < start + end; i++) {
			sb.append(byteToHex(bytes[i]));
		}
		return sb.toString();

	}

	public String bytesToHexPri(byte bytes[], int start, int end) {
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < start + end; i++) {
			sb.append(byteToHexPri(bytes[i]));
		}
		return sb.toString();

	}

	public static String byteToHex(byte bt) {
		return HEX_DIGITS[(bt & 0xf0) >> 4] + "" + HEX_DIGITS[bt & 0xf];

	}

	public String byteToHexPri(byte bt) {
		return HEX_DIGITS[(bt & 0xf0) >> 4] + "" + HEX_DIGITS[bt & 0xf];

	}
	public String getMD5StringPri(String s) {
		return getMD5StringPri(s.getBytes());
	}
}
