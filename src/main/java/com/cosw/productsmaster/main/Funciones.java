/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.productsmaster.main;


import com.cosw.productsmaster.persistencia.DetalleCompra;
import com.cosw.productsmaster.persistencia.DetalleCompraId;
import com.cosw.productsmaster.persistencia.Envio;
import com.cosw.productsmaster.persistencia.EstadoEnvio;
import com.cosw.productsmaster.persistencia.Factura;
import com.cosw.productsmaster.persistencia.Lugar;
import com.cosw.productsmaster.persistencia.Pedido;
import com.cosw.productsmaster.persistencia.Producto;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author hcadavid
 */
public class Funciones {

    private Funciones() {
    }
    
    /**
     * Crea un nuevo pedido
     * @param s La sesion
     * @param direccion La direccion del pedido
     * @param fecha La fecha de envio del pedido
     * @param idProductos Los productos que se van a aregistrar al pedido
     * @param cantidades Las cantidad de los producto que van a ser registrados
     * @return El valor total del pedido
     * @throws Exception En caso de que cantidaddes.length != idProductos
     */
    public static int registroPedido(Session s, String direccion, Date fecha, int[] idProductos, int[] cantidades) throws Exception {
        if(idProductos.length != cantidades.length)
            throw new Exception("cantidades no corresponder al numero de productos a pedir");
        
        int valorPedido = 0;
        Set<DetalleCompra> detallesCompra = new HashSet<>();
        Pedido pedido = new Pedido(direccion, fecha, 0);
        int idPedido = (int) s.save(pedido);
        
        for (int i = 0; i < idProductos.length; i++) {
            int idProducto = idProductos[i];
            DetalleCompraId idDetalle = new DetalleCompraId(idPedido, idProducto);            
            Producto producto = (Producto)s.load(Producto.class, idProducto);
            
            valorPedido += cantidades[i] * producto.getPrecioLista();
            
            DetalleCompra detalleCompra = new DetalleCompra(idDetalle, producto, cantidades[i], producto.getPrecioLista());
            detallesCompra.add(detalleCompra);
            s.save(detalleCompra);
        }
        
        pedido.setDetalleCompras(detallesCompra);
        pedido.setValorTotal(valorPedido);
        
        s.update(pedido);
        
        return valorPedido;
    }
    
    /**
     * Pedidos de los últimos N días que tengan en común productos de un mismo proveedor.
     * @param s La sesion
     * @param dia Cantidad de los ultimos dias
     * @param idProveedor El proveedor
     * @return La lista de los pedidos que se encontraron
     */
    public static List<Pedido> pedidosNDiasProductos(Session s, int dia, int idProveedor) {
        Query q2 = s.createQuery("SELECT e.pedidos from Envio e inner join e.pedidos as p inner join p.detalleCompras as detalleP inner join detalleP.productos as prod inner join prod.proveedores as prov where (prov.idProveedores = ?) and (day(e.fechaSalida) < day(:finalDate)) GROUP BY prov");
        Date fecha = new Date();
        Date fechaFinal = new Date(fecha.getYear(), fecha.getMonth(), fecha.getDate()-dia);
        return null;
    }
    
    /**
     * Registra un nuevo envio de los pedidos que llegan por parametro
     * @param s La sesion
     * @param idPedidos El array de los id de los pedidos que van a ser asociados al pedido
     * @param idLugar El identificador del lugar donde inicialmente se encuentra el envio
     * @return  El id del envio registrado
     */
    public static int registroEnvio(Session s, int[] idPedidos, int idLugar, Date fechaSalida) {
        //FALTA VERIFICAR QUE LOS PEDIDOS QUE LLEGUEN POR PARAMETRO NO SE ENCUENTREN ACTUALMENTE EN UN ENVIO
        Envio e = new Envio(fechaSalida);
        Lugar l = (Lugar)s.load(Lugar.class, idLugar);
        EstadoEnvio ev= new EstadoEnvio(e, l, EstadoEnvio.OFICINA, "Este es un envio de prueba", "LATITUD:42Âº48'47\"589_LONGITUD:1Âº38'52\"684");
        e.getEstadoEnvios().add(ev);
        for(int i = 0; i < idPedidos.length; i++){
            Pedido p = (Pedido) s.load(Pedido.class, idPedidos[i]);
            e.getPedidos().add(p);
        }
        s.save(e);
        return e.getIdEnvio();
    }
    
    /**
     * Valor a pagar, por proveedor, de un pedido
     * @param s La sesion
     * @param idPedido El identificador del pedido
     * @return Retorna una lista con el proveedor y el valor a pagar.
     */
    public static List<Object[]> valorPagarProveedorPedido(Session s, int idPedido) {
        Query q1 = s.createQuery("SELECT proveedor, SUM(detail.cantidad * detail.precioUnitario)"
                + "FROM Pedido p "
                + "INNER JOIN p.detalleCompras detail "
                + "INNER JOIN detail.productos product "
                + "INNER JOIN product.proveedores proveedor "
                + "WHERE p.idPedidos = :idPedido "
                + "GROUP BY proveedor");
        q1.setInteger("idPedido", idPedido);
        return q1.list();
    }
}


