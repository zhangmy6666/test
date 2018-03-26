package cn.zmy.nio.async.server;

public class AsyncServer {
	private static int DEFAULT_PORT = 12345;
	private static AsyncServerHandler serverHandler;
	public volatile static long clientCount = 0;

	public static void start() {
		start(DEFAULT_PORT);
	}

	public static synchronized void start(int port) {
		if (serverHandler != null)
			return;
		serverHandler = new AsyncServerHandler(port);
		new Thread(serverHandler, "AsyncServer").start();
	}

	public static void main(String[] args) {
		AsyncServer.start();
	}

}
