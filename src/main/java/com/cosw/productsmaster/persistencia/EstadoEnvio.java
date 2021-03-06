package com.cosw.productsmaster.persistencia;
// Generated Feb 18, 2015 10:14:56 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * EstadoEnvios generated by hbm2java
 */
@Entity
@Table(name="EstadoEnvios")
public class EstadoEnvio  implements java.io.Serializable {

    public static final String OFICINA = "OF";
    public static final String CAMINO = "CA";
    public static final String DESTINO = "LL";
    
    private int idEstadoEnvios;
    private Envio envios;
    private Lugar lugares;
    private String estado;
    private String descripcion;
    private String coordenadas;

    public EstadoEnvio() {
    }

    public EstadoEnvio(int idEstadoEnvios, Envio envios, Lugar lugares, String estado, String descripcion, String coordenadas) {
       this.idEstadoEnvios = idEstadoEnvios;
       this.envios = envios;
       this.lugares = lugares;
       this.estado = estado;
       this.descripcion = descripcion;
       this.coordenadas = coordenadas;
    }
    
    public EstadoEnvio(Envio envios, Lugar lugares, String estado, String descripcion, String coordenadas){
       this.envios = envios;
       this.lugares = lugares;
       this.estado = estado;
       this.descripcion = descripcion;
       this.coordenadas = coordenadas;
    }
   
    @Id 
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="idEstadoEnvios", unique=true, nullable=false)
    public int getIdEstadoEnvios() {
        return this.idEstadoEnvios;
    }
    
    public void setIdEstadoEnvios(int idEstadoEnvios) {
        this.idEstadoEnvios = idEstadoEnvios;
    }
    
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Envio_idEnvio", nullable=false)
    public Envio getEnvios() {
        return this.envios;
    }
    
    public void setEnvios(Envio envios) {
        this.envios = envios;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Lugar_idLugar", nullable=false)
    public Lugar getLugares() {
        return this.lugares;
    }
    
    public void setLugares(Lugar lugares) {
        this.lugares = lugares;
    }

    
    @Column(name="estado", nullable=false, length=11)
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    @Column(name="descripcion", nullable=false, length=45)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Column(name="coordenadas", nullable=false, length=45)
    public String getCoordenadas() {
        return this.coordenadas;
    }
    
    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }
}


