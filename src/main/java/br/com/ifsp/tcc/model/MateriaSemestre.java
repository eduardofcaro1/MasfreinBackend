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
@Table(name = "materia_semestre")
public class MateriaSemestre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "materia_id", foreignKey = @ForeignKey(name = "materia_semestre_ibfk_1"))
	private Materia materia;

	@ManyToOne
	@JoinColumn(name = "semestre_id", foreignKey = @ForeignKey(name = "materia_semestre_ibfk_3"))
	private Semestre semestre;

	@ManyToOne
	@JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "materia_semestre_ibfk_2"))
	private UsuarioDashboard usuario;

	@Column(name = "numero_modulo")
	private Integer numeroModulo;

	public MateriaSemestre() {
		super();
	}

	public MateriaSemestre(Long id, Materia materia, Semestre semestre, UsuarioDashboard usuario,
			Integer numeroModulo) {
		super();
		this.id = id;
		this.materia = materia;
		this.semestre = semestre;
		this.usuario = usuario;
		this.numeroModulo = numeroModulo;
	}

	public static MateriaSemestreBuilder builder() {
		return new MateriaSemestreBuilder();
	}

	public static class MateriaSemestreBuilder {
		private Long id;
		private Materia materia;
		private Semestre semestre;
		private UsuarioDashboard usuario;
		private Integer numeroModulo;

		private MateriaSemestreBuilder() {
		}

		public MateriaSemestreBuilder id(Long id) {
			this.id = id;
			return this;
		}

		public MateriaSemestreBuilder materia(Materia materia) {
			this.materia = materia;
			return this;
		}

		public MateriaSemestreBuilder semestre(Semestre semestre) {
			this.semestre = semestre;
			return this;
		}

		public MateriaSemestreBuilder usuario(UsuarioDashboard usuario) {
			this.usuario = usuario;
			return this;
		}

		public MateriaSemestreBuilder numeroModulo(Integer numeroModulo) {
			this.numeroModulo = numeroModulo;
			return this;
		}

		public MateriaSemestre build() {
			return new MateriaSemestre(id, materia, semestre, usuario, numeroModulo);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}

	public UsuarioDashboard getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDashboard usuario) {
		this.usuario = usuario;
	}

	public Integer getNumeroModulo() {
		return numeroModulo;
	}

	public void setNumeroModulo(Integer numeroModulo) {
		this.numeroModulo = numeroModulo;
	}

}
