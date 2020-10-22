package com.example.demo1.mq.producer;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQStreamMessage;
import org.apache.activemq.command.ActiveMQTextMessage;

/*
activemq 消息队列

JMS规范  1、新建connectionFactory->2 新建connection->3 新建session ->4.session产生message
MessageProducer  sendMessage 到 desitination(queue和topic)
MessageConsumer  receive from desitination(queue和topic)
receive 同步阻塞     receive 阻塞一段时间  receiveNoWait 直接返回
MessageListener 异步非阻塞

session 可以创建是否开启事务，签收方式
当开启事务时，必须手动调用session.commit
事务偏重于生产者，签收偏向消费者
事务的优先级大于签收  当提交了事务，可以不用签收


队列 destination 是queue
   特点： 1对1 1个消息只能被一个消费者消费
         无时间上的关联性，生产者和消费者不需要同时在线
         不会重复消费，如果消息被消费后，会从队列上移除
  生产消费场景
    1.生产者先启动，消费者后启动（多个实例） 先启动的先消费消息
    2.生产者后启动，消费者先启动（多实例） 消息平均分配到消费者去消费

topic 生产消费原理， desitination 是topic
      特点：  1对多  1个生产者对应多个消费者
             有时间上的关联性，消费者必须先启动，生产者若先启动，此时消费者还没有订阅，则产生的消息是废消息，无状态化的
             允许持久订阅，定消费者离线，再启动时，还是能够消费生产者之前生产的消息 一定程度上放松了时间相关性的要求
      必须先启动多个消费者实例，再启动生产者，这样，消费者才能消费，
      所有的消费者获取到的消息都是同一个topic下的所有消息

消息持久化 类似于 redis持久化 rdb aop
activemq持久化：两种模式 DeliveryMode.PERSISTENT  DeliveryMode.NON_PERSISTENT
生产者调用messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

如果是不设置默认持久化
若设置了非持久化 ，则mq服务器宕机后，消息不会恢复， 若设置了持久化，则会恢复， 会默认持久化到kahadb中

top持久化

TopicSubscriber topicSubscriber = session.createDurableSubscriber 通过session创建
topicSubscriber.receive 不通过消费者去获取了  activeMqConnectionFactoryConnection需要设置clientId
 */


@Slf4j
public class QueueProducer {

  public static final String BROKENURL = "tcp://10.101.125.144:61616";


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

      //通过session创建queue desitination
      Queue queue = session.createQueue("liangqing");
      //通过session创建队列
      MessageProducer messageProducer = session.createProducer(queue);
      //messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
      //messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

      //循环生产消息
      for (int i = 0; i < 10; i++) {
        //消息类型  ActiveMQTextMessage   ActiveMQMapMessage ActiveMQObjectMessage ActiveMQBytesMessage ActiveMQStreamMessage
        ActiveMQTextMessage activeMqTextMessage = new ActiveMQTextMessage();
        activeMqTextMessage.setText("hello liangqing ,i am  mq");
        activeMqTextMessage.setJMSMessageID(IdWorker.get32UUID());
        messageProducer.send(activeMqTextMessage);
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
