package br.com.ifsp.tcc.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ifsp.tcc.model.Instituicao;
import br.com.ifsp.tcc.model.Laboratorio;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {
    List<Laboratorio> findByInstituicao(Instituicao instituicao);

    @Query("""
            SELECT l 
            FROM Laboratorio l 
            WHERE l NOT IN (
                SELECT ar.laboratorio 
                FROM AulaRegistrada ar 
                WHERE ar.dia = :dia AND (ar.horaInicio < :horaFim AND ar.horaFim > :horaInicio) AND (ar.flg_status IN ('A'))
            )
        """)
    List<Laboratorio> findLaboratoriosDisponiveis(@Param("dia") LocalDate dia, @Param("horaInicio") LocalTime horaInicio, @Param("horaFim") LocalTime horaFim);

    @Modifying
    @Query("""
        UPDATE AulaRegistrada ar SET ar.flg_status = 'N'
        WHERE ar.laboratorio IN (
            SELECT ar2.laboratorio
            FROM AulaRegistrada ar2
            WHERE ar2.dia = :dia
              AND (ar2.horaInicio < :horaFim AND ar2.horaFim > :horaInicio)
              AND (ar2.flg_status IN ('P'))
        )
    """)
    int atualizaAulasLabsConflito(@Param("dia") LocalDate dia, @Param("horaInicio") LocalTime horaInicio, @Param("horaFim") LocalTime horaFim);

}