package br.com.neuwirt.jms;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;


public class TopicoConsumidorEstoqueSeletor {
	
@SuppressWarnings("resource")
public static void main(String[] args) throws Exception {
	
	
	InitialContext context = new InitialContext();	
    ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");   
    
    Connection connection = factory.createConnection();    
    connection.setClientID("estoque");
    
    connection.start();
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
    
    Topic topico = (Topic) context.lookup("loja");
    		//sobre o false: � para definir se ser� enviado uma ou mais mensagens dessa connection, al�m de s� consumir 
	MessageConsumer consumer = session.createDurableSubscriber(topico, "assinatura-selector", "ebook is null or ebook=false", false ); //
    
	consumer.setMessageListener(new MessageListener() {
		
		@Override
		public void onMessage(Message message) {
			
			TextMessage textMessage = (TextMessage) message;
			try {
				System.out.println(textMessage.getText());
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
