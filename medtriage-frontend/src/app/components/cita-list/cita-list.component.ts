import { Component, OnInit, signal, computed, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CitaService } from '../../services/cita.service';
import { CitaMedica } from '../../models/cita.model';

@Component({
  selector: 'app-cita-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './cita-list.component.html',
  styleUrls: ['./cita-list.component.css']
})
export class CitaListComponent implements OnInit {
  private citaService = inject(CitaService);

  citas = signal<CitaMedica[]>([]);
  filtroPrioridad = signal<string | number>('TODAS');

  citasFiltradas = computed(() => {
    const lista = this.citas();
    const prioridad = this.filtroPrioridad();
    if (prioridad === 'TODAS') return lista;
    return lista.filter(c => c.nivelPrioridad === prioridad);
  });

  ngOnInit(): void {
    this.cargarCitas();
  }

  cargarCitas(): void {
    this.citaService.getCitas().subscribe({
      next: (data) => this.citas.set(data),
      error: (err) => console.error('Error al cargar citas', err)
    });
  }

  cambiarFiltro(prioridad: string | number): void {
    this.filtroPrioridad.set(prioridad);
  }

  avanzarEstado(cita: CitaMedica): void {
    let siguienteEstado = 'EN_ATENCION';
    if (cita.estado === 'PENDIENTE') siguienteEstado = 'EN_ATENCION';
    else if (cita.estado === 'EN_ATENCION') siguienteEstado = 'ATENDIDA';
    else return;

    this.citaService.actualizarEstado(cita.id, siguienteEstado).subscribe({
      next: () => this.cargarCitas(),
      error: (err) => console.error('Error al actualizar estado', err)
    });
  }
}