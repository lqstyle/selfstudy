package com.example.demo1.mq.consumer;

import java.io.IOException;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

@Slf4j
public class QueueMqConsumer {

  public static final String BROKENURL = "tcp://10.101.125.144:61616";


  public static void main(String[] args) {
   // commonWay();
    listenerWay();
  }

  private static void listenerWay() {
    ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(BROKENURL);
    try {
      Connection activeMqConnectionFactoryConnection = activeMQConnectionFactory.createConnection();
      activeMqConnectionFactoryConnection.start();
      Session session = activeMqConnectionFactoryConnection
          .createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

      Queue queue = session.createQueue("liangqing");
      MessageConsumer messageConsumer = session
          .createConsumer(queue);

      MessageListener messageListener  = message -> {
        TextMessage receive = (TextMessage) message;
        try {
          log.info("方法名：main,消息消费成功 {}",receive.getText());
        } catch (JMSException e) {
          log.error("执行异常");
        }
      };
      messageConsumer.setMessageListener(messageListener);


      System.in.read();
      //session.commit();
      messageConsumer.close();
      session.close();
      activeMqConnectionFactoryConnection.close();

    } catch (JMSException | IOException e) {
      e.printStackTrace();
    }
  }


  public  static void commonWay(){
    ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(BROKENURL);
    try {
      Connection activeMqConnectionFactoryConnection = activeMQConnectionFactory.createConnection();
      activeMqConnectionFactoryConnection.start();
      Session session = activeMqConnectionFactoryConnection
          .createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

      Queue queue = session.createQueue("liangqing");
      MessageConsumer messageConsumer = session
          .createConsumer(queue);

      while (true) {
        TextMessage receive = (TextMessage) messageConsumer.receive(100);
        if (receive != null) {
          log.info("方法名：main,消息消费成功 {}",receive.getText());
        } else {
          break;
        }
      }

      session.commit();
      messageConsumer.close();
      session.close();
      activeMqConnectionFactoryConnection.close();

    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

}
