package br.com.ifsp.tcc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifsp.tcc.model.AlunoMateriaSemestre;

@Repository
public interface AlunoMateriaSemestreRepository extends JpaRepository<AlunoMateriaSemestre, Long> {
    Optional<AlunoMateriaSemestre> findByUsuarioIdAndMateriaSemestreId(Long usuarioId, Long materiaSemestreId);
    AlunoMateriaSemestre findByUsuarioId(Long usuarioId);
    List<AlunoMateriaSemestre> findByMateriaSemestreId(Long materiaSemestreId);
	boolean existsByUsuarioIdAndMateriaSemestreId(Long alunoId, Long materiaSemestreId);
}