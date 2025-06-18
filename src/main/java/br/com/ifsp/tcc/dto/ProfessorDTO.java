package br.com.ifsp.tcc.dto;

public class ProfessorDTO {
	private Long id;
	private String nomeUsuario;
	private String flgAtivo;
	private String celular;
	private boolean isAdmin;
	private boolean flgProfessor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getFlgAtivo() {
		return flgAtivo;
	}

	public void setFlgAtivo(String flgAtivo) {
		this.flgAtivo = flgAtivo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isFlgProfessor() {
		return flgProfessor;
	}

	public void setFlgProfessor(boolean flgProfessor) {
		this.flgProfessor = flgProfessor;
	}

}
