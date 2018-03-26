package cn.zmy.nio.netty;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class NettyObjectClient {
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
							ch.pipeline().addLast(new ObjectEncoder(),
									new ObjectDecoder(ClassResolvers.cacheDisabled(null)), new HelloClientHandler());
						}
					});
			// Start the client.
			bootstrap.connect(new InetSocketAddress("127.0.0.1", 8000));
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

		/**
		 * 当绑定到服务端的时候触发，打印"Hello world, I'm client."
		 *
		 * @alia OneCoder
		 * @author lihzh
		 * @date 2013年11月16日 上午12:50:47
		 */
		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			System.out.println("Hello world, I'm client.");
			// List<UserDto> userList = new ArrayList<UserDto>();
			// for (int i = 0 ; i < 100; i++) {
			// UserDto dto = new UserDto();
			// dto.setUsername("mmmm");
			// userList.add(dto);
			// }
			// ctx.writeAndFlush(userList);
			ctx.writeAndFlush("客户端猫猫猫--------");
		}

		/**
		 * 读取数据
		 * 
		 */
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			// List<UserDto> userList = (List<UserDto>) msg;
			// System.out.println(userList.size());
			// System.out.println(toByteArray(userList).length);
			System.out.println("client read.." + ((String) msg));
		}

	}
}
