/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.productsmaster.rep;

import com.cosw.productsmaster.persistencia.Factura;
import com.cosw.productsmaster.persistencia.Pedido;
import com.cosw.productsmaster.persistencia.Tienda;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author HOMERO
 */
public interface RepositorioFacturas extends CrudRepository<Factura, Integer>{
    
    @Query("SELECT f FROM Factura f  where f.tienda = :tienda and f.pedido=:pedido") 
    public Factura VerificarPedidodeTienda(@Param("pedido") Pedido pedido,@Param("tienda") Tienda tienda);
}
