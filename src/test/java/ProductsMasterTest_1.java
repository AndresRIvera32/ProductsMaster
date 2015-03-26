/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.cosw.productsmaster.logica.ProductsMasterLogica;
import com.cosw.productsmaster.persistencia.Categoria;
import com.cosw.productsmaster.persistencia.Descuento;
import com.cosw.productsmaster.persistencia.DetalleCompra;
import com.cosw.productsmaster.persistencia.DetalleCompraId;
import com.cosw.productsmaster.persistencia.Envio;
import com.cosw.productsmaster.persistencia.EstadoEnvio;
import com.cosw.productsmaster.persistencia.Factura;
import com.cosw.productsmaster.persistencia.Lugar;
import com.cosw.productsmaster.persistencia.Pais;
import com.cosw.productsmaster.persistencia.Pedido;
import com.cosw.productsmaster.persistencia.Producto;
import com.cosw.productsmaster.persistencia.Proveedor;
import com.cosw.productsmaster.persistencia.Tendero;
import com.cosw.productsmaster.persistencia.Tienda;
import com.cosw.productsmaster.rep.RepositorioCategorias;
import com.cosw.productsmaster.rep.RepositorioDescuentos;
import com.cosw.productsmaster.rep.RepositorioDetalleCompra;
import com.cosw.productsmaster.rep.RepositorioEnvios;
import com.cosw.productsmaster.rep.RepositorioEstadoEnvios;
import com.cosw.productsmaster.rep.RepositorioFacturas;
import com.cosw.productsmaster.rep.RepositorioLugares;
import com.cosw.productsmaster.rep.RepositorioPaises;
import com.cosw.productsmaster.rep.RepositorioPedidos;
import com.cosw.productsmaster.rep.RepositorioProductos;
import com.cosw.productsmaster.rep.RepositorioProveedores;
import com.cosw.productsmaster.rep.RepositorioTenderos;
import com.cosw.productsmaster.rep.RepositorioTiendas;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.collections.IteratorUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 *
 * @author HOMERO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = {"classpath:applicationContextH2.xml"})
public class ProductsMasterTest_1 {
    
    @Autowired
    private ProductsMasterLogica superStuff;
    
    @Autowired
    private RepositorioLugares repositorioLugares;
    
    @Autowired
    private RepositorioDetalleCompra repositorioDetalleCompra;
    
    @Autowired
    private RepositorioPaises repositorioPaises;
    
    @Autowired
    private RepositorioProveedores repositorioProveedores;
    
    @Autowired
    private RepositorioDescuentos repositorioDescuentos;
    
    @Autowired
    private RepositorioCategorias repositorioCategorias;
    
    @Autowired
    private RepositorioProductos repositorioProductos;
    
    @Autowired
    private RepositorioPedidos repositorioPedidos;
    
    @Autowired
    private RepositorioEnvios repositorioEnvios;
    
    @Autowired
    private RepositorioFacturas repositorioFacturas;
    
    @Autowired
    private RepositorioEstadoEnvios repositorioEstadoEnvios;
    
    @Autowired
    private RepositorioTenderos repositorioTenderos;
    
    @Autowired
    private RepositorioTiendas repositorioTiendas;
    
    //private static boolean DATOSPREPARADOS = false;
    

    
    @Before
    public void setUp() {
        repositorioDetalleCompra.deleteAll();
        repositorioProductos.deleteAll();
        repositorioProveedores.deleteAll();
        repositorioCategorias.deleteAll();
        repositorioDescuentos.deleteAll();
        repositorioEstadoEnvios.deleteAll();
        repositorioEnvios.deleteAll();
        repositorioPedidos.deleteAll();
        repositorioPaises.deleteAll();
        repositorioLugares.deleteAll(); 
       
       
     
     
    }
    
    
    @After
    public void tearDown() { 
    }
    public void InsertarDatos(){
        Pais p1 = new Pais("Colombia", "COL", "ESPAÃ‘OL", Pais.SIHAYCOBERTURA);
        Set<Lugar> newPlaces = new LinkedHashSet<>();
        Lugar L1=new Lugar(p1, "Bogota", "Cedritos");
        newPlaces.add(L1);
        newPlaces.add(new Lugar(p1,"Bogotas","Las Orquideas"));
        newPlaces.add(new Lugar(p1,"BogotÃ¡","Mazuren"));
        newPlaces.add(new Lugar(p1,"Bogotai","Verbenal"));

        p1.setLugares(newPlaces);
        repositorioPaises.save(p1);
        List<Lugar> lugares = (List<Lugar>)repositorioLugares.findAll();

        Proveedor pr1 = new Proveedor(1,lugares.get(0), "Licorera mr concha", "Calle Falsa 123", "3044463405", "www.eltiomoe.com", "Lico@mail.com");
        Proveedor pr2 = new Proveedor(2, lugares.get(0), "Frutas la delicia", "Calle 21a-33", "5764463404", "www.frutasdeli.com", "frutasdeli@mail.com");
        superStuff.crearNuevoProveedor(pr1);
        superStuff.crearNuevoProveedor(pr2);
        
        Categoria c1=new Categoria(1, "Frutas", "Categoria que agrupa las frutas");
        Categoria c2=new Categoria(2, "Licor", "Categoria que agrupa los licores");
        Descuento d1 = new Descuento(0, new Date(), new Date(), "Esto es un descuento del 0%");
        Descuento d2 = new Descuento(50, new Date(), new Date(), "Esto es un descuento del 50%");
       repositorioCategorias.save(c1);
       repositorioCategorias.save(c2);
       repositorioDescuentos.save(d1);
       repositorioDescuentos.save(d2);
       repositorioProductos.save(new Producto(1, c2, d2, "Jack Daniel´s Whiskey Old Time", pr1, 1000000));
       repositorioProductos.save(new Producto(2, c2, d1, "Cerveza Aguila", pr1, 1000000));
       repositorioProductos.save(new Producto(3, c2, d1, "Aguardiente Antioqueño", pr1, 1000000));
       repositorioProductos.save(new Producto(4, c2, d1, "Vino Cariñoso", pr1, 1000000));
       
       repositorioProductos.save(new Producto(5, c1, d1, "Melon", pr2, 3000));
       repositorioProductos.save(new Producto(6, c1, d1, "Papaya", pr2, 5000));
    }
    
    
    
    @Test
    public void VerifPedidoTendero(){
        
        InsertarDatos();
        
        List<Lugar> lugares = (List<Lugar>)repositorioLugares.findAll();
        System.out.println(lugares.get(0).getCiudad());
        Tienda td=new Tienda(41, lugares.get(0), "cra 47 163b-32");
        repositorioTiendas.save(td);
        Tienda td1=new Tienda(2, lugares.get(1), "cra 13 164b-32");
        repositorioTiendas.save(td1);
        
        Set<Tienda> s=new LinkedHashSet<>();
        s.add(td);
        Tendero tend=new Tendero(2085, "Julio Serrano",s); 
        repositorioTenderos.save(tend);
        
        /*Asignacion de tienda a tendero*/
        Set<Tienda> s1=new LinkedHashSet<>();
        s1.add(td1);
        Tendero tend1=new Tendero(2088, "David Salsedo",s1);
        tend.setTiendas(s1);
        repositorioTenderos.save(tend1);
        /*hacer un pedido de una tienda*/
        Pedido ped=new Pedido("cra 47 163b-32", new Date(), 8000);
        repositorioPedidos.save(ped);
        //Pedido pedido=repositorioPedidos.findOne(1);
        Set<DetalleCompra> SetDet=new LinkedHashSet<>();
        Producto p1=repositorioProductos.findOne(5);
        Producto p2=repositorioProductos.findOne(6);
        
        DetalleCompraId id=new DetalleCompraId(1, 5);
        DetalleCompra det=new DetalleCompra(id, p1, 1, 3000);
        repositorioDetalleCompra.save(det);
        
        DetalleCompraId id1=new DetalleCompraId(1, 6);
        DetalleCompra det1=new DetalleCompra(id1, p2, 1, 3000);
        repositorioDetalleCompra.save(det1);
        
        Tienda t=repositorioTiendas.findOne(41);
        Pedido p=repositorioPedidos.findOne(1);

        repositorioFacturas.save(new Factura(1, 8, ped,t));
               
        Factura f=superStuff.VerificarPedidoTendero(41, 1);
        boolean e;
        if(f!=null){
               e=true;
        }else{
            e=false;
        }
           assertEquals("la tienda con id=41 corresponde al pedido con id=1",e,true );        

}

}
