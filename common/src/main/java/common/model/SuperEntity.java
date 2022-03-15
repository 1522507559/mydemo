package common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SuperEntity {

    @ApiModelProperty(value="Id")
    @TableField("id")
    @TableId(type = IdType.AUTO)
    public Integer id;

    @ApiModelProperty(value="创建时间")
    @TableField("gmt_create")
    public Date createTime;

    @ApiModelProperty(value="更新时间")
    @TableField("gmt_modified")
    public Date updateTime;

}
