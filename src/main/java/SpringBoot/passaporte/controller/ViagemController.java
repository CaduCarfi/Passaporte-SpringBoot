package SpringBoot.passaporte.controller;

import SpringBoot.passaporte.dto.Viagem.ViagemRequestDTO;
import SpringBoot.passaporte.dto.Viagem.ViagemResponseDTO;
import SpringBoot.passaporte.service.ViagemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viagem")
public class ViagemController {

    private final ViagemService viagemService;

    public ViagemController(ViagemService viagemService) {
        this.viagemService = viagemService;
    }

    @PostMapping
    public ResponseEntity<ViagemResponseDTO> criar(@RequestBody @Valid ViagemRequestDTO dto) {
        ViagemResponseDTO viagem = viagemService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(viagem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViagemResponseDTO> buscar(@PathVariable Long id) {
        ViagemResponseDTO viagem = viagemService.findById(id);
        return ResponseEntity.ok(viagem);
    }

    @GetMapping
    public ResponseEntity<List<ViagemResponseDTO>> listar() {
        List<ViagemResponseDTO> viagem = viagemService.findAll();
        return ResponseEntity.ok(viagem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ViagemResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ViagemRequestDTO dto) {
        ViagemResponseDTO viagem = viagemService.atualizar(id, dto);
        return ResponseEntity.ok(viagem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        viagemService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/destino-mais-visitado")
    public ResponseEntity<TipoDeRetorno> nomeDoMetodo() {
        TipoDeRetorno resultado = viagemService.algumMetodo();
        return ResponseEntity.ok(resultado);
    }

}