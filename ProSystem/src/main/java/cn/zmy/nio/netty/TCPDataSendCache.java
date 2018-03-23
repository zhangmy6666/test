package cn.zmy.nio.netty;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public class TCPDataSendCache {

	/**
	 * 根据socket通道获取客户端IP
	 * 
	 * @param channel
	 *            字符串
	 * @return 客户端连接IP
	 */
	public static String getClientIP(Channel channel) {
		return channel.remoteAddress().toString().substring(1);
	}

	private static Map<String, Channel> map = new ConcurrentHashMap<String, Channel>();

	private static Map<String, Date> mapTime = new ConcurrentHashMap<String, Date>();

	public static void updateChannelTime(Channel channel) {
		String clientIP = getClientIP(channel);
		mapTime.put(clientIP, new Date());
	}

	public static void addChannel(Channel channel) {
		String clientIP = getClientIP(channel);
		// logger.debug("client is connetected:" + clientIP);

		map.put(clientIP, channel);
		mapTime.put(clientIP, new Date());
	}

	public static void removeChannel(Channel channel) {
		String clientIP = getClientIP(channel);
		if (map.containsKey(clientIP)) {
			map.remove(clientIP);
			// logger.debug("client is closed:" + clientIP);
		}
	}

	public static Channel getChannel(String key) {
		Channel channel = null;
		if (StringUtils.isNotBlank(key)) {
			channel = map.get(key);
		} else {
			Iterator<Entry<String, Channel>> iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, Channel> entry = iter.next();
				channel = entry.getValue();
				break;
			}
		}

		// 当网络连接不通时删除
		if (null != channel) {
			String clientIP = getClientIP(channel);
			Date date = mapTime.get(clientIP);
			// 时间 > 10秒,则判断失效 删除
			if (date != null && (((new Date()).getTime() - date.getTime()) > 20000)) {
				// logger.debug("connection is overtime 20s:" + clientIP);
				removeChannel(channel);
				// 重新获取
				channel = getChannel(key);
			}
		}

		return channel;
	}

	public static void sendMsg(MsgCacheDto msgCacheDto) {
		final Channel channel = getChannel(msgCacheDto.getClientIP());
		final String message = msgCacheDto.getSendMsg();
		if (null != channel) {
			while (true) {
				if (channel.isWritable()) {
					channel.writeAndFlush(msgCacheDto.getSendMsg()).addListener(new ChannelFutureListener() {

						@Override
						public void operationComplete(ChannelFuture future) throws Exception {
							if (future.isSuccess()) {
								System.out.println("send " + getClientIP(channel) + ", m:" + message + " s");
							} else {
								System.out.println("send " + getClientIP(channel) + ", m:" + message + " f");
							}
						};
					});
					break;
				} else {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						System.out.println("TCPDataSendCache.sendMsg has errors");
					}
				}
			}
		} else {
			System.out.println("socket channel is empty!");
		}
	}
}
