package hnfnu.zmq.controller;

import com.google.code.kaptcha.Producer;
import hnfnu.zmq.common.rabbitmq.RabbitServiceImpl;
import hnfnu.zmq.common.redis.RedisDao;
import hnfnu.zmq.pojo.LoginParam;
import hnfnu.zmq.pojo.Response;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class BaseController {


    private final static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    RabbitServiceImpl rabbitService;

    @Autowired
    RedisDao redisDao;

    @Autowired
    RedissonClient redissonClient;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducer;

    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        logger.info("进入getCaptcha方法");
//        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("captcha");
        String captchaKey = UUID.randomUUID().toString();
        response.setDateHeader("Expires", 0L);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        response.addHeader("captcha-key", captchaKey);
        String capText = captchaProducer.createText();
        String code = capText;//答案
        String capStr = null;
        capStr = capText.substring(0, capText.lastIndexOf("@"));
        code = capText.substring(capText.lastIndexOf("@") + 1);
        redisDao.setEx(captchaKey,code,60,TimeUnit.SECONDS);
        BufferedImage bi = captchaProducer.createImage(capStr);
        try {
            ImageIO.write(bi, "jpg", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/login")
    public Response login(@RequestBody LoginParam loginParam){
        Response response = new Response();
        try {
            System.out.println("loginParam is "+loginParam);
            if(StringUtils.isEmpty(loginParam.getUserName())){
                logger.info("登录时没有username");
                return response;
            }
            if(StringUtils.isEmpty(loginParam.getCaptchaKey())){
                logger.info("登录时没有验证码key");
                return response;
            }
            //验证码不相等
            if (!loginParam.getCaptcha().equals(redisDao.get(loginParam.getCaptchaKey()))) {
                redisDao.delete(loginParam.getCaptchaKey());
                logger.info("登录时验证码错误");
                response.setMsg("验证码错误");
                return response;
            }else{
                redisDao.delete(loginParam.getCaptchaKey());
            }

            //密码加密校验
            String md5Password=DigestUtils.md5DigestAsHex(loginParam.getPassWord().getBytes());
            System.out.println(md5Password);

            //权限认证


            //生成token


            rabbitService.send(loginParam);

            response.setMsg("success");

        }catch (Exception e){
            e.printStackTrace();
        }

        return  response;
    }

}
