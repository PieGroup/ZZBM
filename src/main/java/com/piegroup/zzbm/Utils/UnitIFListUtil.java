package com.piegroup.zzbm.Utils;

import sun.misc.Contended;

import java.io.File;
import java.io.FileFilter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @param <C> 代表继承或者implements的类
 */
@Contended
public class UnitIFListUtil<C>  {


    private  ClassLoader classLoader = getClass().getClassLoader();
    //用来保存继承的类和使用的注解
    //private Map< Annotation,Class<? extends C>> ClazzAnnotations = new HashMap< Annotation, Class<? extends C>>();
    /**
     * @param packages 实现的包
     * @return
     */
    public  List getObjectUnits(String packages){
        //设置查询的包名

        List<Class<? extends C>> objectList = new ArrayList<Class<? extends C>>();//策略列表


        File[] resources = getResources(packages);//获取包下面所有的class文件

        Class<Object> objectClass = null;
        try {
            objectClass = (Class<Object>) classLoader.loadClass(Object.class.getName());//使用相同的加载器加载策略接口
        } catch (ClassNotFoundException e1) {
            throw new RuntimeException("未找到策略接口");
        }
        for (int i = 0; i < resources.length; i++) {
            try {
                //载入包下的类
                Class<?> clazz = classLoader.loadClass(packages + "." + resources[i].getName().replace(".class", ""));
                //判断是否是CalPrice的实现类并且不是CalPrice它本身，满足的话加入到策略列表
                if (Object.class.isAssignableFrom(clazz) && clazz != objectClass) {
//                    A annotation =  handleAnnotation((Class<? extends C>) clazz);
//                    Annotation annotation = handleAnnotation((Class<? extends C>) clazz);
//                    if (annotation == null) break;
//                    ClazzAnnotations.put(annotation,(Class<? extends C>) clazz);
                    objectList.add((Class<? extends C>) clazz);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
//        for (Class<? extends T> clazz : objectList) {
//            A annotation = handleAnnotation(clazz);
//            annotationList.add(annotation);
//        }
        return objectList;
    }

    //扫面包下面所有的class文件
    private   File[] getResources(String packages){
        try {
            File file = new File(classLoader.getResource(packages.replace(".", "/")).toURI());
            return file.listFiles(new FileFilter() {
                public boolean accept(File pathname) {
                    if (pathname.getName().endsWith(".class")) {//我们只扫描class文件
                        return true;
                    }
                    return false;
                }
            });
        } catch (URISyntaxException e) {
            throw new RuntimeException("未找到策略资源");
        }
    }


    public static UnitIFListUtil getInstance() {
        return UnitIFListUtilInstance.instance;
    }

    private static class UnitIFListUtilInstance {

        private static UnitIFListUtil instance = new UnitIFListUtil();
    }

}
