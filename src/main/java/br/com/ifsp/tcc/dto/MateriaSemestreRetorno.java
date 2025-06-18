package br.com.ifsp.tcc.dto;

public class MateriaSemestreRetorno {

	private Long id;
	private Long semestreId;
	private Long materiaId;
	private Long usuarioId;
	private String nomeMateria;
	private Long cursoId;

	public MateriaSemestreRetorno(Long id, Long semestreId, Long materiaId, Long usuarioId, String nomeMateria,
			Long cursoId) {
		super();
		this.id = id;
		this.semestreId = semestreId;
		this.materiaId = materiaId;
		this.usuarioId = usuarioId;
		this.nomeMateria = nomeMateria;
		this.cursoId = cursoId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSemestreId() {
		return semestreId;
	}

	public void setSemestreId(Long semestreId) {
		this.semestreId = semestreId;
	}

	public Long getMateriaId() {
		return materiaId;
	}

	public void setMateriaId(Long materiaId) {
		this.materiaId = materiaId;
	}

	public String getNomeMateria() {
		return nomeMateria;
	}

	public void setNomeMateria(String nomeMateria) {
		this.nomeMateria = nomeMateria;
	}

	public Long getCursoId() {
		return cursoId;
	}

	public void setCursoId(Long cursoId) {
		this.cursoId = cursoId;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

}
