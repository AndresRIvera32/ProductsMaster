/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.productsmaster.rest;

import com.cosw.productsmaster.logica.ProductsMasterLogica;
import com.cosw.productsmaster.persistencia.Proveedor;
import com.cosw.productsmaster.persistencia.Tendero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HOMERO
 */
@RestController
@RequestMapping("/tenderos")
public class TenderosController {
    @Autowired
    ProductsMasterLogica superStuff;
    
    @RequestMapping(value="/",method = RequestMethod.POST)
    public ResponseEntity<?> persist(@RequestBody Tendero t) {
        superStuff.crearNuevoTendero(t);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
