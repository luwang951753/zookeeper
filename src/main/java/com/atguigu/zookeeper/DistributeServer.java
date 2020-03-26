package com.atguigu.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @Author lw
 * @Create2020-03-25 16:40
 */
public class DistributeServer {

    public static void main(String[] args) throws KeeperException, InterruptedException, IOException {

        DistributeServer server = new DistributeServer();

        //1.连接zookeeper集群
        server.getConnect();

        //2.注册节点
        server.regist(args[0]);

        //3.业务逻辑处理
        server.business();

    }

    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    private void regist(String hostname) throws KeeperException, InterruptedException {

        String path = zkClient.create("/servers/server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        System.out.println(hostname+"is online");

    }

    private String connectString = "localhost:2181";
    private int sessionTimeout = 2000;
    ZooKeeper zkClient;
    private void getConnect() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            public void process(WatchedEvent event) {

            }
        });
    }

}
