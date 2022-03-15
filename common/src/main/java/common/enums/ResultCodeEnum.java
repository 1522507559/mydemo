package common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

public enum ResultCodeEnum implements IEnum {

    CODE_0(0,"success"),
    CODE_1001(1001, "fail"),
    CODE_2001(2001, "complete");

    private final int code;
    private final String msg;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int key(){
        return this.code;
    }

    public String value(){
        return this.msg;
    }

    @Override
    public Serializable getValue() {
        return null;
    }
}
