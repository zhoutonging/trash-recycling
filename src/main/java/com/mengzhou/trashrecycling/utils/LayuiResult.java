package com.mengzhou.trashrecycling.utils;


import lombok.Data;

/**
 * Layui返回值封装
 *
 * @author ZHOUTONG
 * @date 2019年08月07日 10:06
 */
@Data
public class LayuiResult {
    private int code;
    private String msg;
    private Long count;
    private Object data;

    public static LayuiResult success(String msg) {
        LayuiResult result = new LayuiResult();
        result.setCode(0);
        result.setMsg(msg);
        return result;
    }

    public static LayuiResult success(Integer code, String msg) {
        LayuiResult result = new LayuiResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static LayuiResult success(Long count, Object data) {
        LayuiResult result = new LayuiResult();
        result.setCode(0);
        result.setMsg("加载成功");
        result.setCount(count);
        result.setData(data);
        return result;
    }

    public static LayuiResult success(String msg, Object data) {
        LayuiResult result = new LayuiResult();
        result.setCode(0);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static LayuiResult success(Object data) {
        LayuiResult result = new LayuiResult();
        result.setCode(0);
        result.setMsg("加载成功");
        result.setData(data);
        return result;
    }


    public static LayuiResult fail() {
        LayuiResult result = new LayuiResult();
        result.setCode(1);
        result.setMsg("处理失败！");
        return result;
    }

    public static LayuiResult fail(String mssge) {
        LayuiResult result = new LayuiResult();
        result.setCode(1);
        result.setMsg(mssge);
        return result;
    }

    public static LayuiResult fail(Integer code, String mssge) {
        LayuiResult result = new LayuiResult();
        result.setCode(code);
        result.setMsg(mssge);
        return result;
    }

}
