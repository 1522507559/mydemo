package common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum ConnTypeEnum implements IEnum<Integer> {
    CLUSTER(0, "集群"),
    SINGLE(1, "单点"),
    ;

    private final Integer code;
    private final String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    ConnTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.code;
    }
}
