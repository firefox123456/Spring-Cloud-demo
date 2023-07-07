package hnfnu.zmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


import java.io.IOException;
import java.util.Map;

@Controller
public class DirectReceiver {
    private static int i=0;
    @RabbitListener(queues = {"s001"})
    public void test(Map map ,Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long tag){

        try {
//            if(i!=3){
//                //b1:一个布尔值，表示是否将拒绝的消息重新放回队列。如果设置为true，则消息会被重新放回队列等待重新投递；如果设置为false，则消息会被直接丢弃。
//                System.out.println(i);
//                i++;
//                int j=1/0;
//
//            }
            //else {
                //b：一个布尔值，表示是否确认多条消息。如果设置为true，则表示确认所有小于等于deliveryTag的未确认消息；如果设置为false，则仅确认deliveryTag指定的消息

//            }

            System.out.println("test's map"+map);
            channel.basicAck(tag,false);
        } catch (Exception e) {
            try {
                channel.basicNack(tag, false,true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

//    @RabbitListener(queues = {"s001"})
//    public void test1(Map map){
//
//        System.out.println("test1's map"+map);
//
//    }
}
