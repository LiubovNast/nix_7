package com.alevel.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentSkipListSet;

@WebServlet("/")
public class AppServlets extends HttpServlet {

    private final ConcurrentSkipListSet<String> ipAddress = new ConcurrentSkipListSet();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        String current = req.getRemoteHost() + " :: " + req.getHeader("user-agent");
        ipAddress.add(current);
        ipAddress.forEach(m -> {
            if (!m.equals(current)) {
                printWriter.println(m + "<br>");
            } else {
                printWriter.println("<b>" + m + "</b br>");
            }
        });
        printWriter.close();
    }
}
