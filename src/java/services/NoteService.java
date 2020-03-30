/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.NotesDB;
import java.util.Date;
import java.util.List;
import models.Notes;

/**
 *
 * @author 810585
 */
public class NoteService {
    private NotesDB notesDB;

    public NoteService() {
        notesDB = new NotesDB();
    }

    public Notes get(String title) throws Exception {
        return notesDB.getTitle(title);
    }

    public List<Notes> getAll() throws Exception {
        return notesDB.getAll();
    }

    public int update(Date datecreated, String title, String contents) throws Exception {
        Notes notes = get(title);
        notes.setDatecreated(datecreated);
        notes.setTitle(title);
        notes.setContents(contents);
        return notesDB.update(notes);
    }

    public int delete(String title) throws Exception {
        Notes deletedNote = notesDB.getTitle(title);
        return notesDB.delete(deletedNote);
    }

    public int insert(Date datecreated, String title, String contents) throws Exception {
        Notes notes = new Notes(datecreated, title, contents);
        return notesDB.insert(notes);
    }
}
