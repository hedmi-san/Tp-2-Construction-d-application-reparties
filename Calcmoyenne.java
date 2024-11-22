/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author LAPTOP SPIRIT
 */
@WebServlet(name = "Calcmoyenne", urlPatterns = {"/Calcmoyenne"})
public class Calcmoyenne extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Calcmoyenne</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Calcmoyenne at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    private double calculateAverage(double[] grades, double[] coefficients) {
        double totalWeightedGrades = 0;
        double totalCoefficients = 0;
        for (int i = 0; i < grades.length; i++) {
            totalWeightedGrades += grades[i] * coefficients[i];
            totalCoefficients += coefficients[i];
        }
        return totalCoefficients != 0 ? totalWeightedGrades / totalCoefficients : 0;
    }
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
        try {
                double[] grades = new double[5];
                double[] coefficients = new double[5];
                for (int i = 0; i < 5; i++) {
                    grades[i] = Double.parseDouble(request.getParameter("note" + (i + 1)));
                    coefficients[i] = Double.parseDouble(request.getParameter("cof" + (i + 1)));
                }
                double moyenne = calculateAverage(grades, coefficients);

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head><title>Average Calculation</title></head>");
                out.println("<body>");
                out.println("<h1>Votre moyenne est: " + String.format("%.2f", moyenne) + "</h1>");
                if (moyenne >= 10.0) {
                    out.println("<h2>Vous êtes admis</h2>");
                } else {
                    out.println("<h2>Vous êtes ajourné</h2>");
                }
                out.println("</body>");
                out.println("</html>");
        } catch (NumberFormatException e) {
            out.println("<p>Error: Please enter valid numbers.</p>");
        }finally {
            out.close();
        }
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
