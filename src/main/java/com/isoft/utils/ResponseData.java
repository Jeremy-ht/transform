package com.isoft.utils;

import com.isoft.enums.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@ApiModel(value = "全局统一返回结果")
public class ResponseData {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回状态码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<>();

    public ResponseData() {
    }

    public static ResponseData success(){
        ResponseData res = new ResponseData();
        res.setSuccess(true);
        res.setCode(ResultCodeEnum.SUCCESS.getCode());
        res.setMessage("操作成功");
        return res;
    }

    public static ResponseData error(){
        ResponseData res = new ResponseData();
        res.setSuccess(false);
        res.setCode(ResultCodeEnum.FAILED.getCode());
        res.setMessage("操作失败");
        return res;
    }

    public ResponseData success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public ResponseData message(String mes){
        this.setMessage(mes);
        return this;
    }

    public ResponseData code(Integer code){
        this.setCode(code);
        return this;
    }

    public ResponseData data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public ResponseData data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
