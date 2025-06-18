package br.com.ifsp.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifsp.tcc.model.VwMateriasAluno;

@Repository
public interface VwMateriasAlunoRepository extends JpaRepository<VwMateriasAluno, Long> {
    List<VwMateriasAluno> findBySemestreIdAndUsuarioId(Long semestreId, Long usuarioId);
	List<VwMateriasAluno> findByUsuarioId(Long usuarioId);
	List<VwMateriasAluno> findByMateriaSemestreId(long materiaSemestreId);
}