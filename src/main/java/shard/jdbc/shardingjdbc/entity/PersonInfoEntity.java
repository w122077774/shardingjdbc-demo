package shard.jdbc.shardingjdbc.entity;

/**
 * @author snowalker
 * @version 1.0
 * @date 2019/3/23 10:50
 * @className OrderNewInfoEntity
 * @desc 复合分片订单实体
 */
public class PersonInfoEntity {

    private Integer pid;
    private String userId;
    private String orderId;
    private String wife;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getWife() {
        return wife;
    }

    public void setWife(String wife) {
        this.wife = wife;
    }

    @Override
    public String toString() {
        return "PersonInfoEntity{" +
                "pid=" + pid +
                ", userId='" + userId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", wife='" + wife + '\'' +
                '}';
    }
}
