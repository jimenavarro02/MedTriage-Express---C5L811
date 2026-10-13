export interface Doctor {
  id: number;
  nombre: string;
  especialidad: string;
  disponible: boolean;
}

export interface Paciente {
  id: number;
  identificacion: string;
  nombreCompleto: string;
  correo: string;
  telefono: string;
}

export interface CitaMedica {
  id: number;
  codigoCita: string;
  nombrePaciente: string;
  nombreDoctor: string;
  nivelPrioridad: string;
  motivoConsulta: string;
  estado: string;
  montoConsulta: number;
  fechaCita: string;
}

export interface CrearCitaPayload {
  doctorId: number;
  pacienteId: number;
  nivelPrioridad: number; 
  motivoConsulta: string;
  montoConsulta: number;
  fechaCita: string; 
}