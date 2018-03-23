package cn.zmy.nio.netty;

import java.util.List;

import cn.zmy.nio.util.ByteHelp;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MessageBodyDecoder extends ByteToMessageDecoder {

	@Override
	public void decode(ChannelHandlerContext arg0, ByteBuf in, List<Object> out) {
		try {
			if (in.readableBytes() < 4) {
				return;
			}

			in.markReaderIndex();
			byte[] temp = new byte[4];
			in.readBytes(temp);
			int length = ByteHelp.BytesToInt(temp);
			if (in.readableBytes() < length) {
				in.resetReaderIndex();
				return;
			}
			byte[] data = new byte[length];
			in.readBytes(data, 0, length);
			out.add(data);
		} catch (Exception e) {
			System.out.println("MessageBodyDecoder has errors:" + e.getMessage());
			arg0.flush();
		}
	}
}
