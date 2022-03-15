package common.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class JsonResult {

    @ApiModelProperty(value="结果状态：成功success、失败failure、完成complete")
    public String code;

    @ApiModelProperty(value="结果信息")
    public String msg;

    @ApiModelProperty(value="结果对象")
    public Object data;

}
