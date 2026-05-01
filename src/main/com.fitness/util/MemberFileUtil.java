package com.fitness.util;

import com.fitness.model.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MemberFileUtil {

    public static String getFilePath(String basePath) {
        return basePath + File.separator + "data" + File.separator + "members.txt";
    }

    public static void addMember(String filePath, Member member) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(member.toString());
            writer.newLine();
        }
    }

    public static List<Member> getAllMembers(String filePath) throws IOException {
        List<Member> members = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return members;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 7) {
                    members.add(new Member(
                            data[0],
                            data[1],
                            data[2],
                            data[3],
                            data[4],
                            Integer.parseInt(data[5]),
                            data[6]
                    ));
                }
            }
        }
        return members;
    }


    public static Member getMemberById(String filePath, String memberId) throws IOException {
        List<Member> members = getAllMembers(filePath);
        for (Member member : members) {
            if (member.getMemberId().equalsIgnoreCase(memberId)) {
                return member;
            }
        }
        return null;
    }

    public static List<Member> searchMembers(String filePath, String keyword) throws IOException {
        List<Member> result = new ArrayList<>();
        List<Member> members = getAllMembers(filePath);
        String lower = keyword.toLowerCase();

        for (Member member : members) {
            if (member.getMemberId().toLowerCase().contains(lower) ||
                    member.getName().toLowerCase().contains(lower) ||
                    member.getContact().toLowerCase().contains(lower) ||
                    member.getEmail().toLowerCase().contains(lower)) {
                result.add(member);
            }
        }
        return result;
    }

    public static boolean updateMember(String filePath, Member updatedMember) throws IOException {
        List<Member> members = getAllMembers(filePath);
        boolean updated = false;

        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getMemberId().equalsIgnoreCase(updatedMember.getMemberId())) {
                members.set(i, updatedMember);
                updated = true;
                break;
            }
        }

        if (updated) {
            rewriteMembers(filePath, members);
        }
        return updated;
    }

    public static boolean deleteMember(String filePath, String memberId) throws IOException {
        List<Member> members = getAllMembers(filePath);
        boolean removed = members.removeIf(member -> member.getMemberId().equalsIgnoreCase(memberId));

        if (removed) {
            rewriteMembers(filePath, members);
        }
        return removed;
    }

    private static void rewriteMembers(String filePath, List<Member> members) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Member member : members) {
                writer.write(member.toString());
                writer.newLine();
            }
        }
    }
}