package br.com.felipesobreira.banco.controller;

import br.com.felipesobreira.banco.exceptions.ResourceNotFoundException;
import br.com.felipesobreira.banco.model.Conta;
import br.com.felipesobreira.banco.repository.ContaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;

    @GetMapping("/conta")
    public List<Conta> retornaTodasAsContas(){
        return contaRepository.findAll();
    }

    @GetMapping("/conta/id")
    public ResponseEntity<Conta> retornaConta(@PathVariable(value = "id") long contaID) throws ResourceNotFoundException{

        Conta contaBanco =  contaRepository.findById(contaID).orElseThrow(()-> new ResourceNotFoundException("Conta não encontrada: " + contaID));

        return ResponseEntity.ok().body(contaBanco);
    }

    @PostMapping("/conta")
    public Conta criarConta(@Valid @RequestBody Conta conta){
        return contaRepository.save(conta);
    }

    @DeleteMapping("/conta/{id}")
    public Map<String, Boolean> excluirConta(@PathVariable(value = "id") long contaID) throws ResourceNotFoundException {
        Conta contaBanco = contaRepository.findById(contaID).orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada: " + contaID));

        contaRepository.delete(contaBanco);
        Map<String,Boolean> res = new HashMap<>();
        res.put("deletado", true);

        return res;
    }

    @PutMapping("/conta/{id}/{depositar}")
    public ResponseEntity<Conta> depositarConta(@PathVariable(value = "id") long contaID,
                                                @PathVariable(value = "depositar") boolean depositar,
                                                @RequestBody Map<String,Long> valor) throws ResourceNotFoundException {
        Conta contaBanco = contaRepository.findById(contaID).orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada: " + contaID));

        double reqValor = valor.get("valor");

        if(reqValor > 0 && depositar) {
            double saldoConta = Double.parseDouble(contaBanco.getSaldo());
            saldoConta += reqValor;

            contaBanco.setSaldo(String.valueOf(saldoConta));
            final Conta contaAtualizada = contaRepository.save(contaBanco);
            return ResponseEntity.ok(contaAtualizada);

        }else if(reqValor > 0){
            double saldoConta = Double.parseDouble(contaBanco.getSaldo());

            if(saldoConta >= reqValor) {
                saldoConta -= reqValor;

                contaBanco.setSaldo(String.valueOf(saldoConta));
                final Conta contaAtualizada = contaRepository.save(contaBanco);
                return ResponseEntity.ok(contaAtualizada);
            }
        }

        return ResponseEntity.ok(contaBanco);
    }

}
