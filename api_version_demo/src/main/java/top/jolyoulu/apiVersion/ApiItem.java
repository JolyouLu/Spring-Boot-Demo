package top.jolyoulu.apiVersion;

import lombok.Data;

/**
 * 版本号，对应的实体类
 * 默认版本为1.0.0，并实现了Comparable接口，支持版本之间的比较
 */
@Data
public class ApiItem implements Comparable<ApiItem> {

    // 版本号：1.0.0  默认
    //第一位数
    private int high = 1;
    //第二位数
    private int mid = 0;
    //第三位数
    private int low = 0;

    public ApiItem() {
    }

    /**
     * compareTo 方法，该方法返回一个 int 类型的数据，但是此 int 的值只能是一下3种：
     * 比较版本大小、是否相等，
     * 1：表示大于
     * -1：表示小于
     * 0：表示相等
     */
    @Override
    public int compareTo(ApiItem right) {
        if (this.getHigh() > right.getHigh()) {
            return 1;
        } else if (this.getHigh() < right.getHigh()) {
            return -1;
        }
        if (this.getMid() > right.getMid()) {
            return 1;
        } else if (this.getMid() < right.getMid()) {
            return -1;
        }
        if (this.getLow() > right.getLow()) {
            return 1;
        } else if (this.getLow() < right.getLow()) {
            return -1;
        }
        return 0;
    }
}