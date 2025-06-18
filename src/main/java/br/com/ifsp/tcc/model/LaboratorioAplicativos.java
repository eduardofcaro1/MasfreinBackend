package br.com.ifsp.tcc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "laboratorio_aplicativos")
public class LaboratorioAplicativos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "laboratorio_id")
    private Long laboratorioId;

    @Column(name = "aplicativo_id")
    private Long aplicativo_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getlaboratorioId() {
        return laboratorioId;
    }

    public void setlaboratorioId(Long laboratorioId) {
        this.laboratorioId = laboratorioId;
    }

    public Long getAplicativo_id() {
        return aplicativo_id;
    }

    public void setAplicativo_id(Long aplicativo_id) {
        this.aplicativo_id = aplicativo_id;
    }
}
