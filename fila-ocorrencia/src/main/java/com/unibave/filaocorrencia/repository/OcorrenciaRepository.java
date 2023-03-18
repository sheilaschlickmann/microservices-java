package com.unibave.filaocorrencia.repository;

import com.unibave.filaocorrencia.model.OcorrenciaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OcorrenciaRepository extends JpaRepository<OcorrenciaModel, Long> {


}
