package com.fitness.servlet;

import com.fitness.util.MemberFileUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/searchMember")
public class SearchMemberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String filePath = MemberFileUtil.getFilePath(getServletContext().getRealPath("/"));

        if (keyword != null && !keyword.trim().isEmpty()) {
            request.setAttribute("members", MemberFileUtil.searchMembers(filePath, keyword));
        }

        request.getRequestDispatcher("searchMember.jsp").forward(request, response);
    }
}