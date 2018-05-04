package com.jhqc.pxsj.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Main2 {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {

		int read = SelectionKey.OP_READ;
		int write = SelectionKey.OP_WRITE;
		int connect = SelectionKey.OP_CONNECT;
		int accept = SelectionKey.OP_ACCEPT;

		//打开一个ServerSocketChannel
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		// 使通道为非阻塞
		serverSocketChannel.configureBlocking(false);
		// 创建基于NIO通道的socket连接
		ServerSocket serverSocket = serverSocketChannel.socket();
		// 新建socket通道的端口
		serverSocket.bind(new InetSocketAddress("127.0.0.1", 80));
		// 创建selector
		Selector selector = Selector.open();
		// 将NIO通道选绑定到择器,当然绑定后分配的主键为skey
		SelectionKey skey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

		while (true) {
			int num = selector.select();// 获取通道内是否有选择器的关心事件
			if (num < 1) {
				continue;
			}
			// 获取通道内关心事件的集合
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> it = selectedKeys.iterator();
			// 遍历事件集合每个key
			while (it.hasNext()) {
				try {
					SelectionKey key = (SelectionKey) it.next();
					// 判断是新连接请求还是旧连接其它事件
					if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
						// 通过key获取对应channel
						ServerSocketChannel serverChanel = (ServerSocketChannel) key.channel(); 
						// 从serverSocketChannel中创建出与客户端的连接socketChannel
						SocketChannel sc = serverChanel.accept();
						sc.configureBlocking(false);
						// 把新连接注册到选择器，对读事件感兴趣
						SelectionKey newKey = sc.register(selector, SelectionKey.OP_READ);
						it.remove();
					} else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
						// 读客户端数据的事件,此时有客户端发数据过来
						SocketChannel sc = (SocketChannel) key.channel();
						//读取数据
						int bytesEchoed = 0;
						ByteBuffer echoBuffer = ByteBuffer.allocate(1024);
						while ((bytesEchoed = sc.read(echoBuffer)) > 0) {
							System.out.println("bytesEchoed:" + bytesEchoed);
						}
						echoBuffer.flip();
						System.out.println("limit:" + echoBuffer.limit());
						byte[] content = new byte[echoBuffer.limit()];
						echoBuffer.get(content);
						String result = new String(content);
						System.out.println("读取内容:" + result);
						echoBuffer.clear();
						it.remove(); // 任务完成，记得上面也是一样，要remove掉，否则下一次又来一次任务，就死循环了
					}
				} catch (Exception e) {
				}
			}
		}
	}

}