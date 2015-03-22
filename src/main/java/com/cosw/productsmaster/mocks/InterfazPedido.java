/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.productsmaster.mocks;

/**
 *
 * @author Usuario
 */
public interface InterfazPedido {
    
    /**
     * 
     * @param EmpresaEnvio
     * @param Pedido
     * @return Codigo del envio del pedido
     */
    public int RegistrarPedido(int EmpresaEnvio,int Pedido);
}
