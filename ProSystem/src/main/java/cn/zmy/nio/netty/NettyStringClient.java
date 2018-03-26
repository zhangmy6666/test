package cn.zmy.nio.netty;

import java.net.InetSocketAddress;

import cn.zmy.nio.util.MessageBodyDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class NettyStringClient {
	public static void main(String args[]) {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			// Client服务启动器 3.x的ClientBootstrap 改为Bootstrap，且构造函数变化很大，这里用无参构造。
			Bootstrap bootstrap = new Bootstrap();
			// 指定channel类型
			bootstrap.channel(NioSocketChannel.class).group(new NioEventLoopGroup())
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8),
									new StringDecoder(CharsetUtil.UTF_8),
									// new MessageBodyDecoder(),
									new HelloClientHandler());
						}
					});
			// Start the client.
			bootstrap.connect(new InetSocketAddress("127.0.0.1", 8888));
		} finally {
			workerGroup.shutdownGracefully();
		}
	}

	private static class HelloClientHandler extends ChannelInboundHandlerAdapter {

		/**
		 * 异常处理
		 * 
		 */
		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
			ctx.close();
			cause.printStackTrace();
		}

		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			System.out.println("Hello world, I'm client.");
			// ctx.writeAndFlush("jumpjump--------");

			String msg = "Hello, I'm a client.";
			ByteBuf buf = ctx.alloc().buffer(msg.length());
			buf.writeBytes(msg.getBytes());
			ctx.writeAndFlush(buf);

		}

		/**
		 * 异常处理
		 * 
		 */
		@Override
		public void channelInactive(ChannelHandlerContext ctx) throws Exception {
			System.err.println("channelInactive!");
		}

		/**
		 * 读取数据
		 * 
		 */
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			System.out.println("client read.." + ((String) msg));
		}
	}
}
