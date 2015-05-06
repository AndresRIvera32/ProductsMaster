/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.productsmaster.rest;

import com.cosw.productsmaster.logica.ProductsMasterLogica;
import com.cosw.productsmaster.persistencia.Factura;
import com.cosw.productsmaster.persistencia.Producto;
import com.cosw.productsmaster.persistencia.Proveedor;
import com.cosw.productsmaster.persistencia.Tendero;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    
   
    /**
     * Metodo para retornar un producto con un id
     * @param id
     * @return un producto
     */
     @RequestMapping(value="producto/{id}",method = RequestMethod.GET)
     public Producto consprod(@PathVariable int id) {
         if(cl.ConsultaProducto(id)!=null){
             return cl.ConsultaProducto(id);
         }
     return null; 
     }
     
     /**
      * Metodo para retornar productos que hay
      * @return Todos los productos
      */
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
    
    /**
     * 
     * @return 
     */
    @RequestMapping("/proveedor")
    public ArrayList<Proveedor> proveedoresJson() {
        ArrayList<Proveedor> lista = null;
        try {
            lista = (ArrayList<Proveedor>) cl.cargarTodosLosProveedores();
       } catch (PersistenceException ex) {
           Logger.getLogger(ControladorPeticiones.class.getName()).log(Level.SEVERE, null, ex);
       }
        return lista;
    }
    
    /**
     * Metodo para retornar los productos de un proveedor
     * @param id
     * @return productos de proveedor
     */
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
    
    
    
    /**
     * Metodo para retonar productos de una categoria
     * @param id
     * @return productos de categoria
     */
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
    /**
     * 
     * @param id idPedido
     * @param id2 idTienda
     * @return respuesta si corresponde o no corresponde el pedido a la tienda
     */
    @RequestMapping(value="pedidofac/{id}/tiendafac/{id2}",method = RequestMethod.GET)
     public String PedidoTienda(@PathVariable int id,@PathVariable int id2) {
         String json;
         if(cl.VerificarPedidoTendero(id2,id)!=null){
             
        return json = "{\"Tienda\":"+id2+",\"Pedido\":"+id+"}";           
         }
         return json = "{\"Tienda\":\"\",\"Pedido\":\"\"}";          

     }
    
    
}
