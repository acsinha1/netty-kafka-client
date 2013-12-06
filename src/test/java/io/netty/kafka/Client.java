package io.netty.kafka;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {

	
	private final int port;
	private final String host;
	
	
	
	public Client(int port, String host) {
		this.port = port;
		this.host = host;
	}



	public void run(){
		 EventLoopGroup group = new NioEventLoopGroup();
		 try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group).channel(NioSocketChannel.class);
			bootstrap.connect(host, port).sync().channel();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
