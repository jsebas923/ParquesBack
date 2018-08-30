/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.iteria.parquesback;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ITERIASAS
 */
@Entity
@Table(name = "PARQUES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parques.findAll", query = "SELECT p FROM Parques p")
    , @NamedQuery(name = "Parques.findById", query = "SELECT p FROM Parques p WHERE p.id = :id")
    , @NamedQuery(name = "Parques.findByName", query = "SELECT p FROM Parques p WHERE p.name = :name")
    , @NamedQuery(name = "Parques.findByState", query = "SELECT p FROM Parques p WHERE p.state = :state")
    , @NamedQuery(name = "Parques.findByCapacity", query = "SELECT p FROM Parques p WHERE p.capacity = :capacity")
    , @NamedQuery(name = "Parques.findByStatus", query = "SELECT p FROM Parques p WHERE p.status = :status")})
public class Parques implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "STATE")
    private String state;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAPACITY")
    private int capacity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "STATUS")
    private String status;

    public Parques() {
    }

    public Parques(String id) {
        this.id = id;
    }

    public Parques(String id, String name, String state, int capacity, String status) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.capacity = capacity;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parques)) {
            return false;
        }
        Parques other = (Parques) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.iteria.parquesback.Parques[ id=" + id + " ]";
    }
    
}
