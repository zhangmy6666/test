package cn.zmy.spring.proxy;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * 根据被代理对象的信息，动态组装一个代理类，生成Proxy1.java文件，然后将其编译成Proxy1.class。
 * 这样在运行的时候，根据具体的被代理对象生成代理类了。
 * @author zhang
 *
 */
public class Proxy {
	 /** 
     *  
     * @param infce 被代理类的接口 
     * @param h 代理类 
     * @return 
     * @throws Exception 
     */  
    public static Object newProxyInstance(Class infce, InvocationHandler h) throws Exception {   
        String methodStr = "";  
        String rt = "\r\n";  

        //利用反射得到infce的所有方法，并重新组装  
        Method[] methods = infce.getMethods();    
        for(Method m : methods) {  
            methodStr += "    @Override" + rt +   
                         "    public  "+m.getReturnType()+" " + m.getName() + "() {" + rt +  
                         "        try {" + rt +  
                         "        Method md = " + infce.getName() + ".class.getMethod(\"" + m.getName() + "\");" + rt +  
                         "        h.invoke(this, md);" + rt +  
                         "        }catch(Exception e) {e.printStackTrace();}" + rt +                          
                         "    }" + rt ;  
        }  

        //生成Java源文件  
        String srcCode =   
            "package cn.zmy.spring.proxy;" +  rt +  
            "import java.lang.reflect.Method;" + rt +  
            "public class $Proxy1 implements " + infce.getName() + "{" + rt +  
            "    public $Proxy1(InvocationHandler h) {" + rt +  
            "        this.h = h;" + rt +  
            "    }" + rt +            
            "   cn.zmy.spring.proxy.InvocationHandler h;" + rt +                           
            methodStr + rt +  
            "}";  
        String fileName =   
            "D:/git/ProSystem/src/main/java/cn/zmy/spring/proxy/$Proxy1.java";  
        File f = new File(fileName);  
        FileWriter fw = new FileWriter(f);  
        fw.write(srcCode);  
        fw.flush();  
        fw.close();  

        //将Java文件编译成class文件  
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();  
        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);  
        Iterable units = fileMgr.getJavaFileObjects(fileName);  
        CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);  
        t.call();  
        fileMgr.close();  

        //加载到内存，并实例化  
        URL[] urls = new URL[] {new URL("file:/" + "D:/git/ProSystem/src/main/java/")};  
        URLClassLoader ul = new URLClassLoader(urls);  
        Class c = ul.loadClass("cn.zmy.spring.proxy.$Proxy1");  

        Constructor ctr = c.getConstructor(InvocationHandler.class);  
        Object m = ctr.newInstance(h);  

        return m;  
    }
}
