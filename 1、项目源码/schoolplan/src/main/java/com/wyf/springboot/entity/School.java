package com.wyf.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author wyf
 * @since 2022-07-27
 */
@Getter
@Setter
@TableName("sys_school")
@ApiModel(value = "School对象", description = "")
@ToString
public class School implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("sid")
    private Integer sid;

    @ApiModelProperty("school")
    private String school;

    @ApiModelProperty("cityCode")
    private Integer cityCode;

    @ApiModelProperty("city")
    private String city;

    @ApiModelProperty("subType")
    private Integer subType;

    @ApiModelProperty("majorCode")
    private String majorCode;

    @ApiModelProperty("profess")
    private String profess;

    @ApiModelProperty("plan")
    private Integer plan;


}
