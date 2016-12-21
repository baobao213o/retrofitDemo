package com.example.admin.rxjava;

import com.example.admin.network.ReturnCode;

/**
 * 自定义异常类-api接口调用 服务器操作异常
 */
public class ServerException extends Exception {
    private  String msg="";
    public ServerException(int returnCode) {
        for( ReturnCode code : ReturnCode.values()){
            if(code.getCode()==returnCode){
                msg=code.getMsg();
                break;
            }
        }
    }
    public String getMessage(){
        return msg;
    }

}
