package com.sandu.common.util;


import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单号工具类
 * 单例
 * @author xiaobing
 * @date 2020-02-29 10:34
 * @version v1.0.0
 * @Description
 *
 * Modification History:
 * Date                 Author          Version          Description
---------------------------------------------------------------------------------*
 * 2020-02-29 10:34     xiaobing          v1.0.0           Created
 *
 */
public enum NoUtil {

    INSTANCE;


    /**
     * 发票单号
     */
    public static final String PREFIX_INVOICE="ICE";


    private final long datacenterId;
    private final long workerId;

    private final AtomicInteger increment = new AtomicInteger(0);



    private NoUtil() {
        this.datacenterId = this.getDatacenterId(31L);
        this.workerId = this.getMaxWorkerId(this.datacenterId, 31L);
    }


    /**
     * 生成一个单号
     * 生成规则
     *      前缀yyyyMMddWorkerIdDatacenterIdIncrement
     * @param prefix
     * @return
     */
    public String generate(String prefix) {



        LocalDateTime localDateTime = LocalDateTime.now();

        String nowDateTimeStr = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        StringBuffer sb = new StringBuffer();

        sb.append(prefix);
        sb.append(nowDateTimeStr);
        sb.append(this.workerId);
        sb.append(this.datacenterId);
        sb.append(this.getIncrement());

        return sb.toString();
    }


    /**
     * 数据标识id部分
     */
    private long getDatacenterId(long maxDatacenterId) {
        long id = 0L;
        try {
            InetAddress ip = InetAddress.getLocalHost();

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            String di = network.getDisplayName();

            if (network == null) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                if (null != mac) {
                    id = ((0x000000FF & (long) mac[mac.length - 1]) | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
                    id = id % (maxDatacenterId + 1);
                }
            }
        } catch (Exception e) {
        }
        return id;
    }

    /**
     * 获取 maxWorkerId
     */
    private long getMaxWorkerId(long datacenterId, long maxWorkerId) {
        StringBuilder mpid = new StringBuilder();
        mpid.append(datacenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (name != null && !"".equals(name)) {
            /*
             * GET jvmPid
             */
            mpid.append(name.split("@")[0]);
        }
        /*
         * MAC + PID 的 hashcode 获取16个低位
         */
        return (mpid.toString().hashCode() & 0xffff) % (maxWorkerId + 1);
    }


    /**
     * 获取递增量，为防止并发情况导致单号重复
     * 每次递增到1000则从0开始重置
     * 并不能保证该方法不会产生大于1000的值
     * 但是至少能够保证原子性不会出现重复
     * @return
     */
    private int getIncrement() {

        if (this.increment.get() >= 1000) {
            this.increment.set(0);
            return this.increment.incrementAndGet();
        } else {
            return this.increment.incrementAndGet();
        }

    }

    private LocalDateTime a2;





}
