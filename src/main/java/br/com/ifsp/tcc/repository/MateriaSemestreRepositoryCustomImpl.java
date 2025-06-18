package br.com.ifsp.tcc.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ifsp.tcc.dto.MateriaSemestreRetorno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class MateriaSemestreRepositoryCustomImpl implements MateriaSemestreRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MateriaSemestreRetorno> findBySemestreIdAndCursoId(Long semestreId, Long cursoId) {
        String sql = "SELECT a.id, a.semestre_id, a.materia_id, a.usuario_id, b.nome, b.curso_id " +
                     "FROM materia_semestre a " +
                     "JOIN materia b ON a.materia_id = b.id " +
                     "WHERE a.semestre_id = :semestreId AND b.curso_id = :cursoId";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("semestreId", semestreId);
        query.setParameter("cursoId", cursoId);

        List<Object[]> results = query.getResultList();
        List<MateriaSemestreRetorno> dtos = new ArrayList<>();
        
        for (Object[] result : results) {
        	MateriaSemestreRetorno dto = new MateriaSemestreRetorno(
                ((Number) result[0]).longValue(),
                ((Number) result[1]).longValue(),
                ((Number) result[2]).longValue(),
                ((Number) result[3]).longValue(),
                (String) result[4],
                ((Number) result[5]).longValue()
            );
            dtos.add(dto);
        }
        
        return dtos;
    }
}
