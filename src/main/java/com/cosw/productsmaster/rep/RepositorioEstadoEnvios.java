/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.productsmaster.rep;

import com.cosw.productsmaster.persistencia.EstadoEnvio;
import com.cosw.productsmaster.persistencia.Lugar;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Usuario
 */
public interface RepositorioEstadoEnvios extends CrudRepository<EstadoEnvio,Integer> {
    
    
    @Modifying
    @Query("UPDATE EstadoEnvio e SET estado=:estado, descripcion=:descripcion, coordenadas=:coordenadas, lugares=:lugar WHERE e.envios.idEnvio=:idEnvio")
    @Transactional
    public void ActualizarEnvio(@Param("idEnvio") int idEnvio,@Param("estado") String estado,@Param("descripcion") String descripcion,@Param("coordenadas") String coordenadas,@Param("lugar") Lugar lugar);

    
}
