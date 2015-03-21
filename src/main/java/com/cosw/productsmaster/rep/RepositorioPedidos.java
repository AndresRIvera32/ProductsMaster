/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.productsmaster.rep;



import com.cosw.productsmaster.persistencia.Factura;
import com.cosw.productsmaster.persistencia.Pedido;
import java.io.Serializable;
import java.util.Set;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HOMERO
 */
public interface RepositorioPedidos extends CrudRepository<Pedido, Integer>{
    
    @Query("SELECT f FROM Factura f  where f.pedido = :pedido") 
    public Factura ConsultarFacturaDePedido(@Param("pedido") Pedido pedido); 

}
