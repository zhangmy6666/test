package cn.zmy.nio.netty;

import java.io.IOException;
import java.io.Serializable;

public class UserDto implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 7917599551954077318L;
	
	private String username;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	 
	public static void main(String[] args) throws IOException {
//		ByteArrayOutputStream boutput = new ByteArrayOutputStream();
//		DataOutputStream doutput = new DataOutputStream(boutput);
//		doutput.writeInt(1196);
//		byte[] bytes = boutput.toByteArray();
//		
////		byte[] bytes = new byte[4];
////		for (int i = 0; i < bytes.length; i++)
////		{
////			bytes[i] = (byte) ((1196 >> (i * 8)) & 0xff);
////		}
//		
//		for (byte b : bytes) 
//		{
//			System.err.println(b);
//		}
		System.err.println(Integer.toBinaryString(1193));
	}
	 
}
