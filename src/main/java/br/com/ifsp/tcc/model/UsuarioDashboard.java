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
@Table(name = "usuario_dashboard")
public class UsuarioDashboard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "dsc_usuario", nullable = false)
	private String dscUsuario;

	@Column(name = "senha_usuario", nullable = false)
	private String senhaUsuario;

	@Column(name = "flg_ativo", nullable = false, length = 1)
	private String flgAtivo;

	@Column(name = "celular", length = 14)
	private String celular;

	@Column(name = "nome_usuario")
	private String nomeUsuario;

	@ManyToOne
	@JoinColumn(name = "instituicao_id", foreignKey = @ForeignKey(name = "usuario_dashboard_ibfk_1"))
	private Instituicao instituicao;

	@Column(name = "is_admin")
	private Integer isAdmin;

	@Column(name = "flg_professor")
	private Integer flgProfessor;

	@Column(name = "flg_mobile")
	private Integer flgMobile;

	public UsuarioDashboard() {
		super();
	}

	public UsuarioDashboard(Long id, String dscUsuario, String senhaUsuario, String flgAtivo, String celular,
			String nomeUsuario, Instituicao instituicao, Integer isAdmin, Integer flgProfessor, Integer flgMobile) {
		super();
		this.id = id;
		this.dscUsuario = dscUsuario;
		this.senhaUsuario = senhaUsuario;
		this.flgAtivo = flgAtivo;
		this.celular = celular;
		this.instituicao = instituicao;
		this.isAdmin = isAdmin;
		this.nomeUsuario = nomeUsuario;
		this.flgProfessor = flgProfessor;
		this.flgMobile = flgMobile;
	}

	public static UserBuilder builder() {
		return new UserBuilder();
	}

	public static class UserBuilder {
		private Long id;
		private String dscUsuario;
		private String senhaUsuario;
		private String flgAtivo;
		private String celular;
		private String nomeUsuario;
		private Instituicao instituicao;
		private Integer isAdmin;
		private Integer flgProfessor;
		private Integer flgMobile;

		private UserBuilder() {
		}

		public UserBuilder id(Long id) {
			this.id = id;
			return this;
		}

		public UserBuilder dscUsuario(String dscUsuario) {
			this.dscUsuario = dscUsuario;
			return this;
		}

		public UserBuilder senhaUsuario(String senhaUsuario) {
			this.senhaUsuario = senhaUsuario;
			return this;
		}

		public UserBuilder flgAtivo(String flgAtivo) {
			this.flgAtivo = flgAtivo;
			return this;
		}

		public UserBuilder celular(String celular) {
			this.celular = celular;
			return this;
		}

		public UserBuilder instituicao(Instituicao instituicao) {
			this.instituicao = instituicao;
			return this;
		}

		public UserBuilder isAdmin(Integer isAdmin) {
			this.isAdmin = isAdmin;
			return this;
		}

		public UserBuilder nomeUsuario(String nomeUsuario) {
			this.nomeUsuario = nomeUsuario;
			return this;
		}

		public UserBuilder flgProfessor(Integer flgProfessor) {
			this.flgProfessor = flgProfessor;
			return this;
		}

		public UserBuilder flgMobile(Integer flgMobile) {
			this.flgMobile = flgMobile;
			return this;
		}

		public UsuarioDashboard build() {
			return new UsuarioDashboard(id, dscUsuario, senhaUsuario, flgAtivo, celular, nomeUsuario, instituicao,
					isAdmin, flgProfessor, flgMobile);
		}
	}

	// Getters and Setters

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

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
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
