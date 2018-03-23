/**
 * 南京青牛通讯技术有限公司
 * 日期：$Date: 2015-09-09 16:08:15 +0800 (Wed, 09 Sep 2015) $
 * 作者：$Author: zhangmy $
 * 版本：$Rev: 1193 $
 * 版权：All rights reserved.
 */
package cn.zmy.nio.netty;


/**
 * 
 * @ClassName: MsgCacheDto
 * @Description: ack返回
 */
public class MsgCacheDto
{
    /**
     * 链接IP
     */
    private String clientIP;

    /**
     * 发送消息
     */
    private String sendMsg;
    
    /**
     * constructor
     */
    public MsgCacheDto(String clientIP, String sendMsg) {
    	this.clientIP = clientIP;
    	this.sendMsg = sendMsg;
    }

	/**
	 * getter method
	 * @return Returns the clientIP.
	 */
	public String getClientIP() {
		return clientIP;
	}

	/**
	 * setter method
	 * @param clientIP The clientIP to set.
	 */
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	/**
	 * getter method
	 * @return Returns the sendMsg.
	 */
	public String getSendMsg() {
		return sendMsg;
	}

	/**
	 * setter method
	 * @param sendMsg The sendMsg to set.
	 */
	public void setSendMsg(String sendMsg) {
		this.sendMsg = sendMsg;
	}
}