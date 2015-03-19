/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.productsmaster.rep;

import com.cosw.productsmaster.persistencia.DetalleCompra;
import com.cosw.productsmaster.persistencia.DetalleCompraId;
import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Camilo
 */
public interface RepositorioDetalleCompra extends CrudRepository<DetalleCompra, DetalleCompraId>{
    
}
