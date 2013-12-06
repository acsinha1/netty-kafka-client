package io.netty.kafka.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.kafka.messages.RequestMessage;

public class MessageEncoder extends MessageToByteEncoder<RequestMessage> {

	@Override
	protected void encode(ChannelHandlerContext ctx, RequestMessage msg, ByteBuf out)
			throws Exception {
		msg.write(out);
	}

}
