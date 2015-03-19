/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.productsmaster.rest;

import com.cosw.productsmaster.logica.ProductsMasterLogica;
import com.cosw.productsmaster.persistencia.Pedido;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Camilo
 */
@RestController
@RequestMapping("/pedido")
public class PedidosController {
    
    @Autowired
    ProductsMasterLogica superStuff;
    
    @RequestMapping(value="/",method = RequestMethod.POST)
    public ResponseEntity<?> persist(@PathVariable("direccion") String direccion, 
                                     @PathVariable("fecha") Date fecha,
                                     @PathVariable("productos") int[] productos,
                                     @PathVariable("cantidades") int[] cantidades) {  
        
        try {
            superStuff.registrarPedido(direccion, fecha, productos, cantidades);
            return new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
        } catch (Exception ex) {
            Logger.getLogger(PedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
