package br.neuwirt.jms.log;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;


public class FilaProdutor {
	

public static void main(String[] args) throws Exception {
	
	
	InitialContext context = new InitialContext(); 
	
    ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");  
    
    Connection connection = factory.createConnection();  
    
    connection.start();    
    
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
    
    Destination fila = (Destination) context.lookup("LOG");
	
    session.createProducer(fila);
    
    MessageProducer producer = session.createProducer(fila);    

    	Message message = session.createTextMessage(" WARN | Listening for connections at: mqtt://DBFWK087:1883?maximumConnections=1000&wireFormat.maxFrameSize=104857600");
    	producer.send(message, DeliveryMode.NON_PERSISTENT, 7, 80000 );

		
    //new Scanner(System.in).nextLine(); 
    
    
    session.close();
    connection.close();
    context.close();
}
}
