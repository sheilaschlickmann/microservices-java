package com.unibave.filaocorrencia.service;

import com.unibave.filaocorrencia.model.OcorrenciaModel;
import com.unibave.filaocorrencia.repository.OcorrenciaRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.validator.GenericValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

    public List<OcorrenciaModel> findAll() {
        return ocorrenciaRepository.findAll();
    }

    public Optional<OcorrenciaModel> findById(Long id) {
        return ocorrenciaRepository.findById(id);
    }

    public OcorrenciaModel updateOcorrenciaByFields(Long id, Map<String, Object> fields) {
        Optional<OcorrenciaModel> existingOcorrencia = ocorrenciaRepository.findById(id);

        if (existingOcorrencia.isPresent()) {
            fields.forEach((key, value) -> {
                System.out.println("key " + key + " value " + value);
                Field field = ReflectionUtils.findField(OcorrenciaModel.class, key);
                field.setAccessible(true);
                System.out.println("passei");
                System.out.println("field " + field);
                System.out.println("existingOcorrencia.get(), " + existingOcorrencia.get());
                System.out.println("value " + value);
                if (GenericValidator.isDate(value.toString(), "yyyy-MM-dd", true)){
                    SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
                    try {
                        Date date = formatter.parse((String) value);
                        ReflectionUtils.setField(field, existingOcorrencia.get(), date);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    ReflectionUtils.setField(field, existingOcorrencia.get(), value);
                }
            });
            return ocorrenciaRepository.save(existingOcorrencia.get());
        }
        return null;
    }
}

