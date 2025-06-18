package br.com.ifsp.tcc.model;

import java.time.LocalDate;

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
@Table(name = "matricula_curso")
public class MatriculaCurso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "fk_matricula_usuario"))
	private UsuarioDashboard usuario;

	@ManyToOne
	@JoinColumn(name = "curso_id", nullable = false, foreignKey = @ForeignKey(name = "fk_matricula_curso"))
	private Curso curso;

	@Column(name = "data_matricula", nullable = false)
	private LocalDate dataMatricula;

	@Column(name = "data_fim_matricula")
	private LocalDate dataFimMatricula;

	@Column(name = "status_matricula", nullable = false, length = 1)
	private char statusMatricula = 'A'; // A=Ativo, T=Trancado, C=Concluído, X=Cancelado

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UsuarioDashboard getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDashboard usuario) {
		this.usuario = usuario;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public LocalDate getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(LocalDate dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public LocalDate getDataFimMatricula() {
		return dataFimMatricula;
	}

	public void setDataFimMatricula(LocalDate dataFimMatricula) {
		this.dataFimMatricula = dataFimMatricula;
	}

	public char getStatusMatricula() {
		return statusMatricula;
	}

	public void setStatusMatricula(char statusMatricula) {
		this.statusMatricula = statusMatricula;
	}
}