package br.com.ifsp.tcc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "instituicao")
public class Instituicao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "key", nullable = false)
	private String key;

	@Column(name = "flg_ativo")
	private String flgAtivo;

	public Instituicao() {
		super();
	}

	public Instituicao(Long id, String nome, String key, String flgAtivo) {
		super();
		this.id = id;
		this.nome = nome;
		this.key = key;
		this.flgAtivo = flgAtivo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getFlgAtivo() {
		return flgAtivo;
	}

	public void setFlgAtivo(String flgAtivo) {
		this.flgAtivo = flgAtivo;
	}

}
