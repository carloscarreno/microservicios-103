package com.aironmountain.bitcoins.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aironmountain.bitcoins.entities.Bitcoin;

import com.aironmountain.bitcoins.service.BitcoinService;

@RestController
@RequestMapping("/api/bitcoins")
public class BitcoinController {
     
        
     private final BitcoinService service;

    public BitcoinController(BitcoinService service) {
        this.service = service;
    }

    @GetMapping
    public List<Bitcoin> listar() {
        return service.listar();
    }

    @PostMapping
    public Bitcoin crear(@RequestBody Bitcoin nuevo) {
        return service.guardar(nuevo);
    }

    @GetMapping("/{id}")
    public Bitcoin obtener(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Bitcoin actualizar(@PathVariable Long id, @RequestBody Bitcoin nuevo) {
        return service.actualizar(id, nuevo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/usuario/{username}")
    public List<Bitcoin> obtenerBitcoins(@PathVariable String username) throws Exception {
            return service.obtenerBitcoins(username);     
    }

}
