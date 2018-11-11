package com.security.utils;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MqSessionUtils {
	
       private static  ConnectionFactory connectionFactory;
       private static  Connection connection;
       private static Session session;
         
         static{
        	 
        	 connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        	 try {
        		 connection =connectionFactory.createConnection();
             	connection.start();
           	   
			} catch (JMSException e) {
				e.printStackTrace();
			}
         }
	      
        public static Session getSession() throws Exception{
        	
        	session=connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        	
        	if(session ==null){
        		throw new Exception("MQ Session 初始化失败！");
        	}
        	return session;
        }
	         

}
