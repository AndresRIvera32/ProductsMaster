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
public interface InterfazPago {
    
    /**
     * 
     * @param EntidadFinanciera
     * @param valor
     * @param NumCuenta
     * @return devuelve el numero de factura del pago
     */
    public int Pago(int EntidadFinanciera,long valor,int NumCuenta);
}
