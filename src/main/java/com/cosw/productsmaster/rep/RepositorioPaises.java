/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.productsmaster.rep;

import com.cosw.productsmaster.persistencia.Pais;
import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author HOMERO
 */
public interface RepositorioPaises extends CrudRepository<Pais, String>{
    
}
