package br.com.ifsp.tcc.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vw_materias_semestre")
public class VwMateriasSemestre {

    @Id
    private Long id;

    @Column(name = "materia_id")
    private Long materiaId;

    @Column(name = "curso_id")
    private Long curso_id;
    @Column(name = "nome_curso")
    private String nomeCurso;
    private String nome;

    private String sigla;

    @Column(name = "qtd_aulas")
    private Integer quantidadeAulas;

    @Column(name = "numero_modulo")
    private Integer numeroModulo;

    @Column(name = "semestre_id")
    private Long semestreId;

    private String descricao;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "nome_usuario")
    private String nomeUsuario;

    @Column(name = "instituicao_id")
    private Long instituicaoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(Long materiaId) {
        this.materiaId = materiaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Integer getQuantidadeAulas() {
        return quantidadeAulas;
    }

    public void setQuantidadeAulas(Integer quantidadeAulas) {
        this.quantidadeAulas = quantidadeAulas;
    }

    public Integer getNumeroModulo() {
        return numeroModulo;
    }

    public void setNumeroModulo(Integer numeroModulo) {
        this.numeroModulo = numeroModulo;
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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Long getInstituicaoId() {
        return instituicaoId;
    }

    public void setInstituicaoId(Long instituicaoId) {
        this.instituicaoId = instituicaoId;
    }

    public Long getCurso_id() {
        return curso_id;
    }

    public void setCurso_id(Long curso_id) {
        this.curso_id = curso_id;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }
}
