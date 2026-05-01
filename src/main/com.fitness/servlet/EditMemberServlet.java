package com.fitness.servlet;

import com.fitness.model.Member;
import com.fitness.util.MemberFileUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/editMember")
public class EditMemberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memberId = request.getParameter("id");
        String filePath = MemberFileUtil.getFilePath(getServletContext().getRealPath("/"));
        request.setAttribute("member", MemberFileUtil.getMemberById(filePath, memberId));
        request.getRequestDispatcher("editMember.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memberId = request.getParameter("memberId");
        String name = request.getParameter("name");
        String contact = request.getParameter("contact");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));
        String address = request.getParameter("address");

        String filePath = MemberFileUtil.getFilePath(getServletContext().getRealPath("/"));
        Member updatedMember = new Member(memberId, name, contact, email, gender, age, address);
        MemberFileUtil.updateMember(filePath, updatedMember);

        response.sendRedirect("viewMembers");
    }
}