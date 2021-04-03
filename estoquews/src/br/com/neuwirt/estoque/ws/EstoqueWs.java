package br.com.neuwirt.estoque.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import br.com.neuwirt.estoque.modelo.item.Item;
import br.com.neuwirt.estoque.modelo.item.ItemDao;
import br.com.neuwirt.estoque.modelo.item.ItemValidador;
import br.com.neuwirt.estoque.modelo.usuario.AutorizacaoException;
import br.com.neuwirt.estoque.modelo.usuario.TokenDao;
import br.com.neuwirt.estoque.modelo.usuario.TokenUsuario;


@WebService
@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL, parameterStyle=ParameterStyle.WRAPPED) // configurações default do jax-ws
public class EstoqueWs {


	
	 private ItemDao dao = new ItemDao();

	    @WebMethod(operationName = "todosOsItens")
	    @ResponseWrapper(localName = "itens")
	    @RequestWrapper(localName = "listaItens")
	    @WebResult(name = "item")
	    public List<Item> getItens() {
	        System.out.println("Chamando getItens()");
	        return dao.todosItens();
	    }
	
	    @WebMethod(operationName="CadastrarItem") 
	    public Item cadastrarItem(@WebParam(name="tokenUsuario", header=true) TokenUsuario token, 
	    		@WebParam(name="item") Item item) 
	    				throws AutorizacaoException {

	        System.out.println("Cadastrando " + item + ", " + token);

	        if(! new TokenDao().ehValido(token)) {
	            throw new AutorizacaoException("Autorizacao falhou");
	        }

	        new ItemValidador(item).validate();

	        this.dao.cadastrar(item);
	        return item;
	    }
}

/*
	private ItemDao dao = new ItemDao();


    @WebMethod(operationName = "todosOsItens")
    @WebResult(name = "itens")
    public ListaItens getItens(@WebParam(name="filtros") Filtros filtros){
    	
        System.out.println("Chamando getItens()");
        
        List<Filtro> listaFiltros = filtros.getLista();
        List<Item> lista = dao.todosItens(listaFiltros);
        
        return new ListaItens(lista);
    }
*/