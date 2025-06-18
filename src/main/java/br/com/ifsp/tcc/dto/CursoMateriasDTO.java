package br.com.ifsp.tcc.dto;

import java.util.List;

import br.com.ifsp.tcc.model.Curso;
import br.com.ifsp.tcc.model.Materia;

public class CursoMateriasDTO {
	private Curso curso;
	private List<Materia> materias;

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

}