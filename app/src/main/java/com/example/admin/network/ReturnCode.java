package com.example.admin.network;

/**
 * response's returnCode
 * Created by yubao on 2016/9/14.
 */
public enum  ReturnCode {

    RET_OK(0,"ok"),
    RET_10001(10001,"错误的请求KEY"),
    RET_10002(10002,"该KEY无请求权限"),
    RET_10003(10003,"KEY过期"),
    RET_10004(10004,"错误的OPENID"),
    RET_10005(10005,"应用未审核超时，请提交认证"),
    RET_10007(10007,"未知的请求源"),
    RET_10008(10008,"被禁止的IP"),
    RET_10009(10009,"被禁止的KEY"),
    RET_10011(10011,"当前IP请求超过限制"),
    RET_10012(10012,"请求超过次数限制"),
    RET_10013(10013,"测试KEY超过请求限制"),
    RET_10014(10014,"系统内部异常"),
    RET_10020(10020,"接口维护"),
    RET_10021(10021,"接口停用");

    private String msg ;
    private int code ;

    ReturnCode( int code,String msg){
        this.msg = msg ;
        this.code = code ;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

}
