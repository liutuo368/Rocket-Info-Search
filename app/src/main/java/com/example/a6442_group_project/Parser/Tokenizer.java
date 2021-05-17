package com.example.a6442_group_project.Parser;

public class Tokenizer {
    private String _buffer;
    private Token currentToken;

    public Tokenizer(String text) {
        _buffer = text;
        next();
    }

    public void next() {
        _buffer = _buffer.trim(); //remove space

        if(_buffer.isEmpty()) {
            currentToken = null;
            return;
        }

        char firstChar = _buffer.charAt(0);
        if(firstChar == ';') {
            currentToken = new Token(";", Token.Type.SEMICOLON);
        } else if(firstChar == '=') {
            currentToken = new Token("=", Token.Type.EQUAL);
        } else {
            int i = 0;
            while(i < _buffer.length() && (_buffer.charAt(i) != ';' && _buffer.charAt(i) != '=')) {
                i++;
            }
            String s = _buffer.substring(0, i);
            s = s.trim();
            if(s.equalsIgnoreCase("country")) {
                currentToken = new Token(s, Token.Type.COUNTRY);
            } else if(s.equalsIgnoreCase("company")) {
                currentToken = new Token(s, Token.Type.COMPANY);
            } else {
                currentToken = new Token(s, Token.Type.CONTENT);
            }
        }
        int tokenLen = currentToken.token().length();
        _buffer = _buffer.substring(tokenLen);
    }

    public Token current() {
        return currentToken;
    }

    public boolean hasNext() {
        return currentToken != null;
    }
}
