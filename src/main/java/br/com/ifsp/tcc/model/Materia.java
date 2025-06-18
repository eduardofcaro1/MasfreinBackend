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
@Table(name = "materia")
public class Materia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false, length = 100)
	private String nome;

	@Column(name = "sigla", length = 8)
	private String sigla;

	@Column(name = "qtd_aulas")
	private Integer qtdAulas;

	@ManyToOne
	@JoinColumn(name = "curso_id", foreignKey = @ForeignKey(name = "materia_ibfk_1"))
	private Curso curso;

	public Materia() {
		super();
	}

	public Materia(Long id, String nome, String sigla, Integer qtdAulas, Curso curso) {
		super();
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
		this.qtdAulas = qtdAulas;
		this.curso = curso;
	}

	public static MateriaBuilder builder() {
		return new MateriaBuilder();
	}

	public static class MateriaBuilder {
		private Long id;
		private String nome;
		private String sigla;
		private Integer qtdAulas;
		private Curso curso;

		private MateriaBuilder() {
		}

		public MateriaBuilder id(Long id) {
			this.id = id;
			return this;
		}

		public MateriaBuilder nome(String nome) {
			this.nome = nome;
			return this;
		}

		public MateriaBuilder sigla(String sigla) {
			this.sigla = sigla;
			return this;
		}

		public MateriaBuilder qtdAulas(Integer qtdAulas) {
			this.qtdAulas = qtdAulas;
			return this;
		}

		public MateriaBuilder curso(Curso curso) {
			this.curso = curso;
			return this;
		}

		public Materia build() {
			return new Materia(id, nome, sigla, qtdAulas, curso);
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

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
}
