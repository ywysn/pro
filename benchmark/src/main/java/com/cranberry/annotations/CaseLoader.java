package main.java.com.cranberry.annotations;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

class CaseRunner{
    private static final int DEFAULT_ITERATIONS = 10;
    private static final int DEFAULT_GROUP = 5;
    private final List<Case> caseList;

    public CaseRunner(List<Case> caseList){
        this.caseList = caseList;
    }

    public void run() throws InvocationTargetException, IllegalAccessException {
        for(Case bCase : caseList){
            int iterations = DEFAULT_ITERATIONS;
            int group = DEFAULT_GROUP;
            Measurement classMeasurement = bCase.getClass().getAnnotation(Measurement.class);
            if(classMeasurement != null){
                iterations = classMeasurement.iterations();
                group = classMeasurement.group();
            }

            Method[] methods = bCase.getClass().getMethods();
            for(Method method : methods){
                Benchmark benchmark = method.getAnnotation(Benchmark.class);
                if(benchmark == null){
                    continue;
                }
                Measurement methodMeasurement = method.getAnnotation(Measurement.class);
                if(methodMeasurement != null){
                    iterations = methodMeasurement.iterations();
                    group = methodMeasurement.group();
                }
                runCase(bCase,method,iterations,group);
            }
        }
    }
    private void runCase(Case bCase,Method method,int iterations,int group) throws InvocationTargetException, IllegalAccessException {
        System.out.println("这是"+bCase.getClass().getName().substring(30)+"类的"
                +method.getName()+"方法"+"共有：");
        System.out.println("共有"+group+"组 每组"+iterations+"次");
        for(int i = 0;i < group;i++){
            long t1 = System.nanoTime();
            for(int j = 0;j < iterations;j++){
                method.invoke(bCase);
            }
            long t2 = System.nanoTime();
            System.out.printf("第%d次实验，耗时：%d纳秒%n",i,t2-t1);
        }
    }

}
public class CaseLoader {

    public CaseRunner load() throws Exception {
        String pkg = "main/java/com/cranberry/cases";
        String pkgDot = "main.java.com.cranberry.cases";
        List<String> classNameList = new ArrayList<>();
        //1.根基一个固定的类，找到类加载器
        //2.根据加载器找到类文件所在路径
        //3.扫描路径所有文件
        ClassLoader classLoader = this.getClass().getClassLoader();
        Enumeration<URL> urls = classLoader.getResources(pkg);

        while (urls.hasMoreElements()){
            URL url = urls.nextElement();
            if(!url.getProtocol().equals("file")){
                //如果不是*.class文件，暂不支持
                continue;
            }

            String dirname = URLDecoder.decode(url.getPath(),"UTF-8");
            File dir = new File(dirname);
            if(!dir.isDirectory()){
                continue;
            }
            File[] files = dir.listFiles();
            if(files == null){
                continue;
            }
            for(File file : files){
                //没有判断后缀是否是*.class
                String filename = file.getName();
                String className = filename.substring(0,filename.length()-6);
                classNameList.add(className);
            }
        }
        List<Case> caseList = new ArrayList<Case>();
        for(String className : classNameList){
            Class<?> cls = Class.forName(pkgDot+"."+className);
            if(hashInterface(cls,Case.class)){
                caseList.add((Case)cls.newInstance());

            }
        }
        return new CaseRunner(caseList);
    }
    private boolean hashInterface(Class<?> cls,Class<?> intf){
        Class<?>[] intfs = cls.getInterfaces();
        for(Class<?> i : intfs){
            if(i == intf){
                return true;
            }
        }
        return false;
    }
}
