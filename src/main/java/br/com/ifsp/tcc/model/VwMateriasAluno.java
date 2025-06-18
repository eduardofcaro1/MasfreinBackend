package br.com.ifsp.tcc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vw_materias_aluno")
public class VwMateriasAluno {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "usuario_id")
	private Long usuarioId;

	@Column(name = "materia_semestre_id")
	private Long materiaSemestreId;

	@Column(name = "flg_aceito")
	private String flgAceito;

	@Column(name = "nome_usuario")
	private String nomeUsuario;

	@Column(name = "dsc_usuario")
	private String dscUsuario;

	@Column(name = "nome")
	private String nome;

	@Column(name = "materia_id")
	private Long materiaId;

	@Column(name = "semestre_id")
	private Long semestreId;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "curso_id")
	private Long cursoId;

	@Column(name = "nome_curso")
	private String nomeCurso;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Long getMateriaSemestreId() {
		return materiaSemestreId;
	}

	public void setMateriaSemestreId(Long materiaSemestreId) {
		this.materiaSemestreId = materiaSemestreId;
	}

	public String getFlgAceito() {
		return flgAceito;
	}

	public void setFlgAceito(String flgAceito) {
		this.flgAceito = flgAceito;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getMateriaId() {
		return materiaId;
	}

	public void setMateriaId(Long materiaId) {
		this.materiaId = materiaId;
	}

	public Long getSemestreId() {
		return semestreId;
	}

	public void setSemestreId(Long semestreId) {
		this.semestreId = semestreId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getCursoId() {
		return cursoId;
	}

	public void setCursoId(Long cursoId) {
		this.cursoId = cursoId;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public String getDscUsuario() {
		return dscUsuario;
	}

	public void setDscUsuario(String dscUsuario) {
		this.dscUsuario = dscUsuario;
	}

}