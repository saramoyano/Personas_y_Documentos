package modelo;

import javax.persistence.*;
import java.util.Set;

@Entity(name="documento")
public class Documento extends EntidadAbstracta {

@ManyToOne(fetch = FetchType.LAZY)
private Persona autor;
private String ruta;
@OneToMany(mappedBy = "documentoFirmado", cascade = CascadeType.ALL)
private Set<FirmaElectronica> firmas;

    public Documento() {
    }

    public Persona getAutor() {
        return autor;
    }

    public void setAutor(Persona autor) {
        this.autor = autor;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Set<FirmaElectronica> getFirmas() {
        return firmas;
    }

    @Override
    public String toString() {
        if (getAutor() == null) {
            return String.format("Documento ruta %s, sin autor", getRuta());
        } else {
            return String.format("Documento ruta %s, firmado por %s", getRuta(), getAutor().getNombre());
        }
    }

}
