package com.self.mybatis;

import java.util.List;

/**
 * @author shichen
 * @create 2018/9/13
 * @desc
 */
public class ProxyMainTest {

    public static void main(String[] args) {
        MapperRegistry mapperRegistry = new MapperRegistry();

        //指定扫描的包，并生成代理对象
        Package pak = Package.getPackage("com.self.mybatis");
        List<Class<?>> clazz = ScannerUtil.getAllClassByPackageName(pak);

        for (int i = 0; i < clazz.size(); i ++) {
            mapperRegistry.addMapper(clazz.get(i));
        }

        UserMapperDao userMapperDao = mapperRegistry.getMapper(UserMapperDao.class);
        String info = userMapperDao.getUser("zsc");
        System.out.println(info);
    }

}
