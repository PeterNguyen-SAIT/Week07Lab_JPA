/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import static java.time.OffsetTime.now;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Notes;
import services.NoteService;

/**
 *
 * @author 810585
 */
public class NoteServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NoteService ns = new NoteService();

        String action = request.getParameter("action");
        if (action != null && action.equals("edit")) {
            String count = request.getParameter("selectedEdit");
            int index = Integer.parseInt(count);
            try {
                Notes notes = ns.get(index);
                request.setAttribute("currentValue", index);
                request.setAttribute("selectedTitle", notes.getTitle());
                request.setAttribute("selectedContents", notes.getContents());
            } catch (Exception ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        List<Notes> notes = null;
        try {
            notes = ns.getAll();
        } catch (Exception ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("notes", notes);
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String selectedTitle = request.getParameter("titleJSP");
        String selectedContent = request.getParameter("contentJSP");
        String count = request.getParameter("tracker");

        NoteService ns = new NoteService();
        Date date = new Date();

        System.out.println(selectedTitle);
        System.out.println(selectedContent);

        try {
            int index = Integer.parseInt(count);
            if (action.equals("delete")) {
                ns.delete(index);
            } else if (action.equals("save")) {
                ns.update(date, selectedTitle, selectedContent, index);
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Whoops.  Could not perform that action.");
        }

        List<Notes> notes = null;
        try {
            notes = ns.getAll();
        } catch (Exception ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("notes", notes);
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
