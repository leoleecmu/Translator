/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hananli.cmu.p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author haonan
 */
@WebServlet(name = "Translator", urlPatterns = {"/Translator/*"})
public class Translator extends HttpServlet {
  
   public String trans(String str) throws Exception{
        
        String APIkey="trnsl.1.1.20151114T002356Z.7bde6816ffbce10c.31a7c36e0f4cd6b8133fe3e436ba0f6929233e3e";
        URL url = new URL("https://translate.yandex.net/api/v1.5/tr/translate?key="+APIkey+"&lang=en-de&text="+str);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String re="";
        String line=br.readLine();
        while(line!=null){
                re=re+line;
                line=br.readLine();
        }
        int statusL=re.indexOf("<text>");
        int statusR=re.indexOf("</text>",statusL+6);
        String status=re.substring(statusL+6,statusR);
       return status;
   }
    
    // GET returns a value given a key
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String result = "";
        
        // The name is on the path /name so skip over the '/'
        String translatedText = (request.getPathInfo()).substring(1);
        
        // Things went well so set the HTTP response code to 200 OK
        response.setStatus(200);
            // tell the client the type of the response
        response.setContentType("text/plain;charset=UTF-8");
        
        try {
            result=trans(translatedText);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
       
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(result); 
               
    }
    
 }