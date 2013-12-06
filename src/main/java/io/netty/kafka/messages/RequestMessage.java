package io.netty.kafka.messages;

import io.netty.buffer.ByteBuf;

public abstract class RequestMessage extends Message {
	
	protected Short apiKey;
	protected Short apiVersion;
	protected Integer correlationId;
	protected String clientId;
	
	public void write(ByteBuf buffer){
		buffer.writeShort(apiKey).writeShort(apiVersion).writeInt(correlationId);
		writeString(clientId, buffer);
		
	}

	public Short getApiKey() {
		return apiKey;
	}

	public Short getApiVersion() {
		return apiVersion;
	}

	public Integer getCorrelationId() {
		return correlationId;
	}

	public String getClientId() {
		return clientId;
	}
	
	
	
}
