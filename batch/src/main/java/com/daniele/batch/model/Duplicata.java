package com.daniele.batch.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tab_nota_duplicata", schema="dbo")
public class Duplicata {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
	
	private Integer parcela;
	
	private String valor;
	
	private LocalDate dt_vencimento;

	
	
	public int getParcela() {
		return parcela;
	}


	public void setParcela(int parcela) {
		this.parcela = parcela;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Duplicata() {
		
	}
	

	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}


	public LocalDate getDt_vencimento() {
		return dt_vencimento;
	}


	public void setDt_vencimento(LocalDate dt_vencimento) {
		this.dt_vencimento = dt_vencimento;
	}




	
}
