package com.example.demo1.mq.producer;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

@Slf4j
public class TopicProducer {

  public static final String BROKENURL = "tcp://10.101.125.144:61616";
  public static final String TOPIC = "topic";


  public static void main(String[] args) {
    //创建 ConnetionFatory
    ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(BROKENURL);
    try {

      //通过连接工厂创建 连接
      Connection activeMqConnectionFactoryConnection = activeMQConnectionFactory.createConnection();
      activeMqConnectionFactoryConnection.start();
      //通过连接创建session
      Session session = activeMqConnectionFactoryConnection
          .createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

      //通过session创建topic desitination
      Topic topic = session.createTopic(TOPIC);
      //通过session创建队列
      MessageProducer messageProducer = session.createProducer(topic);
      messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
      //循环生产消息
      for (int i = 0; i < 10; i++) {
        //消息类型  ActiveMQTextMessage   ActiveMQMapMessage ActiveMQObjectMessage ActiveMQBytesMessage ActiveMQStreamMessage
        TextMessage textMessage = new ActiveMQTextMessage();
        textMessage.setText("hello liangqing ,i am  mq "+i);
        textMessage.setJMSMessageID(IdWorker.get32UUID());
        messageProducer.send(textMessage);
        log.info("方法名：main,结果{}","消息生产成功");
      }

      log.info("方法名：main,结果{}","消息生产成功");
      session.commit();
      messageProducer.close();
      session.close();
      activeMqConnectionFactoryConnection.close();

    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

}
