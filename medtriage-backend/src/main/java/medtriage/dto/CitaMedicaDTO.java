package medtriage.dto;

import java.time.LocalDateTime;

public class CitaMedicaDTO {
    private Long id;
    private String codigoCita;
    private String nombrePaciente;
    private String nombreDoctor;
    private Integer nivelPrioridad;
    private String motivoConsulta;
    private String estado;
    private Double montoConsulta;
    private LocalDateTime fechaCita;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(String codigoCita) {
        this.codigoCita = codigoCita;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getNombreDoctor() {
        return nombreDoctor;
    }

    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
    }

    public Integer getNivelPrioridad() {
        return nivelPrioridad;
    }

    public void setNivelPrioridad(Integer nivelPrioridad) {
        this.nivelPrioridad = nivelPrioridad;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getMontoConsulta() {
        return montoConsulta;
    }

    public void setMontoConsulta(Double montoConsulta) {
        this.montoConsulta = montoConsulta;
    }

    public LocalDateTime getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDateTime fechaCita) {
        this.fechaCita = fechaCita;
    }
}