package cn.zmy.nio.async.client;

import java.util.Scanner;

public class AsyncClient {

	private static String DEFAULT_HOST = "127.0.0.1";
	private static int DEFAULT_PORT = 12345;
	private static AsyncClientHandler clientHandler;

	public static void start() {
		start(DEFAULT_HOST, DEFAULT_PORT);
	}

	public static synchronized void start(String ip, int port) {
		if (clientHandler != null) {
			return;
		}			
		clientHandler = new AsyncClientHandler(ip, port);
		new Thread(clientHandler, "AsyncClient").start();
	}

	// 向服务器发送消息
	public static boolean sendMsg(String msg) throws Exception {
		if (msg.equals("q")) {
			return false;
		}
		clientHandler.sendMsg(msg);
		return true;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		AsyncClient.start();
		System.out.println("请输入请求消息：");
		Scanner scanner = new Scanner(System.in);
		while (AsyncClient.sendMsg(scanner.nextLine()))
			;
	}
}
