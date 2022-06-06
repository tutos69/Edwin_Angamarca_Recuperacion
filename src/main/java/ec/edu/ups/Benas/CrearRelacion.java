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
import java.util.List;

/**
 *
 * @author edwin
 */
@Named
@SessionScoped
public class CrearRelacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private BodegaFacade bodegaFacade;
    @EJB
    private ProductoFacade productoFacade;
    private Bodega bodega;
    private List<Bodega> listp = new ArrayList<>();
    private List<Producto> listpp = new ArrayList<>();
    private Producto producto;
    private int id;
    private String nombre;
    private String ciudad;

    @PostConstruct
    public void init() {
        this.bodega = new Bodega();
        this.producto = new Producto();
        listp = bodegaFacade.findAll();
        listpp = productoFacade.findAll();
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

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String add() {

        System.out.println(bodega.getId() + " Producto " + producto.getId() + " " + producto.toString());
        for (Bodega bodega1 : listp) {
            if (bodega1.getId() == this.bodega.getId()) {
                this.bodega = bodega1;
                System.out.println(bodega.toString());
            }
        }
        for (Producto p : listpp) {
            if (p.getId() == this.producto.getId()) {
                this.producto = p;
                System.out.println(producto.toString());
            }
        }
        bodega.addProducto(producto);
        producto.addBodega(bodega);
        bodegaFacade.edit(bodega);
        productoFacade.edit(producto);
        this.limpiar();
        return null;
    }

    public void limpiar() {
        this.bodega =  new Bodega();
        this.producto =  new Producto();
    }

    public void edit(Bodega s) {
        bodegaFacade.edit(s);
        listp = bodegaFacade.findAll();
    }

    public String delete() {
        System.out.println(bodega.getId() + " Producto" + producto.getId());
        for (Bodega bodega1 : listp) {
            if (bodega1.getId() == this.bodega.getId()) {
                this.bodega = bodega1;
                System.out.println(bodega.toString());
            }
        }
        for (Producto bodega1 : listpp) {
            if (bodega1.getId() == this.producto.getId()) {
                this.producto = bodega1;
                System.out.println(producto.toString());
            }
        }
        bodega.removeProducto(producto);
        producto.removeBodega(bodega);
        bodegaFacade.edit(bodega);
        productoFacade.edit(producto);
        this.limpiar();
        listp = bodegaFacade.findAll();//llamo al findall para que se me actualice la lista
        return null;
    }
}
