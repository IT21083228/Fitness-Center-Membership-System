package com.fitness.servlet;

import com.fitness.util.MemberFileUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteMember")
public class DeleteMemberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memberId = request.getParameter("id");
        String filePath = MemberFileUtil.getFilePath(getServletContext().getRealPath("/"));
        MemberFileUtil.deleteMember(filePath, memberId);
        response.sendRedirect("viewMembers");
    }
}