package br.com.ifsp.tcc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "curso")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false, length = 100)
	private String nome;

	@Column(name = "duracao_semestres")
	private Integer duracaoSemestres;

	@ManyToOne
	@JoinColumn(name = "instituicao_id", foreignKey = @ForeignKey(name = "curso_ibfk_1"), nullable = true)
	private Instituicao instituicao;

	public Curso() {
		super();
	}

	public Curso(Long id, String nome, Integer duracaoSemestres, Instituicao instituicao) {
		super();
		this.id = id;
		this.nome = nome;
		this.duracaoSemestres = duracaoSemestres;
		this.instituicao = instituicao;
	}

	public static CursoBuilder builder() {
		return new CursoBuilder();
	}

	public static class CursoBuilder {
		private Long id;
		private String nome;
		private Integer duracaoSemestres;
		private Instituicao instituicao;

		private CursoBuilder() {
		}

		public CursoBuilder id(Long id) {
			this.id = id;
			return this;
		}

		public CursoBuilder nome(String nome) {
			this.nome = nome;
			return this;
		}

		public CursoBuilder duracaoSemestres(Integer duracaoSemestres) {
			this.duracaoSemestres = duracaoSemestres;
			return this;
		}

		public CursoBuilder instituicao(Instituicao instituicao) {
			this.instituicao = instituicao;
			return this;
		}

		public Curso build() {
			return new Curso(id, nome, duracaoSemestres, instituicao);
		}
	}

	// Getters and Setters

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

	public Integer getDuracaoSemestres() {
		return duracaoSemestres;
	}

	public void setDuracaoSemestres(Integer duracaoSemestres) {
		this.duracaoSemestres = duracaoSemestres;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
}
