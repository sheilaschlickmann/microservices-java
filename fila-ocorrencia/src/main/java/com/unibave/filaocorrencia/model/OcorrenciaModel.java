package com.unibave.filaocorrencia.model;

import com.unibave.filaocorrencia.enums.GrauUrgenciaType;
import com.unibave.filaocorrencia.enums.SituacaoType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "ocorrencia")
public class OcorrenciaModel extends RepresentationModel<OcorrenciaModel> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private BigDecimal cpf;

    @Column
    private String nome;

    @Column
    private String telefone;

    @Column
    private String localizacao_ocorrencia;

    @Column
    @Enumerated(EnumType.STRING)
    private SituacaoType situacao;

    @Column
    @Enumerated(EnumType.STRING)
    private GrauUrgenciaType grau_urgencia;

    @Column
    private LocalDateTime data_ocorrencia;

    @Column
    private Date data_atendimento;

    @Column
    private Date data_encerramento;

    @Column
    private Long usuario_atendimento;







}
