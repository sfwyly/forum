package com.wtu.forum.service.common.safe;

import org.springframework.stereotype.Service;

/**
 * 安全检查
 */
@Service
public class SafeCheckpoint {
    /**
     * 用户名检查
     * @param username
     * @return 是否合法
     */
    public static boolean usernameCheck(String username){

        String pattern = "[a-zA-Z0-9]+(@)[a-zA-Z0-9]+(.)[a-zA-Z]+";
        if(username==null||"".equals(username)){
            return false;
        }else if(username.matches(pattern)){
            return true;
        }else{
            return false;
        }

    }

    /**
     * 密码必须同时含有数字与字母 8-18位
     * @param password
     * @return
     */
    public static boolean passwordCheck(String password){
        if(password==null||"".equals(password)){
            return false;
        }else if(password.length()<8||password.length()>18){
            return false;
        }else{
            int len=password.length(),flag=0;

            for(int i=0;i<len;i++){
                if(password.charAt(i)>='0'&&password.charAt(i)<='9'){
                    ++flag;
                }else if((password.charAt(i)>='a'&&password.charAt(i)<='z')||(password.charAt(i)>='A'&&password.charAt(i)<='Z')){
                    flag+=2;
                }
            }

            if(flag<=len||flag>=2*len){
                return false;
            }else{
                return true;
            }
        }
    }

    /**
     * 用户留言内容检查
     * @param content 留言内容
     * @return 是否合法
     */
    public static boolean contentCheck(String content){

        if(content==null||"".equals(content)||"".equals(content.replace(" ","").replace("\n",""))){
            return false;
        }
        return true;
    }

    private static boolean IntCheck(String content){
        if(content==null||!content.matches("[1-9]{1}[0-9]{0,}")){
            return false;
        }
        return true;
    }


}
