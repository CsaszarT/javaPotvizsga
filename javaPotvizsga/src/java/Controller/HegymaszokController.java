
package Controller;

import Service.HegymaszokService;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;


public class HegymaszokController extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet HegymaszokController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet HegymaszokController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
            
            HegymaszokService ms = new HegymaszokService();
            // Entity manager létrehozása (adatbázis kapcsolathoz kell), hogy át tudjuk adni a függvényeknek
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("javaPotvizsgaPU"); // PU-t átírni a persistence.xml-ben szereplő névre!!!
            EntityManager em = emf.createEntityManager();
        
        
        //update
            if(request.getParameter("task").equals("updateMaszasiKepessegById")){
                //nem írunk a modellben függvényt, mert perzisztensen módosítunk
                    
                    JSONObject valasz = new JSONObject();
    
                    int id = Integer.parseInt(request.getParameter("id"));
                    int kepessegiSzint = Integer.parseInt(request.getParameter("kepsessegiSzint"));
                    JSONObject valasz = new JSONObject();
                    if(ms.updateMaszasiKepessegById(id,kepessegiSzint)){
                    valasz.put("result", "1");
                    valasz.put("msg", "Sikeres módosítás");
                    }
                    else{
                    valasz.put("result", "0");
                    valasz.put("msg", "Sikertelen módosítás");
                    }
                    out.print(valasz);
            }
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
