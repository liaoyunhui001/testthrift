package com.hui.thrift;


import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;


/**
 * Server启动类
 * Created by hui on 2017/9/13.
 */
public class HelloServiceServer {
	public void startServer() {
		try {
			//开启一个服务，端口为1234
			TServerSocket serverTransport = new TServerSocket(1234);
			//设置实现的进程
			Hello.Processor process = new Hello.Processor(new HelloServiceImpl());

			Factory portFactory = new TBinaryProtocol.Factory(true, true);


			Args args = new Args(serverTransport);
			args.processor(process);
			args.protocolFactory(portFactory);

			TServer server = new TThreadPoolServer(args);
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		HelloServiceServer server = new HelloServiceServer();
		server.startServer();
	}
}
