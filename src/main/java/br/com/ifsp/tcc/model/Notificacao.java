package br.com.ifsp.tcc.model;

import java.time.LocalDateTime;

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
@Table(name = "notificacao")
public class Notificacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "mensagem", nullable = false)
	private String mensagem;

	@Column(name = "data_criacao", nullable = false)
	private LocalDateTime dataCriacao;

	@Column(name = "visualizado", nullable = false)
	private boolean visualizado = false;

	@ManyToOne
	@JoinColumn(name = "aula_id", foreignKey = @ForeignKey(name = "fk_notificacao_aula"))
	private AulaRegistrada aula;

	@ManyToOne
	@JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "fk_notificacao_usuario"))
	private UsuarioDashboard usuario; // destinatário

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public boolean isVisualizado() {
		return visualizado;
	}

	public void setVisualizado(boolean visualizado) {
		this.visualizado = visualizado;
	}

	public AulaRegistrada getAula() {
		return aula;
	}

	public void setAula(AulaRegistrada aula) {
		this.aula = aula;
	}

	public UsuarioDashboard getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDashboard usuario) {
		this.usuario = usuario;
	}

}