package main;

import modelo.Documento;
import modelo.FirmaElectronica;
import modelo.InterfazDatos;
import modelo.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main implements InterfazDatos {

    public static void main(String[] args) {
        Main m = new Main();
        Persona p = new Persona();
        p.setDni("1234");
        p.setNombre("pepe");
        m.altaPersona(p);
        Documento d = new Documento();
        d.setAutor(p);
        d.setRuta("aaaa");
        m.altaDocumento(d);
    }


    public Main() {

    }

    @Override
    public void altaPersona(Persona persona) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiUnidad");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(persona);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void altaDocumento(Documento documento) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiUnidad");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(documento);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void firmar(Documento documento, FirmaElectronica firmaElectronica) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiUnidad");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            documento = em.merge(documento);
            Persona p = documento.getAutor();
            p.getFirmaElectronicas().add(firmaElectronica);
            em.persist(firmaElectronica);
            documento.getFirmas().add(firmaElectronica);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Persona buscarPersona(String dni) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiUnidad");
        EntityManager em = emf.createEntityManager();
        Persona persona = em.find(Persona.class, dni);
        em.close();
        return persona;
    }

    @Override
    public void destruirDocumento(Documento documento) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiUnidad");
        EntityManager em = emf.createEntityManager();
        Persona persona = documento.getAutor();

        try {
            em.getTransaction().begin();
            em.remove(documento);
            if (persona != null) {
                em.refresh(persona);
            }
            for (FirmaElectronica f : documento.getFirmas()) {
                f.setDocumentoFirmado(null);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void bajaPersona(Persona persona) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiUnidad");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            persona = em.merge(persona);
            em.remove(persona);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
