package cn.zmy.nio.netty;

import cn.zmy.nio.util.MessageBodyDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class NettyServer {
	
	public static void main(String args[]) {
		NettyServer.start();
	}

	private static String PORT = "8888";

	public static void start() {
		// EventLoop 代替原来的 ChannelFactory
		/**
		 * Netty 基于事件驱动模型，使用不同的事件来通知状态的改变或者操作状态的改变。
		 * EventLoop定义了在整个连接的生命周期里当有事件发生的时候处理的核心抽象。
		 * 
		 * 当一个连接到达时，Netty就会注册一个 Channel， 然后从 EventLoopGroup 中分配一个 EventLoop
		 * 绑定到这个Channel上， 在该Channel的整个生命周期中都是有这个绑定的 EventLoop 来服务的。
		 * 
		 * 一个 EventLoopGroup 包含一个或多个 EventLoop。 一个 EventLoop
		 * 在它的生命周期内只能与一个Thread绑定,它处理的 I/O 事件都将在专有的 Thread 上被处理。 一个 Channel
		 * 在它的生命周期内只能注册与一个 EventLoop。一个 EventLoop可被分配至一个或多个 Channel 。
		 */
		//bossGroup线程组实际就是Acceptor线程池，负责处理客户端的TCP连接请求
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// workerGroup是真正负责I/O读写操作的线程组，通过ServerBootstrap的group方法进行设置，用于后续的Channel绑定。
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			// server端采用简洁的连写方式，client端才用分段普通写法。
			/**
			 * ChannelPipeline为 ChannelHandler链提供了一个容器并定义了用于沿着链传播入站和出站事件流的 API。
			 * 一个数据或者事件可能会被多个 Handler 处理，在这个过程中，数据或者事件经流 ChannelPipeline，由
			 * ChannelHandler 处理。 在这个处理过程中，一个 ChannelHandler 接收数据后处理完成后交给下一个
			 * ChannelHandler，或者什么都不做直接交给下一个 ChannelHandler。
			 * 
			 * 当 ChannelHandler 被添加到 ChannelPipeline 时，它将会被分配一个
			 * ChannelHandlerContext， ChannelHandlerContext代表了 ChannelHandler 和
			 * ChannelPipeline 之间的绑定。 ChannelHandler 添加到 ChannelPipeline 过程如下：
			 * 1. 一个 ChannelInitializer 的实现被注册到了 ServerBootstrap中 2. 当
			 * ChannelInitializer.initChannel() 方法被调用时，ChannelInitializer 将在
			 * ChannelPipeline 中安装一组自定义的 ChannelHandler 3. ChannelInitializer
			 * 将它自己从 ChannelPipeline 中移除
			 * 
			 */
			serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(
									new StringEncoder(CharsetUtil.UTF_8),
//									new MessageBodyDecoder(),
									new StringDecoder(CharsetUtil.UTF_8),
									new StateChangeHandler());
						}
					}).option(ChannelOption.SO_KEEPALIVE, true);
//					.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
//					.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
			// Start the server.
			/**
			 * Netty为异步非阻塞，我们不能立刻得知消息是否已经被处理了。 Netty 提供了 ChannelFuture 接口，通过该接口的
			 * addListener()方法注册一个 ChannelFutureListener
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
	 * ChannelHandler 为 Netty 中最核心的组件， 它充当了所有处理入站和出站数据的应用程序逻辑的容器。 
	 * 主要用来处理各种事件，比如可以是连接、数据接收、异常、数据转换等。
	 * 
	 * ChannelHandler 有两个核心子类 ChannelInboundHandler 和 ChannelOutboundHandler， 其中
	 * ChannelInboundHandler 用于接收、处理入站数据和事件， 而 ChannelOutboundHandler 则相反。
	 * 
	 * 此处的ChannelHandler属于ChannelInboundHandler
	 * 
	 * @author zhang
	 *
	 */
	private static class StateChangeHandler extends ChannelInboundHandlerAdapter {

		@Override
		public void channelInactive(ChannelHandlerContext ctx) throws Exception {
			System.err.println("channelInactive!");
			NettyServerHandler.removeChannel(ctx.channel());
		}

		/**
		 * 异常处理
		 * 
		 */
		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
			System.err.println("exceptionCaught:" + cause.getMessage());
			NettyServerHandler.removeChannel(ctx.channel());
			ctx.close();
		}

		/**
		 * 当绑定到服务端的时候触发
		 */
		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			System.out.println("Hello world, I'm server.");
			NettyServerHandler.addChannel(ctx.channel());
//			ctx.writeAndFlush("hahah--------");
			
			 String msg = "Hello, I'm server.";
			 ByteBuf buf = ctx.alloc().buffer(msg.length());
			 buf.writeBytes(msg.getBytes());
			 ctx.writeAndFlush(buf);
		}

		/**
		 * 读取数据
		 * 
		 */
		@Override
		public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
			System.out.println("server read.." + ((String) obj));
		}
	}
}
