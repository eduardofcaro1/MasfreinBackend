package br.com.ifsp.tcc.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "aula_registrada")
public class AulaRegistrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;

    @Column(name = "dia", nullable = false)
    private LocalDate dia;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fim", nullable = false)
    private LocalTime horaFim;

    @ManyToOne
    @JoinColumn(name = "materia_semestre_id", foreignKey = @ForeignKey(name = "aula_registrada_ibfk_1"))
    private MateriaSemestre materiaSemestre;

    @ManyToOne
    @JoinColumn(name = "laboratorio_id", foreignKey = @ForeignKey(name = "aula_registrada_ibfk_2"))
    private Laboratorio laboratorio;

    @Column(name = "flg_status")
    private String flg_status;

    public AulaRegistrada() {
        super();
    }

    public AulaRegistrada(Long id, String descricao, LocalDate dia, LocalTime horaInicio, LocalTime horaFim, MateriaSemestre materiaSemestre, Laboratorio laboratorio) {
        this.id = id;
        this.descricao = descricao;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.materiaSemestre = materiaSemestre;
        this.laboratorio = laboratorio;
    }

    public static AulaRegistradaBuilder builder() {
        return new AulaRegistradaBuilder();
    }

    public static class AulaRegistradaBuilder {
        private Long id;
        private String descricao;
        private LocalDate dia;
        private LocalTime horaInicio;
        private LocalTime horaFim;
        private MateriaSemestre materiaSemestre;
        private Laboratorio laboratorio;

        private AulaRegistradaBuilder() {
        }

        public AulaRegistradaBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AulaRegistradaBuilder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public AulaRegistradaBuilder dia(LocalDate dia) {
            this.dia = dia;
            return this;
        }

        public AulaRegistradaBuilder horaInicio(LocalTime horaInicio) {
            this.horaInicio = horaInicio;
            return this;
        }

        public AulaRegistradaBuilder horaFim(LocalTime horaFim) {
            this.horaFim = horaFim;
            return this;
        }

        public AulaRegistradaBuilder materiaSemestre(MateriaSemestre materiaSemestre) {
            this.materiaSemestre = materiaSemestre;
            return this;
        }

        public AulaRegistradaBuilder laboratorio(Laboratorio laboratorio) {
            this.laboratorio = laboratorio;
            return this;
        }

        public AulaRegistrada build() {
            return new AulaRegistrada(id, descricao, dia, horaInicio, horaFim, materiaSemestre, laboratorio);
        }
    }

    // Getters and Setters

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

    public MateriaSemestre getMateriaSemestre() {
        return materiaSemestre;
    }

    public void setMateriaSemestre(MateriaSemestre materiaSemestre) {
        this.materiaSemestre = materiaSemestre;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getFlgStatus(){
        return this.flg_status;
    }

    public void setFlgStatus(String flg_status){
        this.flg_status = flg_status;
    }
}
