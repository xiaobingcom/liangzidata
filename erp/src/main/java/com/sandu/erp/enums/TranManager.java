package com.sandu.erp.enums;

import org.apache.ibatis.executor.BaseExecutor;

import java.sql.Connection;
import java.sql.SQLException;

public class TranManager {
    private static ThreadLocal<Connection> tl;



    private TranManager(){}
    public static  Connection getConn(){
        return tl.get();
    }

    public static  void setConn(Connection connection){
      tl.set(connection);
    }
    /**
     * @return 开启手动提交事务，
     * @false: 将sql命令交给应用程序管理
     */
    public static void startTran(){
        try {
            tl.get().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * @return 回滚
     */
    public static void rollbackTran(){
        try {
            tl.get().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * @return 执行
     */
    public static void commitTran(){
        try {
            tl.get().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * @return 关闭
     */
    public static void release(){
        try {
            tl.get().close();
            tl.remove();//map{tl:conn}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*public void rollbackTran(Savepoint sp){
        try {
            conn.rollback(sp);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}