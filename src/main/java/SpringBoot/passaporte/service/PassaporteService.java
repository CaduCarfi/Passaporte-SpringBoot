package SpringBoot.passaporte.service;

import SpringBoot.passaporte.repository.PassaporteRepository;
import SpringBoot.passaporte.repository.PessoaRepository;
import org.springframework.stereotype.Service;

@Service
public class PassaporteService {

    private final PassaporteRepository passaporteRepository;
    private final PessoaRepository pessoaRepository;

    public PassaporteService(PassaporteRepository passaporteRepository, PessoaRepository pessoaRepository) {
        this.passaporteRepository = passaporteRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public
}
