package br.com.ifsp.tcc.dto;

public class MateriaDTO {
	private Long id;
	private String nome;
	private String sigla;
	private Integer qtdAulas;
	private Integer cursoId;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Integer getQtdAulas() {
		return qtdAulas;
	}

	public void setQtdAulas(Integer qtdAulas) {
		this.qtdAulas = qtdAulas;
	}

	public Integer getCursoId() {
		return cursoId;
	}

	public void setCursoId(Integer cursoId) {
		this.cursoId = cursoId;
	}

	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

}
