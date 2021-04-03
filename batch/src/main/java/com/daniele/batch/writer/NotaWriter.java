package com.daniele.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daniele.batch.model.Nota;
import com.daniele.batch.repository.NotaRepository;

@Component
public class NotaWriter implements ItemWriter<Nota> {
	
	@Autowired
	private NotaRepository notaRepository;
	
	@Override
	public void write(List<? extends Nota> items) throws Exception {			
		for (Nota nota : items) {	
			notaRepository.save(nota);

		}
	}
}



/*
			writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());		
			writer.setSql("INSERT INTO tab_nota(nr_nota,nr_chave,vr_nota,cnpj_emitente,nome_emitente, cpf_cnpj_destinatario,nm_destinatario) VALUES"
						+ " (:nr_nota, :nr_chave, :vr_nota, :cnpj_emitente, :nome_emitente, :cpf_cnpj_destinatario, :nm_destinatario)");		
			writer.setDataSource(dataSource);			
			System.out.println("Gravando nota");
			
					System.out.println("Chave:  " + duplicata.getChave_nf());
					System.out.println("Parcela: " + duplicata.getParcela());
					System.out.println("Vencimento: " + duplicata.getDt_vencimento());
					System.out.println("Valor: " + duplicata.getValor());
*/