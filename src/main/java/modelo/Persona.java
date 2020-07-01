package modelo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity(name = "persona")
public class Persona extends EntidadAbstracta {


    private String nombre;
    private String dni;
    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    private Set<Documento> documentos = new HashSet<>();
    @OneToMany(mappedBy = "personaAutora", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<FirmaElectronica> firmaElectronicas = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    private  Departamento departamento;


    public Persona() {
    }

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Set<Documento> getDocumentos() {
        return documentos;
    }

    public Set<FirmaElectronica> getFirmaElectronicas() {
        return firmaElectronicas;
    }
}




