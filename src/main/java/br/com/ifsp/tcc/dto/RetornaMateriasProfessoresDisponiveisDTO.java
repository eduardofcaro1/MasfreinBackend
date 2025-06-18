package br.com.ifsp.tcc.dto;

import java.util.List;

import br.com.ifsp.tcc.model.Materia;
import br.com.ifsp.tcc.model.MateriaSemestre;
import br.com.ifsp.tcc.model.UsuarioDashboard;

public class RetornaMateriasProfessoresDisponiveisDTO {
	List<Materia> materias;
	List<UsuarioDashboard> professores;
	List<MateriaSemestre> materiasSemestre;

	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	public List<UsuarioDashboard> getProfessores() {
		return professores;
	}

	public void setProfessores(List<UsuarioDashboard> professores) {
		this.professores = professores;
	}

	public List<MateriaSemestre> getMateriasSemestre() {
		return materiasSemestre;
	}

	public void setMateriasSemestre(List<MateriaSemestre> materiasSemestre) {
		this.materiasSemestre = materiasSemestre;
	}

}
