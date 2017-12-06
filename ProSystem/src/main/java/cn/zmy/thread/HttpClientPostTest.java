package cn.zmy.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.zmy.util.HttpUtil;


public class HttpClientPostTest
{
	private static int count = 0;
	
    /**
     * TODO
     * main
     * @Title: main
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws Exception
    {
		long start = System.currentTimeMillis();
		
		int size = 2;
		final CountDownLatch cdl = new CountDownLatch(size);
        ExecutorService es = Executors.newFixedThreadPool(500);
        for (int i = 0; i < (0 + size); i++) {
            es.execute(new Runnable() {
                @Override
                public void run() {
                    try {
//                        
                        String strJSON = HttpUtil.sendPost("http://testmedical.butel.com:93/mds/hpu/csl/accept",
                            "{\"token\":\"9d819167-4674-4296-bf6a-e7ce251bf9cb_e16557a20eae4093874c3faff893a62e\","
                            + "\"id\":\"03517fad53ed467a99e1e74f5235dcf8\"}");
//                        String strJSON = HttpUtil.sendPost("http://localhost/hpu/csl/accept",
//                                "{\"token\":\"9d819167-4674-4296-bf6a-e7ce251bf9cb_e16557a20eae4093874c3faff893a62e\","
//                                + "\"id\":\"03517fad53ed467a99e1e74f5235dcf8\"}");
                        System.err.println(strJSON);
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
