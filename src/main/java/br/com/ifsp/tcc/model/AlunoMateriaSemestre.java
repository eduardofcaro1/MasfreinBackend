package br.com.ifsp.tcc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "aluno_materia_semestre")
public class AlunoMateriaSemestre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "materia_semestre_id", nullable = false)
    private Long materiaSemestreId;

    @Column(name = "flg_aceito", nullable = false, length = 1)
    private String flgAceito;

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
}