package br.com.ifsp.tcc.repository;

import br.com.ifsp.tcc.model.Aplicativo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AplicativoRepository extends JpaRepository<Aplicativo,Long> {
}
