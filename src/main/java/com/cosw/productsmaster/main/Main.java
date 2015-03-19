/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.productsmaster.main;

import com.cosw.productsmaster.persistencia.Banco;
import com.cosw.productsmaster.persistencia.Lugar;
import com.cosw.productsmaster.persistencia.Pais;
import com.cosw.productsmaster.persistencia.Pedido;
import com.cosw.productsmaster.persistencia.Producto;
import com.cosw.productsmaster.persistencia.Proveedor;
import com.cosw.productsmaster.persistencia.Tienda;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author hcadavid
 */
public class Main {
    
    public static void main(String a[]){
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
        configuration.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session=sessionFactory.openSession();        
        Transaction tx=session.beginTransaction();
   
        //Aqui va codigo de pruebas
        
        Query q2 = session.createQuery("SELECT e.pedidos from Envio e inner join e.pedidos as p inner join p.detalleCompras as detalleP inner join detalleP.productos as prod inner join prod.proveedores as prov where (prov.idProveedores = 1) and (day(e.fechaSalida) < day(:finalDate)) GROUP BY prov");
        
        List<Pedido> p = q2.list();
        
        for (Pedido p1 : p) {
            System.out.println("Fecha" + p1.getFechaLlegada().toString());
        }
        
        tx.commit();
        session.close();
    }
    
}
