package io.netty.kafka.messages;

import io.netty.buffer.ByteBuf;

public abstract class ResponseMessage extends Message {
	
	private Integer correlationId;

	

	protected void read(ByteBuf buffer) {
		this.size = buffer.readInt();
		this.correlationId = buffer.readInt();
	}
	
	
	
}
