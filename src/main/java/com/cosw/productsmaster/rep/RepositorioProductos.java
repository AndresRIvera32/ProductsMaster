/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.productsmaster.rep;

import com.cosw.productsmaster.persistencia.Categoria;
import com.cosw.productsmaster.persistencia.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author HOMERO
 */
public interface RepositorioProductos extends CrudRepository<Producto, Integer>{
    
    @Query("SELECT p FROM Producto p inner join p.categoria c where c.idCategorias = :idCategoria")
    public List<Producto> productosPorCategoria(@Param("idCategoria") int idCategoria);
    
    @Query("SELECT producto FROM Producto producto INNER JOIN producto.proveedores AS prv where prv.idProveedores = :idProveedor")
    public List<Producto> cargarProductosPorProveedor(@Param("idProveedor")int idProveedor);
}
