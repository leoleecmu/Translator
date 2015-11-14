/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haonanli.cmu.ds;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author haonan
 */
public class Translater extends HttpServlet {
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        String translatedText = (request.getPathInfo().substring(1));
        
        String APIkey="trnsl.1.1.20151114T002356Z.7bde6816ffbce10c.31a7c36e0f4cd6b8133fe3e436ba0f6929233e3e";
        URL url = new URL("https://translate.yandex.net/api/v1.5/tr/translate?key="+APIkey+"&lang=en-de&text="+translatedText);
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
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(status); 
    }

  
   
}
