package regsys;

import java.util.List;

import rvt.Student;

public class TableFormatter {
    public static void printStudents(List<Student> students) {
        String[] headers = {"Name", "Surname", "Email", "ID", "RegDate"};
        int cols = headers.length;
        int[] widths = new int[cols];
        for (int i = 0; i < cols; i++) widths[i] = headers[i].length();

        for (Student s : students) {
            widths[0] = Math.max(widths[0], safeLen(s.name));
            widths[1] = Math.max(widths[1], safeLen(s.surname));
            widths[2] = Math.max(widths[2], safeLen(s.email));
            widths[3] = Math.max(widths[3], safeLen(s.IDnum));
            widths[4] = Math.max(widths[4], safeLen(s.registrationDate));
        }

        String headerLine = buildRow(headers, widths);
        String border = repeat('-', headerLine.length());

        System.out.println(border);
        System.out.println(headerLine);
        System.out.println(border);

        for (Student s : students) {
            String[] cells = {s.name, s.surname, s.email, s.IDnum, s.registrationDate};
            System.out.println(buildRow(cells, widths));
            System.out.println(border);
        }
    }

    private static int safeLen(String s) {
        return s == null ? 0 : s.length();
    }

    private static String buildRow(String[] cells, int[] widths) {
        StringBuilder sb = new StringBuilder();
        sb.append("|");
        for (int i = 0; i < cells.length; i++) {
            String content = cells[i] == null ? "" : cells[i];
            sb.append(" ");
            sb.append(padRight(content, widths[i]));
            sb.append(" |");
        }
        return sb.toString();
    }

    private static String padRight(String s, int n) {
        if (s.length() >= n) return s;
        StringBuilder sb = new StringBuilder(s);
        for (int i = s.length(); i < n; i++) sb.append(' ');
        return sb.toString();
    }

    private static String repeat(char c, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(c);
        return sb.toString();
    }
}
