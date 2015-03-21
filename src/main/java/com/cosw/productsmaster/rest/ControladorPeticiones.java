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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *
 * @author Usuario
 */

@RestController
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ControladorPeticiones {
    
    @Autowired ProductsMasterLogica cl;
    
   
    
     @RequestMapping(value="producto/{id}",method = RequestMethod.GET)
     public Producto consprod(@PathVariable int id) {
         if(cl.ConsultaProducto(id)!=null){
             return cl.ConsultaProducto(id);
         }
     return null; 
     }
     
     @RequestMapping("/productos")
    public ArrayList<Producto> productosJson() {
        ArrayList<Producto> lista = null;
        try {
            lista = (ArrayList<Producto>) cl.cargarTodosLosProductos();
       } catch (PersistenceException ex) {
           Logger.getLogger(ControladorPeticiones.class.getName()).log(Level.SEVERE, null, ex);
       }
        return lista;
    }
    
    @RequestMapping("/ProveedorProd/{id}")
    public ArrayList<Producto> ProveedorProdJson(@PathVariable int id) {
        ArrayList<Producto> lista = null;
        try {
            lista = (ArrayList<Producto>) cl.cargarProductosPorProveedor(id);
       } catch (PersistenceException ex) {
           Logger.getLogger(ControladorPeticiones.class.getName()).log(Level.SEVERE, null, ex);
       }
        return lista;
    }
    
    @RequestMapping("/ProdsCategoria/{id}")
    public ArrayList<Producto> ProductosCategoria(@PathVariable int id) {
        ArrayList<Producto> lista = null;
        try {
            lista = (ArrayList<Producto>) cl.cargarProductosPorCategoria(id);
       } catch (PersistenceException ex) {
           Logger.getLogger(ControladorPeticiones.class.getName()).log(Level.SEVERE, null, ex);
       }
        return lista;
    }
    
}
