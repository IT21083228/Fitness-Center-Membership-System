package com.fitness.servlet;

import com.fitness.model.Member;
import com.fitness.util.MemberFileUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/addMember")
public class AddMemberServlet extends HttpServlet {
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
        System.out.println("ADD MEMBER FILE PATH: " + filePath);

        if (MemberFileUtil.getMemberById(filePath, memberId) != null) {
            request.setAttribute("error", "Member ID already exists.");
            request.getRequestDispatcher("addMember.jsp").forward(request, response);
            return;
        }


        Member member = new Member(memberId, name, contact, email, gender, age, address);
        MemberFileUtil.addMember(filePath, member);

        response.sendRedirect("viewMembers");
    }
}