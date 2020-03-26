package com.atguigu.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.IOException;
import java.util.List;

/**
 * @Author lw
 * @Create2020-03-25 15:09
 */
public class TestZookeeper {

    private String connectString = "localhost:2181";
    private int sessionTimeout = 2000;
    private ZooKeeper zkClient;

    @Before
    public void init() throws IOException {

        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println("-------------start-------------");
                List<String> children = null;
                try {
                    children = zkClient.getChildren("/",true);
                    for (String child : children) {
                        System.out.println(child);
                    }
                    System.out.println("-------------end-------------");
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    //创建节点
    @Test
    public void createMode() throws KeeperException, InterruptedException {
        String path = zkClient.create("/atguigu", "dahaigeziushuai".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path);

    }

    //获取子节点并监控节点变化
    @Test
    public void getDataAndWatch() throws KeeperException, InterruptedException {

        List<String> children = zkClient.getChildren("/", true);
        for (String child : children) {
            System.out.println(child);
        }
        Thread.sleep(Long.MAX_VALUE);
    }

    //判断节点是存在
    @Test
    public void exist() throws KeeperException, InterruptedException {
        Stat stat = zkClient.exists("/atguigu", false);
        System.out.println(stat == null ?"not exist" : "exist");
    }
}
