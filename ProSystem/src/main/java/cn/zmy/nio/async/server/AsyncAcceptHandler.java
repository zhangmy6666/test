package cn.zmy.nio.async.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AsyncAcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncServerHandler> {

	@Override
	public void completed(AsynchronousSocketChannel channel, AsyncServerHandler attachment) {
		//继续接受其他客户端的请求  
		AsyncServer.clientCount++;  
        System.out.println("连接的客户端数：" + AsyncServer.clientCount);  
        attachment.channel.accept(attachment, this);  
        //创建新的Buffer  
        ByteBuffer buffer = ByteBuffer.allocate(1024);  
        //异步读  第三个参数为接收消息回调的业务Handler  
        channel.read(buffer, buffer, new AsyncReadHandler(channel));
	}

	@Override
	public void failed(Throwable exc, AsyncServerHandler attachment) {
		exc.printStackTrace();
		attachment.latch.countDown();		
	}

}
