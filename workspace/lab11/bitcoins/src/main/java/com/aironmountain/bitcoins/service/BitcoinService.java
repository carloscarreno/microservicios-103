package com.aironmountain.bitcoins.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aironmountain.bitcoins.entities.Bitcoin;
import com.aironmountain.bitcoins.repository.BitcoinRepository;
import com.aironmountain.bitcoins.service.dto.UsuarioDTO;

@Service
public class BitcoinService {
    
    private final BitcoinRepository repository;
    
    @Autowired
    private IdentidadCliente identidadesService;
      
    public BitcoinService(BitcoinRepository repository) {
        this.repository = repository;
    }

    public List<Bitcoin> listar() {
        return repository.findAll();
    }

    public Bitcoin guardar(Bitcoin nuevo) {
        return repository.save(nuevo);
    }

    public Bitcoin buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Bitcoin actualizar(Long id, Bitcoin nuevo) {
        Optional<Bitcoin> encontrado = repository.findById(id);
        if (encontrado.isPresent()) {
            Bitcoin entidad = encontrado.get();
            entidad.setCantidad(nuevo.getCantidad());
            entidad.setValor(nuevo.getValor());
            entidad.setIdusuario(nuevo.getIdusuario());
            return repository.save(entidad);
        }
        return null;
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<Bitcoin> obtenerBitcoins(String username){
       UsuarioDTO dto=identidadesService.obtenerUsuario(username);
       return repository.findByIdusuario(dto.getId());
    }
    
}
