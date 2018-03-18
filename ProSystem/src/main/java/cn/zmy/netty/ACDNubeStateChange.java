package cn.zmy.netty;

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




public class ACDNubeStateChange
{
    
    private static String NUBE_STATE_PORT = "8888";
    
    public void start() {
        // EventLoop 代替原来的 ChannelFactory
       EventLoopGroup bossGroup = new NioEventLoopGroup();
       EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // server端采用简洁的连写方式，client端才用分段普通写法。
           serverBootstrap.group(bossGroup, workerGroup)
                     .channel(NioServerSocketChannel. class )
                     .childHandler( new ChannelInitializer<SocketChannel>() {
                           @Override
                           public void initChannel(SocketChannel ch)
                                    throws Exception {
                               ch.pipeline().addLast(
                            		   new StringEncoder(CharsetUtil.UTF_8),
                            		   new MessageBodyDecoder(),
                                       new ACDNubeStateChangeHandler());
                          }
                     }).option(ChannelOption. SO_KEEPALIVE , true )
                     .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                     .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
           // Start the server.
           ChannelFuture f = serverBootstrap.bind(Integer.parseInt(NUBE_STATE_PORT)).sync();
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

   private static class ACDNubeStateChangeHandler extends ChannelInboundHandlerAdapter {
	   
	   /**
        * 异常处理
        * 
        */
       @Override
	   public void channelInactive(ChannelHandlerContext ctx) throws Exception
       {
//       	   logger.error("channelInactive!");
    	   TCPDataSendCache.removeChannel(ctx.channel());
       }
	   
        /**
         * 异常处理
         * 
         */
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//        	logger.error("exceptionCaught:" + cause.getMessage());
     	    TCPDataSendCache.removeChannel(ctx.channel());
        	ctx.close();
        }

        /**
         * 当绑定到服务端的时候触发
         * 
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
            String msg =  new String((byte[]) obj);
            if(StringUtils.isNotEmpty(msg)) {
//        		ACDNubeStateDto nubeStateDto = null;
//    			StringBuilder sb = new StringBuilder();
//            	try{
//        	    	String[] msgArr = msg.split(";");
//        	    	for(String ns:msgArr){
//        	    		String[] values = ns.split(",");
//        	    		if(values.length != ACDConstants.PERIODIC_HEART_LENGTH 
//        	    				&& values.length != ACDConstants.APERIODIC_HEART_LENGTH_IDEL
//        	    				&& values.length != ACDConstants.APERIODIC_HEART_LENGTH_NOSESSION
//        	    		        && values.length != ACDConstants.APERIODIC_HEART_LENGTH) {
//        	    			logger.error("receive msg format error:" + msg);
//        	    			continue;
//        	    		}
//        	    		// 无效的心跳包
//        	    		nubeStateDto = new ACDNubeStateDto(values);
//        	    		if (StringUtils.isBlank(nubeStateDto.getNubeAN())
//        	    				|| "0".equals(nubeStateDto.getNubeAN()))
//        	    		{
//        	    			// 更新链路时间
//        	    			TCPDataSendCache.updateChannelTime(ctx.channel());
//        	    			// 只返回最后一条数据确认
//        	    			sb.append("3");
//        		    		sb.append("|");
//        		    		sb.append(";");
//        	    			continue;
//        	    		}
//        	            MqttSubscribeCache.add(nubeStateDto);
//        	    		
//        	    	}
//            	} catch(Exception ex){
//            		logger.error("run() ex:", ex);
//            	}
//
//	    		if (null != nubeStateDto && -1 != nubeStateDto.getSeqno()) {
//	    			// 只返回最后一条数据确认
//	    			sb.append("2");
//		    		sb.append("|");
//		    		sb.append(nubeStateDto.getSeqno());
//		    		sb.append(";");
//	    		}
//	    		// 增加空白确认
//	    		if (StringUtils.isNotBlank(sb.toString()))
//	    		{
//		    		ctx.writeAndFlush(sb.toString());
//	    		}
            }
        }
    }
}
