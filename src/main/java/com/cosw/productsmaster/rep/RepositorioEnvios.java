/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.productsmaster.rep;

import com.cosw.productsmaster.persistencia.Envio;
import com.cosw.productsmaster.persistencia.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author HOMERO
 */
public interface RepositorioEnvios extends CrudRepository<Envio, Integer>{
       
}
