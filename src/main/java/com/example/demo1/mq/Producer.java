package com.example.demo1.mq;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

public class Producer {

  public static final String BROKENURL = "tcp://10.101.125.144:61616";


  public static void main(String[] args) {
    ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(BROKENURL);
    try {
      Connection activeMqConnectionFactoryConnection = activeMQConnectionFactory.createConnection();
      activeMqConnectionFactoryConnection.start();
      Session session = activeMqConnectionFactoryConnection
          .createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

      Queue queue = session.createQueue("liangqing");
      MessageProducer messageProducer = session.createProducer(queue);

      for (int i = 0; i < 10; i++) {
        ActiveMQTextMessage activeMqTextMessage = new ActiveMQTextMessage();
        activeMqTextMessage.setText("hello liangqing ,i am  mq");
        activeMqTextMessage.setJMSMessageID(IdWorker.get32UUID());
        messageProducer.send(activeMqTextMessage);
      }

      messageProducer.close();
      session.close();
      activeMqConnectionFactoryConnection.close();

    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

}
