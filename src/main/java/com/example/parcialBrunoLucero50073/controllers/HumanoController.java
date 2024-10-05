package com.example.parcialBrunoLucero50073.controllers;

import com.example.parcialBrunoLucero50073.entities.Humano;
import com.example.parcialBrunoLucero50073.services.HumanoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "mutant")
public class HumanoController {

    private HumanoService humanoService;

    public HumanoController(HumanoService humanoService){
        this.humanoService = humanoService;
    }

    @PostMapping("/")
    public ResponseEntity<String> isMutant(@RequestBody Humano humano){
        try{
            boolean esMutante = humanoService.isMutant(humano.getDna());
            if (esMutante){
                return ResponseEntity.status(HttpStatus.OK).body("Es mutante");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No es mutante");
            }
        } catch (Exception e) {
            if (e.getMessage().equals("El ADN ya existe en la base de datos")){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al hacer la solicitud. Este ADN ya está cargado.");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al hacer la solicitud.");
        }
    }


}
