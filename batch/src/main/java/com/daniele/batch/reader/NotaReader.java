package com.daniele.batch.reader;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class NotaReader implements ItemReader<String> { 

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		File diretorio = new File("C:\\Arquivos\\");
		
		
		for (String path: diretorio.list()) {
			
			File arquivo = new File(path);
			
			Files.move(Paths.get("C:\\Arquivos\\"+arquivo.getName()), 
						Paths.get("C:\\ArquivoProcessado\\"+arquivo.getName()), 
						StandardCopyOption.REPLACE_EXISTING);
			return arquivo.getName();
		}
		
		return null;
	}

	

}

/*
	LinkedList<File> lista = new LinkedList<>();

	
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		System.out.println("PASSANDO PELO READER ....");
		File diretorio = new File("C:\\Arquivos\\");
		if (!diretorio.exists()) {
			throw new BatchRuntimeException("Diretório inválido");
		}
		
		for (String path : diretorio.list() ) {
			File arquivo = new File(path);
			if(!lista.contains(arquivo)) {
			lista.add(arquivo);			
			return arquivo.getName();
			} else {
				System.out.println("Já está na lista");
			}
		}
		
		return null;
	}
	

*/