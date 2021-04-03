package br.com.neuwirt.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;

import br.com.neuwirt.modelo.Pedido;
import br.com.neuwirt.modelo.PedidoFactory;


public class TopicoProdutor {
	

public static void main(String[] args) throws Exception {
	
	
	InitialContext context = new InitialContext(); 
	
    ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");    
    Connection connection = factory.createConnection();    
    connection.start();    
    
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
    
    Destination topico = (Destination) context.lookup("loja");
    
    MessageProducer producer = session.createProducer(topico);  
    
    Pedido pedido = new PedidoFactory().geraPedidoComValores();
    
/*    StringWriter writer = new StringWriter();
    JAXB.marshal(pedido, writer);
    String xml = writer.toString();    	
    System.out.println(xml);
    Message message = session.createTextMessage(xml);
*/
    Message message = session.createObjectMessage(pedido);
    	
    	//message.setBooleanProperty("ebook", false);
    	producer.send(message);

		

    
    session.close();
    connection.close();
    context.close();
}
}
