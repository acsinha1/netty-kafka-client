package io.netty.kafka.messages;

import io.netty.buffer.ByteBuf;

public class MetadataRequest extends RequestMessage {
	
	protected String[] topics;
	
	public MetadataRequest(String clientId, Integer correlationId, String... topics){
		this.topics = topics;
		this.apiVersion = 0;
		this.apiKey = 3;
		this.correlationId = correlationId;
		this.clientId = clientId;
	}
	
	@Override
	public void write(ByteBuf buffer) {
		ByteBuf body = buffer.copy();
		super.write(body);
		if(topics != null && topics.length > 0){
			body.writeInt(topics.length);
			for(int i=0;i<topics.length;i++){
				writeString(topics[i], body);
			}
		}
		buffer.writeInt(body.writerIndex());
		buffer.writeBytes(body);
	}

	public String[] getTopics() {
		return topics;
	}

	

}
