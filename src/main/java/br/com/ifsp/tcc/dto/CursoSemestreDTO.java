package br.com.ifsp.tcc.dto;

import java.util.List;

import br.com.ifsp.tcc.model.Curso;
import br.com.ifsp.tcc.model.Semestre;

public class CursoSemestreDTO {
	List<Curso> cursos;
	List<Semestre> semestres;

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<Semestre> getSemestres() {
		return semestres;
	}

	public void setSemestres(List<Semestre> semestres) {
		this.semestres = semestres;
	}

}
