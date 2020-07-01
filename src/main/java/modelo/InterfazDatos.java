package modelo;

public interface InterfazDatos {

    void altaPersona(Persona persona);
    void altaDocumento(Documento documento);

    void firmar(Documento documento, FirmaElectronica firmaElectronica);
    Persona buscarPersona(String dni);

    void destruirDocumento(Documento documento);
    void bajaPersona(Persona persona);
}
