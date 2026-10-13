package medtriage.controller;

import medtriage.dto.CitaMedicaDTO;
import medtriage.dto.CrearCitaDTO;
import medtriage.service.CitaMedicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/citas")
@CrossOrigin(origins = "http://localhost:4200") // Conexión lista con tu frontend en Angular
public class CitaMedicaController {

    @Autowired
    private CitaMedicaService service;

    @GetMapping
    public ResponseEntity<List<CitaMedicaDTO>> obtenerTodas() {
        return ResponseEntity.ok(service.listarCitas());
    }

    @PostMapping
    public ResponseEntity<CitaMedicaDTO> crearCita(@RequestBody CrearCitaDTO dto) {
        return ResponseEntity.ok(service.crearCita(dto));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<CitaMedicaDTO> actualizarEstado(@PathVariable Long id, @RequestParam String nuevoEstado) {
        return ResponseEntity.ok(service.actualizarEstado(id, nuevoEstado));
    }
}