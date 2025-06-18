package br.com.ifsp.tcc.dto;

public class MateriaSemestreDTO {
	private Long materia;
	private Long semestre;
	private Long usuario;
	private Integer numeroModulo;

	public Long getMateria() {
		return materia;
	}

	public void setMateria(Long materia) {
		this.materia = materia;
	}

	public Long getSemestre() {
		return semestre;
	}

	public void setSemestre(Long semestre) {
		this.semestre = semestre;
	}

	public Long getProfessor() {
		return usuario;
	}

	public void setProfessor(Long professor) {
		this.usuario = professor;
	}

	public Integer getNumeroModulo() {
		return numeroModulo;
	}

	public void setNumeroModulo(Integer numeroModulo) {
		this.numeroModulo = numeroModulo;
	}

}
