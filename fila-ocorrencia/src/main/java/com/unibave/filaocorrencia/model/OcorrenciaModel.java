package com.unibave.filaocorrencia.model;

import com.unibave.filaocorrencia.enums.GrauUrgenciaType;
import com.unibave.filaocorrencia.enums.SituacaoType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "ocorrencia")
public class OcorrenciaModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private int cpf;

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
    private int usuario_atendimento;







}
