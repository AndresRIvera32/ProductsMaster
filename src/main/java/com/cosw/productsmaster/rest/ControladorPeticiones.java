/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.productsmaster.rest;

import com.cosw.productsmaster.logica.ProductsMasterLogica;
import com.cosw.productsmaster.persistencia.Producto;
import com.cosw.productsmaster.persistencia.Tendero;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 * @author Usuario
 */

@RestController
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ControladorPeticiones {
    private ProductsMasterLogica pm; 
    
    
    @Autowired
    public void setFachadaOperaciones(ProductsMasterLogica pm){
        this.pm = pm;        
    }
    
     @RequestMapping("/productos")
    public ArrayList<Producto> ProductosJson() {
        ArrayList<Producto> lista = null;
        try {
            lista =  (ArrayList<Producto>) pm.cargarTodosLosProductos();
       } catch (PersistenceException ex) {
           Logger.getLogger(ControladorPeticiones.class.getName()).log(Level.SEVERE, null, ex);
       }
        return lista;
    }
    
}
