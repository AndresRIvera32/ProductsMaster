/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.productsmaster.logica;

import com.cosw.productsmaster.persistencia.Categoria;
import com.cosw.productsmaster.persistencia.Descuento;
import com.cosw.productsmaster.persistencia.DetalleCompra;
import com.cosw.productsmaster.persistencia.DetalleCompraId;
import com.cosw.productsmaster.persistencia.Envio;
import com.cosw.productsmaster.persistencia.Factura;
import com.cosw.productsmaster.persistencia.Lugar;
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
import com.cosw.productsmaster.rep.RepositorioPedidos;
import com.cosw.productsmaster.rep.RepositorioProductos;
import com.cosw.productsmaster.rep.RepositorioProveedores;
import com.cosw.productsmaster.rep.RepositorioTenderos;
import com.cosw.productsmaster.rep.RepositorioTiendas;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HOMERO
 */
@Service
public class ProductsMasterLogica {
    
    @Autowired
    private RepositorioProveedores repositorioProveedores;
    
    @Autowired
    private RepositorioProductos repositorioProductos;
    
    @Autowired
    private RepositorioCategorias repositorioCategorias;
    
    @Autowired
    private RepositorioEnvios repositorioEnvios;
    
    @Autowired
    private RepositorioEstadoEnvios repositorioEstadoEnvios;
    
    @Autowired
    private RepositorioPedidos repositorioPedidos;
    
    @Autowired
    private RepositorioDetalleCompra repositorioDetalleCompra;
    
    @Autowired
    private RepositorioTenderos repositorioTenderos;
    
    @Autowired
    private RepositorioTiendas repositorioTiendas;
    
    @Autowired
    private RepositorioFacturas repositorioFacturas;
    
    @Autowired
    private RepositorioDescuentos repositorioDescuentos;
    
    /**
     * @author Holmer
     * Registra un nuevo producto
     * @param id El nuevo id que se registrara
     * @param proveedor El proveedor dueño de este producto
     * @param categoria La categoria a la que pertenece el producto
     * @param descuento El descuento aplicable a este producto
     * @param precio El precio de este producto
     * @param descripcion La descripcion caracteristica de este producto
     */
    public void registrarProducto(int id, int proveedor, int categoria, int descuento, int precio, String descripcion) {
        Proveedor prov = repositorioProveedores.findOne(Integer.valueOf(proveedor));
        Categoria cat = repositorioCategorias.findOne(Integer.valueOf(categoria));
        Descuento desc = repositorioDescuentos.findOne(Integer.valueOf(descuento));
        Producto producto = new Producto(id, cat, desc, descripcion, prov, precio);
        repositorioProductos.save(producto);
    }
    
    /**
     * @author Holmer
     * Registra un nuevo producto a partir de un objeto instanciado
     * @param producto 
     */
    public void registrarProducto(Producto producto){
        repositorioProductos.save(producto);
    }
    
    /**
     * @author Andres
     * Trae todos los productos correspondiente a la categoria que llega por parametro
     * @param categoria El id de la categoria existente
     * @return Una List con los productos
     */
    public List<Producto> cargarProductosPorCategoria(int categoria){
        return repositorioProductos.productosPorCategoria(categoria);
    }
    
    /**
     * @author Andres
     * Trae la lista de todos los productos existentes
     * @return La lista de los productos
     */
    public List<Producto> cargarTodosLosProductos(){
        return (List<Producto>) repositorioProductos.findAll();
    }
    /**
     * @author Andres
     * Trae la lista de todos los proveedores existentes
     * @return La lista de los proveedores
     */
    public List<Proveedor> cargarTodosLosProveedores(){
        return (List<Proveedor>) repositorioProveedores.findAll();
    }
    /**
     * @author Holmer
     * Trae el producto correspondiente al id del proveedor
     * @param proveedor El id del proveedor
     * @return La lista de los productos filtrados por proveedor
     */
    public List<Producto> cargarProductosPorProveedor(int proveedor){
        List<Producto> productos = repositorioProductos.cargarProductosPorProveedor(proveedor);
        return productos;
    }
    
    /**
     * @author Andres
     * Trae todas las categorias en existencia
     * @return Una lista de categorias
     */
    public List<Categoria> cargarCategorias(){
        return (List<Categoria>) repositorioCategorias.findAll();
    }
    
    /**
     * @author Camilo
     * Registra un nuevo pedido con los detalles de compra correspondiente
     * @param direccion La direccion del envio
     * @param fecha La fecha en la que deberia llegar el producto
     * @param idProductos La lista de los productos
     * @param cantidades Las cantidades
     * @return El id del nuevo pedido registrado
     * @throws java.lang.Exception En caso de que las cantidades no correspondan a los productos
     */
    public int registrarPedido(String direccion, Date fecha, int[] idProductos, int cantidades[]) throws Exception{
        if(idProductos.length != cantidades.length)
            throw new Exception("cantidades no corresponder al numero de productos a pedir");
        
        int valorPedido = 0;
        Set<DetalleCompra> detallesCompra = new HashSet<>();
        Pedido pedido = new Pedido(direccion, fecha, 0);
        repositorioPedidos.save(pedido);
        
        for (int i = 0; i < idProductos.length; i++) {
            int idProducto = idProductos[i];
            DetalleCompraId idDetalle = new DetalleCompraId(pedido.getIdPedidos(), idProducto);    
            Producto producto = repositorioProductos.findOne(idProducto);
            
            valorPedido += cantidades[i] * producto.getPrecioLista();
            
            DetalleCompra detalleCompra = new DetalleCompra(idDetalle, producto, cantidades[i], producto.getPrecioLista());
            detallesCompra.add(detalleCompra);
            
            repositorioDetalleCompra.save(detalleCompra);
        }
        
        pedido.setDetalleCompras(detallesCompra);
        pedido.setValorTotal(valorPedido);
        
        repositorioPedidos.save(pedido);
        
        return pedido.getIdPedidos();
    }
    
    /**
     * @author Camilo
     * Registra un nuevo envio
     * @param idPedidos Los pedidos asociados a este envio
     * @return El id del nuevo envio registrado
     */
    public int registrarEnvio(int[] idPedidos){
        Envio envio = new Envio(new Date());
        Set<Pedido> pedidos = new HashSet<>();
        for (int idP : idPedidos) {
            pedidos.add(repositorioPedidos.findOne(idP));
        }
        envio.setPedidos(pedidos);
        repositorioEnvios.save(envio);
        return envio.getIdEnvio();
    }
    
    public Producto ConsultaProducto(int id){
        return repositorioProductos.findOne(id);
    }
    
    /**
     * @Author Andres
     * Actualiza el estado del envio
     * @param idEnvio el id del envio el cual se le va a actualizar el estado
     * @param lugar Donde se encuentra el envio
     * @param estado Estado que corresponde a las variables estadicas de Estado
     * @param descripcion La descripcion 
     * @param coords Las coordenadas de la ubicacion exacta del envio
     */
    public void actualizarEstadoDeEnvio(int idEnvio, Lugar lugar, String estado, String descripcion, String coords){
        repositorioEstadoEnvios.ActualizarEnvio(idEnvio,estado,descripcion,coords,lugar);
    }
    
    /**
     * @Author Holmer
     * Crea un nuevo proveedor
     * @param idProveedores El id del proveedor
     * @param lugares la ubicacion geografica del proveedor
     * @param razonSocial Razon social del proveedor
     * @param direccion Direccion de oficina del proveedor
     * @param contactoTelefonico El numero telefonocio de este proveedor
     * @param sitioWeb El sitio web del proveedor
     * @param email Mail por si acaso
     * @return El id del nuevo proveedor
     */
    public int crearNuevoProveedor(int idProveedores, Lugar lugares, String razonSocial, String direccion, String contactoTelefonico, String sitioWeb, String email){
        Proveedor p = new Proveedor(idProveedores, lugares, razonSocial, direccion, contactoTelefonico, sitioWeb, email);
        repositorioProveedores.save(p);
        return p.getIdProveedores();
    }
    
    /**
     * @Author Holmer
     * Crea un nuevo proveedor
     * @param proveedor
     * @return id del proveedo, una vez tiene un estado persistente
     */
    public int crearNuevoProveedor(Proveedor proveedor){
        repositorioProveedores.save(proveedor);
        return proveedor.getIdProveedores();
    }
    
    /**
     * @Author Holmer
     * Crea un nuevo tendero
     * @param idTendero La cedula del tendero
     * @param nombre nombre del tendero
     */
    public void crearNuevoTendero(int idTendero, String nombre){
        Tendero t = new Tendero(idTendero, nombre);
        repositorioTenderos.save(t);
    }
    /**
     * @Author Holmer
     * Crea un nuevo tendero
     * @param t 
     */
    public void crearNuevoTendero(Tendero t) {
       repositorioTenderos.save(t);
    }
    /**
     * @author Andres
     * Consultar si un pedido pertenece a un tendero
     * @param t idTienda
     * @param p idPedido
     * @return boolean
     */
    public Factura VerificarPedidoTendero(int t,int p){
        Tienda t1=repositorioTiendas.findOne(t);
        Pedido p1=repositorioPedidos.findOne(p);
        Factura f=repositorioFacturas.VerificarPedidodeTienda(p1, t1);
        if(f!=null){
            return f;
        }
        return null;
    }
}
