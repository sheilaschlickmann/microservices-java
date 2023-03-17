package com.unibave.filaocorrencia.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibave.filaocorrencia.controller.converter.MapConverter;
import com.unibave.filaocorrencia.model.OcorrenciaModel;
import com.unibave.filaocorrencia.service.OcorrenciaService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/ocorrencia",produces = { "application/json" }, consumes = { "application/json" })
public class OcorrenciaController {
    final OcorrenciaService ocorrenciaService;


    public OcorrenciaController(OcorrenciaService ocorrenciaService) {
        this.ocorrenciaService = ocorrenciaService;
    }
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MapConverter mapConverter;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> saveOcorrencia(@RequestBody  Map<String, Object> OcorrenciaMap) throws JsonProcessingException {

        OcorrenciaModel ocorrenciaConvertida = mapper.convertValue(OcorrenciaMap, OcorrenciaModel.class);
        ocorrenciaConvertida.setData_ocorrencia(LocalDateTime.now(ZoneId.of("UTC")));
        String rountingKey = "projeto-java";

        String transactionJson = mapper.writeValueAsString(ocorrenciaConvertida);

        Message message = MessageBuilder.withBody(transactionJson.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON).build();

        rabbitTemplate.convertAndSend(rountingKey, message);

        return ResponseEntity.status(HttpStatus.CREATED).body(ocorrenciaService.save(ocorrenciaConvertida));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<OcorrenciaModel>> getAllOcorrencias() {


        List<OcorrenciaModel> ocorrenciaModelList = ocorrenciaService.findAll();

        if (ocorrenciaModelList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            for (OcorrenciaModel ocorrencia : ocorrenciaModelList){
                long id = ocorrencia.getId();
                ocorrencia.add(linkTo(methodOn(OcorrenciaController.class).getOneOcorrencia(id)).withSelfRel());
            }
            return new ResponseEntity<List<OcorrenciaModel>>(ocorrenciaModelList,HttpStatus.OK);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value ="/{id}")
    public ResponseEntity<Object> getOneOcorrencia(@PathVariable(value = "id") Long id) {
        Optional<OcorrenciaModel> ocorrenciaModelOptional = ocorrenciaService.findById(id);
        if (!ocorrenciaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ocorrencia não encontrada.");
        }else {
            ocorrenciaModelOptional.get().add(linkTo(methodOn(OcorrenciaController.class).getAllOcorrencias()).withRel("Lista de Ocorrências"));
            return ResponseEntity.status(HttpStatus.OK).body(mapConverter.toJsonMap(ocorrenciaModelOptional.get()));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping(value = "/{id}")
    public OcorrenciaModel updateOcorrenciaFields(@PathVariable Long id,@RequestBody Map<String, Object> fields){
        return ocorrenciaService.updateOcorrenciaByFields(id,fields);
    }
}
