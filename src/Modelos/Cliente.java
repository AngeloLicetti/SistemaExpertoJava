/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Angelo
 */
@Entity
@Table(name = "cliente", catalog = "banco-encadenado", schema = "")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findByIdcliente", query = "SELECT c FROM Cliente c WHERE c.idcliente = :idcliente")
    , @NamedQuery(name = "Cliente.findByDni", query = "SELECT c FROM Cliente c WHERE c.dni = :dni")
    , @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Cliente.findByEdad", query = "SELECT c FROM Cliente c WHERE c.edad = :edad")
    , @NamedQuery(name = "Cliente.findByMesesAntiguedad", query = "SELECT c FROM Cliente c WHERE c.mesesAntiguedad = :mesesAntiguedad")
    , @NamedQuery(name = "Cliente.findByDeudasVigentes", query = "SELECT c FROM Cliente c WHERE c.deudasVigentes = :deudasVigentes")})
public class Cliente implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcliente")
    private Integer idcliente;
    @Column(name = "dni")
    private Integer dni;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "edad")
    private String edad;
    @Column(name = "meses_antiguedad")
    private Integer mesesAntiguedad;
    @Column(name = "deudas_vigentes")
    private Integer deudasVigentes;

    public Cliente() {
    }

    public Cliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        Integer oldIdcliente = this.idcliente;
        this.idcliente = idcliente;
        changeSupport.firePropertyChange("idcliente", oldIdcliente, idcliente);
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        Integer oldDni = this.dni;
        this.dni = dni;
        changeSupport.firePropertyChange("dni", oldDni, dni);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        String oldNombre = this.nombre;
        this.nombre = nombre;
        changeSupport.firePropertyChange("nombre", oldNombre, nombre);
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        String oldEdad = this.edad;
        this.edad = edad;
        changeSupport.firePropertyChange("edad", oldEdad, edad);
    }

    public Integer getMesesAntiguedad() {
        return mesesAntiguedad;
    }

    public void setMesesAntiguedad(Integer mesesAntiguedad) {
        Integer oldMesesAntiguedad = this.mesesAntiguedad;
        this.mesesAntiguedad = mesesAntiguedad;
        changeSupport.firePropertyChange("mesesAntiguedad", oldMesesAntiguedad, mesesAntiguedad);
    }

    public Integer getDeudasVigentes() {
        return deudasVigentes;
    }

    public void setDeudasVigentes(Integer deudasVigentes) {
        Integer oldDeudasVigentes = this.deudasVigentes;
        this.deudasVigentes = deudasVigentes;
        changeSupport.firePropertyChange("deudasVigentes", oldDeudasVigentes, deudasVigentes);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vistas.Cliente[ idcliente=" + idcliente + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
