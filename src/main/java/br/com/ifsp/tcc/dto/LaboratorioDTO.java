package br.com.ifsp.tcc.dto;

import br.com.ifsp.tcc.model.Aplicativo;
import br.com.ifsp.tcc.model.Instituicao;

import java.util.ArrayList;
import java.util.List;

public class LaboratorioDTO {
	private Long id;
	private String nome;
	private Long instituicaoId;
	private int qtdComputadores;
	private int qtdLugares;
	private List<Aplicativo> aplicativos = new ArrayList<>();
	private Instituicao instituicao;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getInstituicaoId() {
		return instituicaoId;
	}

	public void setInstituicaoId(Long instituicaoId) {
		this.instituicaoId = instituicaoId;
	}

	public int getQtdComputadores() {
		return qtdComputadores;
	}

	public void setQtdComputadores(int qtdComputadores) {
		this.qtdComputadores = qtdComputadores;
	}

	public int getQtdLugares() {
		return qtdLugares;
	}

	public void setQtdLugares(int qtdLugares) {
		this.qtdLugares = qtdLugares;
	}

	public List<Aplicativo> getAplicativos() {
		return aplicativos;
	}

	public void setAplicativos(List<Aplicativo> aplicativos) {
		this.aplicativos = aplicativos;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
}
