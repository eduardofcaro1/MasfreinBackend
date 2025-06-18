package br.com.ifsp.tcc.dto;

public class CursoDTO {
	private Long instituicaoId;
	private Long id;
	private String nome;
	private Integer duracaoSemestres;

	public Long getInstituicaoId() {
		return instituicaoId;
	}

	public void setInstituicaoId(Long instituicaoId) {
		this.instituicaoId = instituicaoId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getDuracaoSemestres() {
		return duracaoSemestres;
	}

	public void setDuracaoSemestres(Integer duracaoSemestres) {
		this.duracaoSemestres = duracaoSemestres;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
