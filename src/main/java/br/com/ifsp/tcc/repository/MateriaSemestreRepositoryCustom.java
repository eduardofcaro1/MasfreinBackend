package br.com.ifsp.tcc.repository;

import java.util.List;

import br.com.ifsp.tcc.dto.MateriaSemestreRetorno;

public interface MateriaSemestreRepositoryCustom {
    List<MateriaSemestreRetorno> findBySemestreIdAndCursoId(Long semestreId, Long cursoId);
}
