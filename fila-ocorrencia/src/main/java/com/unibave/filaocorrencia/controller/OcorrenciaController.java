package com.unibave.filaocorrencia.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibave.filaocorrencia.controller.converter.MapConverter;
import com.unibave.filaocorrencia.model.OcorrenciaModel;
import com.unibave.filaocorrencia.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/ocorrencia")
public class OcorrenciaController {
    final OcorrenciaService ocorrenciaService;


    public OcorrenciaController(OcorrenciaService ocorrenciaService) {
        this.ocorrenciaService = ocorrenciaService;
    }

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MapConverter mapConverter;


    /*@Autowired
    private RabbitTemplate rabbitTemplate;*/

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> saveOcorrencia(@RequestBody  Map<String, Object> OcorrenciaMap) {

        OcorrenciaModel ocorrenciaConvertida = mapper.convertValue(OcorrenciaMap, OcorrenciaModel.class);
        ocorrenciaConvertida.setData_ocorrencia(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(ocorrenciaService.save(ocorrenciaConvertida));
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<Page<OcorrenciaModel>> getAllOcorrencias(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body((Page<OcorrenciaModel>) mapConverter.toJsonMap(ocorrenciaService.findAll(pageable)));
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneOcorrencia(@PathVariable(value = "id") Long id) {
        Optional<OcorrenciaModel> occorenciaModelOptional = ocorrenciaService.findById(id);
        if (!occorenciaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ocorrencia não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(mapConverter.toJsonMap(occorenciaModelOptional.get()));
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/{id}")
    public OcorrenciaModel updateOcorrenciaFields(@PathVariable Long id,@RequestBody Map<String, Object> fields){
        return ocorrenciaService.updateOcorrenciaByFields(id,fields);
    }
}
