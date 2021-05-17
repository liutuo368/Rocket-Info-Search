package com.example.a6442_group_project.Parser;

public class Token {
    public enum Type {
        UNKNOWN,
        COMPANY,
        COUNTRY,
        CONTENT,
        SEMICOLON,
        EQUAL
    };

    private String _token = "";
    private Type _type = Type.UNKNOWN;

    public Token(String token, Type type) {
        _token = token;
        _type = type;
    }

    public String token() {
        return _token;
    }

    public Type type() {
        return _type;
    }

}
