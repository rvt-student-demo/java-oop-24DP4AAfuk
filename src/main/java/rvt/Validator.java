package rvt;

public class Validator {
    public static boolean validName(String s) {
        if (s == null) return false;
        String t = s.trim();
        return t.matches("^[\\p{L} '-]{3,}$");
    }

    public static boolean validPersonalCode(String s) {
        if (s == null) return false;
        String t = s.trim();
        return t.matches("^[\\d]{6}-[\\d]{5}+$");
    }

    public static boolean validEmail(String s) {
        if (s == null) return false;
        String t = s.trim();
        return t.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    public static boolean validDate(String s) {
        if (s == null) return false;
        String t = s.trim();
        return t.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }
}
