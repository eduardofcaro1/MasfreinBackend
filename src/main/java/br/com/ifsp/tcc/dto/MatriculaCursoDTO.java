package br.com.ifsp.tcc.dto;

import java.time.LocalDate;

import br.com.ifsp.tcc.model.Curso;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class MatriculaCursoDTO {
	private Integer id;
	private Integer usuarioId;
	private String dscUsuario;
	private String nomeUsuario;
	private String celular;
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

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
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

	public String getDscUsuario() {
		return dscUsuario;
	}

	public void setDscUsuario(String dscUsuario) {
		this.dscUsuario = dscUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public MatriculaCursoDTO(Integer id, Integer usuarioId, Curso curso, LocalDate dataMatricula,
			LocalDate dataFimMatricula, char statusMatricula) {
		super();
		this.id = id;
		this.usuarioId = usuarioId;
		this.curso = curso;
		this.dataMatricula = dataMatricula;
		this.dataFimMatricula = dataFimMatricula;
		this.statusMatricula = statusMatricula;
	}

	public MatriculaCursoDTO() {
		super();
	}

}
