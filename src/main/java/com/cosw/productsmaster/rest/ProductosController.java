/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.productsmaster.rest;

import com.cosw.productsmaster.logica.ProductsMasterLogica;
import com.cosw.productsmaster.persistencia.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cosw.productsmaster.restcontrollers.OperationFailedException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author HOMERO
 */
@RestController
@RequestMapping("/productos")
public class ProductosController {
    @Autowired
    ProductsMasterLogica superStuff;
    
    @RequestMapping(value="/proveedor/{id}",method = RequestMethod.GET) 
    public List<Producto> cargarProductosPorProveedor(@PathVariable int id) {
        List<Producto> productos = null;
        productos = superStuff.cargarProductosPorProveedor(id);
        return productos;
    }
    
    @RequestMapping(value="/",method = RequestMethod.POST)
    public ResponseEntity<?> persist(@RequestBody Producto p) {
        superStuff.registrarProducto(p);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
