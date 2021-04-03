package com.daniele.batch.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "tab_nota", schema="dbo")
public class Nota {
	
	@Id
	private Integer nr_chave;	

	private String nr_nota;
	
	private String vr_nota;
	
	private String cnpj_emitente;
	
	private String nome_emitente;
	
	private String cpf_cnpj_destinatario;
	
	private String nm_destinatario;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="nr_chave")
	private List<Duplicata> duplicatas = new ArrayList<Duplicata>();
	
	public List<Duplicata> getDuplicatas() {
		return duplicatas;
	}
	public void setDuplicatas(List<Duplicata> duplicatas) {
		this.duplicatas = duplicatas;
	}
	public String getNr_nota() {
		return nr_nota;
	}
	public void setNr_nota(String nr_nota) {
		this.nr_nota = nr_nota;
	}
	public Integer getNr_chave() {
		return nr_chave;
	}
	public void setNr_chave(Integer nr_chave) {
		this.nr_chave = nr_chave;
	}
	public String getVr_nota() {
		return vr_nota;
	}
	public void setVr_nota(String vr_nota) {
		this.vr_nota = vr_nota;
	}
	public String getCnpj_emitente() {
		return cnpj_emitente;
	}
	public void setCnpj_emitente(String cnpj_emitente) {
		this.cnpj_emitente = cnpj_emitente;
	}
	public String getNome_emitente() {
		return nome_emitente;
	}
	public void setNome_emitente(String nome_emitente) {
		this.nome_emitente = nome_emitente;
	}
	public String getCpf_cnpj_destinatario() {
		return cpf_cnpj_destinatario;
	}
	public void setCpf_cnpj_destinatario(String cpf_cnpj_destinatario) {
		this.cpf_cnpj_destinatario = cpf_cnpj_destinatario;
	}
	public String getNm_destinatario() {
		return nm_destinatario;
	}
	public void setNm_destinatario(String nm_destinatario) {
		this.nm_destinatario = nm_destinatario;
	}
	
	
	//private List<Duplicata> duplicatas = new ArrayList<Duplicata>();
	
	
	
	
}
