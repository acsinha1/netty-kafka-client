package io.netty.kafka.messages;

import io.netty.buffer.ByteBuf;

public abstract class Message {
	
	protected Integer size;
	
	
	
	
	
	
	public static String readString(ByteBuf buffer){
		Short len = buffer.readShort();
		if(len == -1)
			return null;
		byte[] strBytes = new byte[len];
		buffer.readBytes(strBytes);
		return new String(strBytes);
	}
	
	public static void writeString(String s, ByteBuf buffer){
		if(s == null){
			buffer.writeShort(-1);
		}else{
			buffer.writeShort((short)s.length());
			buffer.writeBytes(s.getBytes());
		}
	}
	
}
