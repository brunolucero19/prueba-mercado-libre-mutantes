package com.example.parcialBrunoLucero50073.controllers;

import com.example.parcialBrunoLucero50073.dtos.DnaRequest;
import com.example.parcialBrunoLucero50073.entities.Individuo;
import com.example.parcialBrunoLucero50073.services.IndividuoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "mutant")
public class IndividuoController {

    private IndividuoService individuoService;

    public IndividuoController(IndividuoService individuoService){
        this.individuoService = individuoService;
    }
    @PostMapping("/")
    public ResponseEntity<String> isMutant(@RequestBody DnaRequest dnaRequest){

        try{
            individuoService.validateDNASequence(dnaRequest.getDna());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        try{
            boolean isMutant = individuoService.isMutant(dnaRequest.getDna());
            if (isMutant){
                return ResponseEntity.status(HttpStatus.OK).body("El individuo es mutante");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("El individuo no es mutante");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al hacer la solicitud.");
        }
    }


}
