package com.fitness.servlet;

import com.fitness.util.MemberFileUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/viewMembers")
public class ViewMembersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filePath = MemberFileUtil.getFilePath(getServletContext().getRealPath("/"));
        System.out.println("VIEW MEMBER FILE PATH: " + filePath);
        request.setAttribute("members", MemberFileUtil.getAllMembers(filePath));
        request.getRequestDispatcher("viewMembers.jsp").forward(request, response);
    }
}