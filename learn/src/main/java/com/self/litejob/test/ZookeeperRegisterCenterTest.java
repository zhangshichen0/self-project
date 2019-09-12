package com.self.litejob.test;

import com.self.litejob.reg.zookeeper.ZookeeperConfiguration;
import com.self.litejob.reg.zookeeper.ZookeeperRegisterCenter;

/**
 * @author shichen
 * @create 2018/6/13
 * @desc
 */
public class ZookeeperRegisterCenterTest {

    public static void main(String[] args) {
        ZookeeperConfiguration zkConfig = new ZookeeperConfiguration();
        zkConfig.setServerLists("127.0.0.1:2181");
        zkConfig.setNamespace("lite-job-test");

        ZookeeperRegisterCenter zkRegisterCenter = new ZookeeperRegisterCenter(zkConfig);
        zkRegisterCenter.init();


        System.out.println(zkRegisterCenter.getRegistryCenterTime("/systemTime/current"));
    }

}
