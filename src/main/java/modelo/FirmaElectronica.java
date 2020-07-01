package modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity(name="firma")
public class FirmaElectronica extends EntidadAbstracta {

    private String hash;
    @ManyToOne(fetch= FetchType.LAZY)
    private Persona personaAutora;
    @ManyToOne(fetch = FetchType.LAZY)
    private Documento documentoFirmado;

    public FirmaElectronica() {
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Persona getPersonaAutora() {
        return personaAutora;
    }

    public void setPersonaAutora(Persona personaAutora) {
        this.personaAutora = personaAutora;
    }

    public Documento getDocumentoFirmado() {
        return documentoFirmado;
    }

    public void setDocumentoFirmado(Documento documentoFirmado) {
        this.documentoFirmado = documentoFirmado;
    }
}
