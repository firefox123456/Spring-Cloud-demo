package hnfnu.zmq.pojo;

import lombok.Data;

@Data
public class LoginParam {
    private String userName;
    private String passWord;
    private String captchaKey;
    private String captcha;
}
