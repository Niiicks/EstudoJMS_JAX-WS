package com.daniele.batch.processor;

import java.time.LocalDate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.springframework.batch.item.ItemProcessor;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.daniele.batch.model.Duplicata;
import com.daniele.batch.model.Nota;

public class NotaProcessor implements ItemProcessor<String, Nota>{

	@Override
	public Nota process(String item) throws Exception {

		Nota nota = new Nota();

		//Permite a construção de um modelo de arvore DOM(Document Object Model) 
		//com a possibilidade de realizar uma análise sintática (Que seria o parse)
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
		
		//Cria a instância para um novo documento
		DocumentBuilder builder = factory.newDocumentBuilder();	
		
		//Faz análise do conteúdo do XML no endereço passado e retorna um documento DOM 
		Document doc = builder.parse("C:\\ArquivoProcessado\\"+item);

		//Instância que será usada pra criar um novo caminho no documento xml
		XPathFactory xPathFactory = XPathFactory.newInstance();
		
		//Instância que receberá os caminhos do xml
		XPath xpath = xPathFactory.newXPath();
		
		//Permite trabalhar com um fragmento do documento (um nó) como um novo documento
		XPathExpression xPathExpr = xpath.compile("/nfeProc/NFe/infNFe/ide/nNF/text()");
		//Evaluate: Provê o acesso ao caminho compilado do XML, e retorna de acordo com o tipo especificado no parâmetro
		Node result =  (Node) xPathExpr.evaluate(doc, XPathConstants.NODE); 	
		nota.setNr_nota(result.getNodeValue());		

		xPathExpr = xpath.compile("/nfeProc/NFe/infNFe/ide/cNF/text()");
		result = (Node) xPathExpr.evaluate(doc, XPathConstants.NODE); 
		nota.setNr_chave(Integer.parseInt(result.getNodeValue()));

		xPathExpr = xpath.compile("/nfeProc/NFe/infNFe/emit/CNPJ/text()");
		result = (Node) xPathExpr.evaluate(doc, XPathConstants.NODE); 
		nota.setCnpj_emitente(result.getNodeValue());

		xPathExpr = xpath.compile("/nfeProc/NFe/infNFe/emit/xNome/text()");
		result = (Node) xPathExpr.evaluate(doc, XPathConstants.NODE); 
		nota.setNome_emitente(result.getNodeValue());

		xPathExpr = xpath.compile("/nfeProc/NFe/infNFe/dest");
		NodeList cpfOuCnpj =  (NodeList) xPathExpr.evaluate(doc, XPathConstants.NODESET);
		nota.setCpf_cnpj_destinatario(cpfOuCnpj.item(0).getChildNodes().item(0).getTextContent());

		xPathExpr = xpath.compile("/nfeProc/NFe/infNFe/dest/xNome/text()");
		result = (Node) xPathExpr.evaluate(doc, XPathConstants.NODE); 
		nota.setNm_destinatario(result.getNodeValue());

		xPathExpr = xpath.compile("/nfeProc/NFe/infNFe/total/ICMSTot/vNF/text()");
		result = (Node) xPathExpr.evaluate(doc, XPathConstants.NODE); 
		nota.setVr_nota(result.getNodeValue());


		xPathExpr = xpath.compile("/nfeProc/NFe/infNFe/cobr/dup");
		NodeList todasDuplicatas =  (NodeList) xPathExpr.evaluate(doc, XPathConstants.NODESET);		
		processaDuplicata(todasDuplicatas, nota);


		return nota;
	}


	public void processaDuplicata(Object todasDuplicatas, Nota nota) { 
		NodeList duplicataNodes = (NodeList) todasDuplicatas;

		for (int i = 0; i < duplicataNodes.getLength() ; i++) { 

			Duplicata duplicata = new Duplicata();			
			duplicata.setDt_vencimento(LocalDate.parse(duplicataNodes.item(i).getChildNodes().item(1).getTextContent()));
			duplicata.setParcela(Integer.parseInt(duplicataNodes.item(i).getChildNodes().item(0).getTextContent()));
			duplicata.setValor(duplicataNodes.item(i).getChildNodes().item(2).getTextContent());
			nota.getDuplicatas().add(duplicata);
		} 
	}


}
