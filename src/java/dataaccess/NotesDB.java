/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Notes;

/**
 *
 * @author 810585
 */
public class NotesDB {

    public int insert(Notes notes) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(notes);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(NotesDB.class.getName()).log(Level.SEVERE, "Cannot insert " + notes.toString(), ex);
            throw new NotesDBException("Error inserting user");
        } finally {
            em.close();
        }
    }

    public int update(Notes notes) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(notes);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(NotesDB.class.getName()).log(Level.SEVERE, "Cannot update " + notes.toString(), ex);
            throw new NotesDBException("Error updating notes");
        } finally {
            em.close();
        }
    }

    public List<Notes> getAll() throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Notes> notes = em.createNamedQuery("Notes.findAll", Notes.class).getResultList();
            return notes;
        } finally {
            em.close();
        }
    }

    public Notes getNote(int noteid) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Notes notes = em.find(Notes.class, noteid);
            return notes;
        } finally {
            em.close();
        }
    }

    public int delete(Notes notes) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();

            // delete notes and the their notes too
            em.remove(em.merge(notes));
            trans.commit();

            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(NotesDB.class.getName()).log(Level.SEVERE, "Cannot delete " + notes.toString(), ex);
            throw new NotesDBException("Error deleting notes");
        } finally {
            em.close();
        }
    }
}
