package cn.zmy.nio.netty;

import org.apache.commons.lang.StringUtils;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class ACDNubeStateChange {

	private static String PORT = "8888";

	public void start() {
		// EventLoop 代替原来的 ChannelFactory
		/**
		 * Netty 基于事件驱动模型，使用不同的事件来通知状态的改变或者操作状态的改变。
		 * EventLoop定义了在整个连接的生命周期里当有事件发生的时候处理的核心抽象。
		 * 
		 * 当一个连接到达时，Netty就会注册一个 Channel，
		 * 然后从 EventLoopGroup 中分配一个 EventLoop 绑定到这个Channel上，
		 * 在该Channel的整个生命周期中都是有这个绑定的 EventLoop 来服务的。
		 * 
		 * 一个 EventLoopGroup 包含一个或多个 EventLoop。
		 * 一个 EventLoop 在它的生命周期内只能与一个Thread绑定,它处理的 I/O 事件都将在专有的 Thread 上被处理。
		 * 一个 Channel 在它的生命周期内只能注册与一个 EventLoop。一个 EventLoop可被分配至一个或多个 Channel 。
		 */
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			// server端采用简洁的连写方式，client端才用分段普通写法。
			/**
			 * ChannelPipeline为 ChannelHandler链提供了一个容器并定义了用于沿着链传播入站和出站事件流的 API。
			 * 一个数据或者事件可能会被多个 Handler 处理，在这个过程中，数据或者事件经流 ChannelPipeline，由 ChannelHandler 处理。
			 * 在这个处理过程中，一个 ChannelHandler 接收数据后处理完成后交给下一个 ChannelHandler，或者什么都不做直接交给下一个 ChannelHandler。
			 * 
			 * 当 ChannelHandler 被添加到 ChannelPipeline 时，它将会被分配一个 ChannelHandlerContext，
			 * ChannelHandlerContext代表了 ChannelHandler 和 ChannelPipeline 之间的绑定。
			 *  ChannelHandler 添加到 ChannelPipeline 过程如下：
			 *  1. 一个 ChannelInitializer 的实现被注册到了 ServerBootStrap中
			 *  2. 当 ChannelInitializer.initChannel() 方法被调用时，ChannelInitializer 将在 ChannelPipeline 中安装一组自定义的 ChannelHandler
			 *  3. ChannelInitializer 将它自己从 ChannelPipeline 中移除
			 * 
			 */
			serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8), new MessageBodyDecoder(),
									new ACDNubeStateChangeHandler());
						}
					}).option(ChannelOption.SO_KEEPALIVE, true)
					.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
					.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
			// Start the server.
			/**
			 * Netty为异步非阻塞，我们不能立刻得知消息是否已经被处理了。
			 * Netty 提供了 ChannelFuture 接口，通过该接口的 addListener()方法注册一个 ChannelFutureListener
			 * 当操作执行成功或者失败时，监听就会自动触发返回结果。
			 */
			ChannelFuture f = serverBootstrap.bind(Integer.parseInt(PORT)).sync();
			// Wait until the server socket is closed.
			f.channel().closeFuture().sync();

		} catch (InterruptedException e) {
			System.out.println("InterruptedException had occured!");
		} finally {
			System.out.println("shutdown !");
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	/**
	 * ChannelHandler 为 Netty 中最核心的组件，
	 * 它充当了所有处理入站和出站数据的应用程序逻辑的容器。
	 * ChannelHandler 主要用来处理各种事件，比如可以是连接、数据接收、异常、数据转换等。
	 * 
	 * ChannelHandler 有两个核心子类 ChannelInboundHandler 和 ChannelOutboundHandler，
	 * 其中 ChannelInboundHandler 用于接收、处理入站数据和事件，
	 * 而 ChannelOutboundHandler 则相反。
	 * 
	 * 此处的ChannelHandler属于ChannelInboundHandler
	 * @author zhang
	 *
	 */
	private static class ACDNubeStateChangeHandler extends ChannelInboundHandlerAdapter {

		@Override
		public void channelInactive(ChannelHandlerContext ctx) throws Exception {
			// logger.error("channelInactive!");
			TCPDataSendCache.removeChannel(ctx.channel());
		}

		/**
		 * 异常处理
		 * 
		 */
		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
			// logger.error("exceptionCaught:" + cause.getMessage());
			TCPDataSendCache.removeChannel(ctx.channel());
			ctx.close();
		}

		/**
		 * 当绑定到服务端的时候触发
		 */
		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			TCPDataSendCache.addChannel(ctx.channel());
		}

		/**
		 * 读取数据
		 * 
		 */
		@Override
		public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
			String msg = new String((byte[]) obj);
			if (StringUtils.isNotEmpty(msg)) {
				// ACDNubeStateDto nubeStateDto = null;
				// StringBuilder sb = new StringBuilder();
				// try{
				// String[] msgArr = msg.split(";");
				// for(String ns:msgArr){
				// String[] values = ns.split(",");
				// if(values.length != ACDConstants.PERIODIC_HEART_LENGTH
				// && values.length != ACDConstants.APERIODIC_HEART_LENGTH_IDEL
				// && values.length !=
				// ACDConstants.APERIODIC_HEART_LENGTH_NOSESSION
				// && values.length != ACDConstants.APERIODIC_HEART_LENGTH) {
				// logger.error("receive msg format error:" + msg);
				// continue;
				// }
				// // 无效的心跳包
				// nubeStateDto = new ACDNubeStateDto(values);
				// if (StringUtils.isBlank(nubeStateDto.getNubeAN())
				// || "0".equals(nubeStateDto.getNubeAN()))
				// {
				// // 更新链路时间
				// TCPDataSendCache.updateChannelTime(ctx.channel());
				// // 只返回最后一条数据确认
				// sb.append("3");
				// sb.append("|");
				// sb.append(";");
				// continue;
				// }
				// MqttSubscribeCache.add(nubeStateDto);
				//
				// }
				// } catch(Exception ex){
				// logger.error("run() ex:", ex);
				// }
				//
				// if (null != nubeStateDto && -1 != nubeStateDto.getSeqno()) {
				// // 只返回最后一条数据确认
				// sb.append("2");
				// sb.append("|");
				// sb.append(nubeStateDto.getSeqno());
				// sb.append(";");
				// }
				// // 增加空白确认
				// if (StringUtils.isNotBlank(sb.toString()))
				// {
				// ctx.writeAndFlush(sb.toString());
				// }
			}
		}
	}
}
