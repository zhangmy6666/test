package cn.zmy.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import cn.zmy.util.HttpUtil;


public class HttpClientPostTest
{
	private static int count = 0;
	
    /**
     * @Title: main
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws Exception
    {
		long start = System.currentTimeMillis();
		
		int size = 5;
		final CountDownLatch cdl = new CountDownLatch(size);
        ExecutorService es = new ThreadPoolExecutor(5, 20, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));
        for (int i = 0; i < (0 + size); i++) {
            es.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                    	long startT = System.currentTimeMillis();
//                        String strJSON = HttpUtil.sendPost("http://medical.butel.com/mds/hpu/transfer/downloadpdf",
//                            "{\"token\":\"e4ba0efc-c43a-4da1-98a6-70ca19d2e8e8_0ea8689329a446a5b67fc360ef195fc4\","
//                            + "\"id\":\"1fdcabab79274ad39df4011fa7cb4e84\"}");
//                        String strJSON = HttpUtil.sendPost("http://localhost/hpu/transfer/downloadpdf",
//                                "{\"token\":\"c25336f7-1dc6-4582-a42d-38f86b6bd250_0ea8689329a446a5b67fc360ef195fc4\","
//                                + "\"id\":\"0988a66d54ae438088864ce80e3895de\"}");
                    	String strJSON = HttpUtil.sendPost("http://testmedical.butel.com:93/mds/hpu/transfer/downloadpdf",
                                "{\"token\":\"c25336f7-1dc6-4582-a42d-38f86b6bd250_0ea8689329a446a5b67fc360ef195fc4\","
                                + "\"id\":\"0988a66d54ae438088864ce80e3895de\"}");
//                        String strJSON = HttpUtil.postFile("http://210.51.168.214:21001/fileupload?token=0ApFb-QZ60xqHrjjy1yENqafGWZkciHD1gC*XzxscEBuYWdqcDc0MjM4OA__",
//                                "{\"token\":\"0ApFb-QZ60xqHrjjy1yENqafGWZkciHD1gC*XzxscEBuYWdqcDc0MjM4OA__\"}");
//                    	String strJSON = HttpUtil.sendGet("http://114.112.74.80/webapi/config/getfileconfig?token=" 
//                    			+ "0ApFb-QZ60xqHrjjy1yENqafGWZkciHD1gC*XzxscEBuYWdqcDc0MjM4OA__");
                        System.err.println(strJSON);
                        System.err.println(System.currentTimeMillis()-startT);
						cdl.countDown();
					} catch (Exception e) {
						synchronized (HttpClientPostTest.class) {
							count++;
						}
						cdl.countDown();
					}
                }
            });
        }
        cdl.await();
		es.shutdown(); 
        
        long time = System.currentTimeMillis() - start;
		System.out.println("request " + size + "条JSON，共消耗：" + (double) time
				/ 1000 + " s");
		System.out.println("平均：" + size / ((double) time / 1000) + " 条/秒");
		System.out.println("错误：" + count);
    }

}
