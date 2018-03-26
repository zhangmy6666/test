package cn.zmy.nio.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class NettyObjectServer {
	public static void main(String[] args) {
		// EventLoop 代替原来的 ChannelFactory
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			// server端采用简洁的连写方式，client端才用分段普通写法。
			serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					// .handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new ObjectEncoder(),
									new ObjectDecoder(ClassResolvers.cacheDisabled(null)), new HelloServerHandler());
						}
					}).option(ChannelOption.SO_KEEPALIVE, true);
			// Start the server.
			ChannelFuture f = serverBootstrap.bind(8000).sync();
			// Wait until the server socket is closed.
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	private static class HelloServerHandler extends ChannelInboundHandlerAdapter {
		/**
		 * 异常处理
		 * 
		 */
		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
			ctx.close();
			System.out.println("Server Exception had occured!");
			cause.printStackTrace();
		}

		/**
		 * 当绑定到服务端的时候触发，打印"Hello world, I'm client."
		 * 
		 */
		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			System.out.println("Hello world, I'm server.");
			// String msg = "Hello, I'm client.";
			// ByteBuf buf = ctx.alloc().buffer(msg.length());
			// buf.writeBytes(msg.getBytes());
			// ctx.writeAndFlush(buf);
			// List<UserDto> userList = new ArrayList<UserDto>();
			// for (int i = 0; i < 1000; i++)
			// {
			// UserDto dto = new UserDto();
			// dto.setUsername("zz");
			// userList.add(dto);
			// }
			// ctx.writeAndFlush(userList);
			ctx.writeAndFlush("服务器狗狗狗--------");
		}

		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			// System.out.println( ((ByteBuf)
			// msg).toString(CharsetUtil.US_ASCII));
			// @SuppressWarnings("unchecked")
			// List<UserDto> userList = (List<UserDto>) msg;
			// System.out.println(userList.size());
			// System.out.println(toByteArray(userList).length);
			System.out.println("server read.." + ((String) msg));
		}
	}
}
