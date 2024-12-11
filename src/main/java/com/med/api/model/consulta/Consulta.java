package com.med.api.model.consulta;

import com.med.api.model.medico.Medico;
import com.med.api.model.paciente.Paciente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "consulta")
@Entity(name = "consulta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne (fetch = FetchType.LAZY) @JoinColumn(name = "medico_id")
    private Medico medico;
    @ManyToOne (fetch = FetchType.LAZY) @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    private LocalDateTime fecha;
    @Enumerated(EnumType.STRING) @Column(name = "motivo_cancelamiento")
    private MotivoCancelamiento motivoCancelamiento;

    public Consulta(Long id, Medico medico, Paciente paciente, LocalDateTime proximoLunes) {
    }

    public void cancelar(MotivoCancelamiento motivo) {
        this.motivoCancelamiento = motivo;
    }
}
