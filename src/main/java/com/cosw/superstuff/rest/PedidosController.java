/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.superstuff.rest;

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
    
    @RequestMapping(value="/echo/{input}",method = RequestMethod.GET)        
    public ResponseEntity<?> consultaX(@PathVariable String input) {       
        return new ResponseEntity<>("REST API working. Echo:"+input,HttpStatus.ACCEPTED);
    }
}