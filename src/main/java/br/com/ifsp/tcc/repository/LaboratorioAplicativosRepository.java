package br.com.ifsp.tcc.repository;

import br.com.ifsp.tcc.model.Laboratorio;
import br.com.ifsp.tcc.model.LaboratorioAplicativos;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LaboratorioAplicativosRepository extends JpaRepository<LaboratorioAplicativos,Long> {
    List<LaboratorioAplicativos> findByLaboratorioId(Long laboratorio_id);
    @Transactional
    void deleteAllByLaboratorioId(Long laboratorio_id);
}
