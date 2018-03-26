package cn.zmy.nio.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
	
	/** 
     * 对象转数组 
     * @param obj 
     * @return 
     */  
    public byte[] toByteArray (Object obj) {     
        byte[] bytes = null;     
        ByteArrayOutputStream bos = new ByteArrayOutputStream();     
        try {       
            ObjectOutputStream oos = new ObjectOutputStream(bos);        
            oos.writeObject(obj);       
            oos.flush();        
            bytes = bos.toByteArray ();     
            oos.close();        
            bos.close();       
        } catch (IOException ex) {       
            ex.printStackTrace();  
        }     
        return bytes;   
    }
}

