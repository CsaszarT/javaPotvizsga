package Controller;

import Service.HegyekService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class HegyekController extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet HegyekController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet HegyekController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        
            HegyekService hs = new HegyekService();
            // Entity manager létrehozása (adatbázis kapcsolathoz kell), hogy át tudjuk adni a függvényeknek
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("javaPotvizsgaPU"); // PU-t átírni a persistence.xml-ben szereplő névre!!!
            EntityManager em = emf.createEntityManager();
            
            if(request.getParameter("task").equals("ujHegy")){
                // VALIDÁLÁS, SQL INJECTION VÉDELEM 
                String szelessegiKoordinata = request.getParameter("szelessegiKoordinata");
                String hosszusagiKoordinata = request.getParameter("hosszusagiKoordinata");
                int magassag = Integer.parseInt(request.getParameter("magassag"));
                int megmaszasNehezsegiFoka = Integer.parseInt(request.getParameter("megmaszasNehezsegiFoka"));// Inté castoljuk mert a JSON-ben string
                // üres JSON object amit majd visszaküldünk a Wiev réteg számára.
                JSONObject valasz = new JSONObject();
                // meghívjuk a Service rétegben lévő fvg-t. 
                if(hs.ujHegy(szelessegiKoordinata, hosszusagiKoordinata, magassag, megmaszasNehezsegiFoka, em)){
                    valasz.put("msg", "Sikeres mentés");
                }
                else{
                    valasz.put("msg", "Hiba");
                }
                // A válasz JSON objectet visszaküldjük a wiev ( esetünkben majd a Postman) számára.
                out.print(valasz.toString());
            }//end of ujHegy
            
            
            if(request.getParameter("task").equals("getOsszesHegy")){
                JSONArray a = hs.hegyek(em);
                out.print(a.toString());
            }//end of getHegyek
            
            
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
