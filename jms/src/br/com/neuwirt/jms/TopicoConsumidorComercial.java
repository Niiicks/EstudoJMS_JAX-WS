package br.com.neuwirt.jms;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.InitialContext;

import org.apache.activemq.ActiveMQConnectionFactory;

import br.com.neuwirt.modelo.Pedido;


public class TopicoConsumidorComercial {


	
@SuppressWarnings("resource")
public static void main(String[] args) throws Exception {
	
	//System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
	
	InitialContext context = new InitialContext();	
    ActiveMQConnectionFactory factory = (ActiveMQConnectionFactory) context.lookup("ConnectionFactory");   
    factory.setTrustAllPackages(true);
    
    Connection connection = factory.createConnection();    
    connection.setClientID("comercial");
    
    connection.start();
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
    
    Topic topico = (Topic) context.lookup("loja");
    
	MessageConsumer consumer = session.createDurableSubscriber(topico, "assinatura");
    
	consumer.setMessageListener(new MessageListener() {
		
		@Override
		public void onMessage(Message message) {
			
			ObjectMessage objectMessage = (ObjectMessage) message;
			
			try {
				Pedido pedido = (Pedido) objectMessage.getObject();
				System.out.println(pedido.getCodigo());
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	});
		
    new Scanner(System.in).nextLine(); 
    
    
    session.close();
    connection.close();
    context.close();
}
}

/*
 * 			TextMessage textMessage = (TextMessage) message;
			try {
				System.out.println(textMessage.getText());
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 */
