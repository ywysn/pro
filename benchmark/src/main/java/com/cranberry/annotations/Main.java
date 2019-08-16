package main.java.com.cranberry.annotations;


public class Main{
    public static void main(String[] args) throws Exception {
        CaseLoader loader = new CaseLoader();
        loader.load().run();
    }
}

//@Measurement(iterations = 10,group = 5)
//public class Main {
//    public static void main(String[] args) {
//        int iteraions = 10;
//        int group = 5;
//        StringConcatCase object = new StringConcatCase();
//        Class<?> cls = object.getClass();
//        Annotation annotationMeasurement = cls.getAnnotation(Measurement.class);
//        if(annotationMeasurement != null){
//            Measurement measurement = (Measurement) annotationMeasurement;
//            iteraions = measurement.iterations();
//            group = measurement.group();
//        }
//        Method[] methods = cls.getMethods();
//
//        for(Method method:methods){
//            Annotation annotationBenchmark = method.getAnnotation(Benchmark.class);
//            if(annotationBenchmark== null){
//                continue;
//            }
//            int methodIteratins = iteraions;
//            int methodGroup = group;
//            System.out.println(method.getName());
//            Annotation methodAnnotation = method.getAnnotation(Measurement.class);
//            if(methodAnnotation != null){
//                Measurement methodMeasurement = (Measurement) methodAnnotation;
//                methodIteratins = methodMeasurement.iterations();
//                methodGroup = methodMeasurement.group();
//            }
//            for(int i = 0;i < methodGroup;i++){
//                long t1 = System.nanoTime();
//                for(int j = 0;j < methodIteratins;j++){
//                    try {
//                        method.invoke(object);
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    } catch (InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//                long t2 = System.nanoTime();
//                System.out.printf("第%d次实验，耗时：%d%n",i,t2-t1);
//            }
//        }
//    }
//}
