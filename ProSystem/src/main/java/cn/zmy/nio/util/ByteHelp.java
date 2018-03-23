package cn.zmy.nio.util;

public class ByteHelp
{
	public static byte[] IntToBytes(int a)
	{
		byte[] bytes = new byte[4];
		for (int i = 0; i < bytes.length; i++)
		{
			bytes[i] = (byte) ((a >> (i * 8)) & 0xff);
		}
		return bytes;
	}

	public static int BytesToInt(byte[] bbb)
	{
		int a = 0;
		for (int i = 0; i < bbb.length; i++)
		{
			int temp = bbb[i];
			a += (temp & 0xff) << (i * 8);
		}
		return a;
	}
}

