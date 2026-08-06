package SpringBoot.passaporte.controller;

import SpringBoot.passaporte.dto.Passaporte.PassaporteRequestDTO;
import SpringBoot.passaporte.dto.Passaporte.PassaporteResponseDTO;
import SpringBoot.passaporte.service.PassaporteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passaporte")
public class PassaporteController {

    private final PassaporteService passaporteService;

    public PassaporteController(PassaporteService passaporteService) {
        this.passaporteService = passaporteService;
    }

    @PostMapping
    public ResponseEntity<PassaporteResponseDTO> criar(@RequestBody @Valid PassaporteRequestDTO dto) {
        PassaporteResponseDTO passaporte = passaporteService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(passaporte);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassaporteResponseDTO> buscar(@PathVariable Long id) {
        PassaporteResponseDTO passaporte = passaporteService.findById(id);
        return ResponseEntity.ok(passaporte);
    }

    @GetMapping
    public ResponseEntity<List<PassaporteResponseDTO>> listar() {
        List<PassaporteResponseDTO> passaportes = passaporteService.findAll();
        return ResponseEntity.ok(passaportes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassaporteResponseDTO> atualizar(@PathVariable Long id,
                                                           @RequestBody @Valid PassaporteRequestDTO dto) {
        PassaporteResponseDTO passaporte = passaporteService.atualizar(id, dto);
        return ResponseEntity.ok(passaporte);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        passaporteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}