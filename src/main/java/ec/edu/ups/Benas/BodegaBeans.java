/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.Benas;

import ec.edu.ups.Controlador.BodegaFacade;
import ec.edu.ups.Controlador.ProductoFacade;
import ec.edu.ups.Modelo.Bodega;
import ec.edu.ups.Modelo.Producto;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author edwin
 */
@Named
@SessionScoped
public class BodegaBeans implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private BodegaFacade bodegaFacade;
    @EJB
    private ProductoFacade productoBeans;
    private Bodega b;
    private List<Bodega> listp = new ArrayList<>();
    private List<Producto> listpp = new ArrayList<>();
    private Producto producto;
    private int id;
    private String nombre;
    private String ciudad;
    private Collection<Producto> detalles;

    @PostConstruct
    public void init() {
        this.producto = new Producto();
        listp = bodegaFacade.findAll();
        listpp = productoBeans.findAll();

    }

    public BodegaFacade getBodegaFacade() {
        return bodegaFacade;
    }

    public void setBodegaFacade(BodegaFacade bodegaFacade) {
        this.bodegaFacade = bodegaFacade;
    }

    public List<Bodega> getListp() {
        return listp;
    }

    public void setListp(List<Bodega> listp) {
        this.listp = listp;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String add() {
        Bodega p = new Bodega(id, nombre, ciudad);
        bodegaFacade.create(p);
        listp = bodegaFacade.findAll();
        this.limpiar();
        return null;
    }

    public void limpiar() {

        this.nombre = "";
        this.ciudad = "";

    }

    public void edit(Bodega s) {

        bodegaFacade.edit(s);

        listp = bodegaFacade.findAll();
    }

    public String delete(Bodega s) {
        bodegaFacade.remove(s);
        listp = bodegaFacade.findAll();//llamo al findall para que se me actualice la lista
        return null;
    }

    public double total(Bodega b) {
        double c = 0;
        double d = 0;
        for (Producto p : b.getProductos()) {

            c = p.getPrecio() * p.getStock();
            d = d + c;
        }
        listp = bodegaFacade.findAll();
        listpp = productoBeans.findAll();
        return d;
    }

    public Bodega getB() {
        return b;
    }

    public void setB(Bodega b) {
        this.b = b;
    }

    public void detalle(Bodega b) {
        for (Producto p : b.getProductos()) {
            this.detalles = b.getProductos();
        }
    }

    public Collection<Producto> getDetalles() {
        return detalles;
    }

    public void setDetalles(Collection<Producto> detalles) {
        this.detalles = detalles;
    }

}
