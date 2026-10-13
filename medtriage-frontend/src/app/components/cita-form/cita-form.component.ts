import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CitaService } from '../../services/cita.service';
import { CrearCitaPayload } from '../../models/cita.model';

@Component({
  selector: 'app-cita-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './cita-form.component.html',
  styleUrls: ['./cita-form.component.css']
})
export class CitaFormComponent {
  private citaService = inject(CitaService);
  private router = inject(Router);
  form: any;

  private obtenerFechaActualISO(): string {
    const ahora = new Date();
    const anio = ahora.getFullYear();
    const mes = String(ahora.getMonth() + 1).padStart(2, '0');
    const dia = String(ahora.getDate()).padStart(2, '0');
    const horas = String(ahora.getHours()).padStart(2, '0');
    const minutos = String(ahora.getMinutes()).padStart(2, '0');
    const segundos = String(ahora.getSeconds()).padStart(2, '0');
    return `${anio}-${mes}-${dia}T${horas}:${minutos}:${segundos}`;
  }

  payload: CrearCitaPayload = {
    doctorId: 1, 
    pacienteId: 1, 
    nivelPrioridad: 1,
    motivoConsulta: '',
    montoConsulta: 25000,
    fechaCita: this.obtenerFechaActualISO()
  };

  guardarCita() {
  if (this.form.invalid) return;

  this.citaService.crearCita(this.form.value).subscribe({
    next: (response) => {
      console.log('Cita guardada con éxito:', response);
      // Redirige al listado para que se vea reflejada inmediatamente
      this.router.navigate(['/citas']);
    },
    error: (err) => {
      console.error('Error detallado del backend:', err);
      alert('Error al registrar la cita: ' + (err.error?.detail || 'Verifique los datos'));
    }
  });
}
}