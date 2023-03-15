package com.unibave.filaocorrencia.service;

import com.unibave.filaocorrencia.model.OcorrenciaModel;
import com.unibave.filaocorrencia.repository.OcorrenciaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Service
public class OcorrenciaService {

    final OcorrenciaRepository ocorrenciaRepository;


    public OcorrenciaService(OcorrenciaRepository ocorrenciaRepository) {
        this.ocorrenciaRepository = ocorrenciaRepository;
    }

    @Transactional
    public OcorrenciaModel save(OcorrenciaModel ocorrenciaModel) {
        return ocorrenciaRepository.save(ocorrenciaModel);
    }

    public Page<OcorrenciaModel> findAll(Pageable pageable) {
        return ocorrenciaRepository.findAll(pageable);
    }

    public Optional<OcorrenciaModel> findById(Long id) {
        return ocorrenciaRepository.findById(id);
    }

    public OcorrenciaModel updateOcorrenciaByFields(Long id, Map<String, Object> fields) {
        Optional<OcorrenciaModel> existingOcorrencia = ocorrenciaRepository.findById(id);

        if (existingOcorrencia.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(OcorrenciaModel.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingOcorrencia.get(), value);
            });
            return ocorrenciaRepository.save(existingOcorrencia.get());
        }
        return null;
    }
}

