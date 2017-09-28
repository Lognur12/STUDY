package ru.javacourse.web;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by d.gurov on 27.07.2017.
 */
@WebServlet(name = "HelloImage", urlPatterns = "/image")
public class HelloImage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        response.setContentType("image/jpg");
        OutputStream outputStream = response.getOutputStream();
        try {
            URL imageURL = HelloImage.class.getResource("petuh3.jpg");
            BufferedImage bi = ImageIO.read(imageURL);
            ImageIO.setUseCache(false);
            ImageIO.write(bi, "jpg", outputStream);
        } finally {
            outputStream.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}

