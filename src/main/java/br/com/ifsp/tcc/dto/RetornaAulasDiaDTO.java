package br.com.ifsp.tcc.dto;

import java.util.List;

import br.com.ifsp.tcc.model.Curso;
import br.com.ifsp.tcc.model.UsuarioDashboard;
import br.com.ifsp.tcc.model.VwAulasDia;

public class RetornaAulasDiaDTO {
	private List<VwAulasDia> aulasDia;
	private List<UsuarioDashboard> professores;
	private List<Curso> cursos;

	public List<VwAulasDia> getAulasDia() {
		return aulasDia;
	}

	public void setAulasDia(List<VwAulasDia> aulasDia) {
		this.aulasDia = aulasDia;
	}

	public List<UsuarioDashboard> getProfessores() {
		return professores;
	}

	public void setProfessores(List<UsuarioDashboard> professores) {
		this.professores = professores;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

}
