package br.com.ifsp.tcc.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vw_aulas_dia")
public class VwAulasDia {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "dia")
	private LocalDate dia;

	@Column(name = "hora_inicio")
	private LocalTime horaInicio;

	@Column(name = "hora_fim")
	private LocalTime horaFim;

	@Column(name = "materia_semestre_id")
	private Long materiaSemestreId;

	@Column(name = "laboratorio_id")
	private Long laboratorioId;

	@Column(name = "lab_nome")
	private String labNome;

	@Column(name = "semestre_descricao")
	private String semestreDescricao;

	@Column(name = "semestre_id")
	private Long semestreId;

	@Column(name = "materia_nome")
	private String materiaNome;

	@Column(name = "materia_id")
	private Long materiaId;

	@Column(name = "professor_id")
	private Long professorId;

	@Column(name = "professor_nome")
	private String professorNome;

	@Column(name = "data_inicio")
	private LocalDate dataInicio;

	@Column(name = "data_fim")
	private LocalDate dataFim;

	@Column(name = "instituicao_id")
	private Long instituicaoId;

	@Column(name = "numero_modulo")
	private Long numeroModulo;

	@Column(name = "curso_id")
	private Long cursoId;

	@Column(name = "nome_curso")
	private String nomeCurso;

	@Column(name= "flg_status")
	private String flgSatus;

	public Long getNumeroModulo() {
		return numeroModulo;
	}

	public void setNumeroModulo(Long numeroModulo) {
		this.numeroModulo = numeroModulo;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(LocalTime horaFim) {
		this.horaFim = horaFim;
	}

	public String getLabNome() {
		return labNome;
	}

	public void setLabNome(String labNome) {
		this.labNome = labNome;
	}

	public Long getMateriaId() {
		return materiaId;
	}

	public void setMateriaId(Long materiaId) {
		this.materiaId = materiaId;
	}

	public String getMateriaNome() {
		return materiaNome;
	}

	public void setMateriaNome(String materiaNome) {
		this.materiaNome = materiaNome;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public Long getInstituicaoId() {
		return instituicaoId;
	}

	public void setInstituicaoId(Long instituicaoId) {
		this.instituicaoId = instituicaoId;
	}

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}

	public String getProfessorNome() {
		return professorNome;
	}

	public void setProfessorNome(String professorNome) {
		this.professorNome = professorNome;
	}

	public Long getMateriaSemestreId() {
		return materiaSemestreId;
	}

	public void setMateriaSemestreId(Long materiaSemestreId) {
		this.materiaSemestreId = materiaSemestreId;
	}

	public Long getLaboratorioId() {
		return laboratorioId;
	}

	public void setLaboratorioId(Long laboratorioId) {
		this.laboratorioId = laboratorioId;
	}

	public String getSemestreDescricao() {
		return semestreDescricao;
	}

	public void setSemestreDescricao(String semestreDescricao) {
		this.semestreDescricao = semestreDescricao;
	}

	public Long getSemestreId() {
		return semestreId;
	}

	public void setSemestreId(Long semestreId) {
		this.semestreId = semestreId;
	}

	public void setFlgStatus(String flgStatus){
		this.flgSatus = flgStatus;
	}

	public String  getflgStatus(){
		return this.flgSatus;
	}
}
