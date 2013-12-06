package io.netty.kafka.messages;

import io.netty.buffer.ByteBuf;

public class MetadataResponse extends ResponseMessage {
	
	private Broker[] brokers;
	private TopicMetadata[] topicsMetadata;
	
	

	public void read(ByteBuf buffer) {
		read(buffer);
		Integer numBrokers = buffer.readInt();
		this.brokers = new Broker[numBrokers];
		for(int i=0;i<numBrokers;i++){
			Integer nodeId = buffer.readInt();
			String host = readString(buffer);
			Integer port = buffer.readInt();
			this.brokers[i] = new Broker(nodeId,host,port);
		}
		Integer numTopics = buffer.readInt();
		this.topicsMetadata = new TopicMetadata[numTopics];
		for(int i=0;i<numTopics;i++){
			TopicMetadata metadata = new TopicMetadata();
			metadata.read(buffer);
			topicsMetadata[i] = metadata;
		}
	}
	
	
	public class TopicMetadata {
		Short topicErrorCode;
		String topicName;
		
		PartitionMetadata[] partitions;
		
		public void read(ByteBuf buffer){
			this.topicErrorCode = buffer.readShort();
			this.topicName = Message.readString(buffer);
			int partitionNum = buffer.readInt();
			this.partitions = new PartitionMetadata[partitionNum];
			for(int i=0;i<partitionNum;i++){
				PartitionMetadata partition = new PartitionMetadata();
				partition.read(buffer);
				this.partitions[i] = partition;
			}
		}
		
		public Short getTopicErrorCode() {
			return topicErrorCode;
		}

		public String getTopicName() {
			return topicName;
		}

		public PartitionMetadata[] getPartitions() {
			return partitions;
		}

	}
	
	public class PartitionMetadata{
		Short errorCode;
		Integer partitionId;
		Integer leader;
		Integer[] replicas;
		Integer[] isr;
		
		public void read(ByteBuf buffer){
			this.errorCode = buffer.readShort();
			this.partitionId = buffer.readInt();
			this.leader = buffer.readInt();
			int replicaNum = buffer.readInt();
			if(replicaNum > 0){
				this.replicas = new Integer[replicaNum];
				for(int i=0;i<replicaNum;i++){
					this.replicas[i] = buffer.readInt();
				}
			}
			int isrNum = buffer.readInt();
			if(isrNum > 0){
				this.isr = new Integer[isrNum];
				for(int i=0;i<isrNum;i++){
					this.isr[i] = buffer.readInt();
				}
			}
			
		}

		public Short getErrorCode() {
			return errorCode;
		}

		public Integer getPartitionId() {
			return partitionId;
		}

		public Integer getLeader() {
			return leader;
		}

		public Integer[] getReplicas() {
			return replicas;
		}

		public Integer[] getIsr() {
			return isr;
		}
	}
	
	public class Broker {
		private final Integer	nodeId;
		private final String host;
		private final Integer port;
		
		public Broker(Integer nodeId, String host, Integer port) {
			this.nodeId = nodeId;
			this.host = host;
			this.port = port;
		}

		public Integer getNodeId() {
			return nodeId;
		}

		public String getHost() {
			return host;
		}

		public Integer getPort() {
			return port;
		}
		
		
	}

	public Broker[] getBrokers() {
		return brokers;
	}

	public TopicMetadata[] getTopicsMetadata() {
		return topicsMetadata;
	}

}
