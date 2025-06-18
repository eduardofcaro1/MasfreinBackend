package br.com.ifsp.tcc.dto;

import br.com.ifsp.tcc.model.Instituicao;

public class UsuarioLogadoDto {
	private Long id;
	private String dscUsuario;
	private String senhaUsuario;
	private String flgAtivo;
	private String celular;
	private Instituicao instituicao;
	private String token;
	private Integer isAdmin;
	private String nomeUsuario;
	private Integer flgProfessor;
	private Integer flgMobile;

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDscUsuario() {
		return dscUsuario;
	}

	public void setDscUsuario(String dscUsuario) {
		this.dscUsuario = dscUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Integer getFlgProfessor() {
		return flgProfessor;
	}

	public void setFlgProfessor(Integer flgProfessor) {
		this.flgProfessor = flgProfessor;
	}

	public Integer getFlgMobile() {
		return flgMobile;
	}

	public void setFlgMobile(Integer flgMobile) {
		this.flgMobile = flgMobile;
	}

}