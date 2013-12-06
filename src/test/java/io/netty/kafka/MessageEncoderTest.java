package io.netty.kafka;

import junit.framework.Assert;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.kafka.codec.MessageEncoder;
import io.netty.kafka.messages.Message;
import io.netty.kafka.messages.MetadataRequest;

public class MessageEncoderTest {

	@Test
	public void testMetadataRequest() throws Exception {
		ByteBuf buf = Unpooled.buffer();
		MetadataRequest request = new MetadataRequest("foo",0,"TestTopic");
		request.write(buf);
		
		Integer len = buf.readInt();
		Assert.assertEquals(3, buf.readShort());
		Assert.assertEquals(0, buf.readShort());
		Assert.assertEquals(0, buf.readInt());
		Assert.assertEquals("foo", Message.readString(buf));
		Assert.assertEquals(1, buf.readInt());
		Assert.assertEquals("TestTopic", Message.readString(buf));
	}
	
}
