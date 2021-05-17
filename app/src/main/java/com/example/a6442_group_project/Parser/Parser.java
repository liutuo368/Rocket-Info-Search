package com.example.a6442_group_project.Parser;

public class Parser {
    Tokenizer _tokenizer;

    public Parser(Tokenizer tokenizer) {
        _tokenizer = tokenizer;
    }

    public SearchCondition parseSearchInfo() {
        SearchCondition searchCondition = new SearchCondition();
        while (_tokenizer.hasNext()) {
            switch (_tokenizer.current().type()) {
                case COMPANY:
                    _tokenizer.next();
                    _tokenizer.next();
                    searchCondition.company = _tokenizer.current().token();
                    _tokenizer.next();
                    break;
                case COUNTRY:
                    _tokenizer.next();
                    _tokenizer.next();
                    searchCondition.country = _tokenizer.current().token();
                    _tokenizer.next();
                    break;
                default:
                    _tokenizer.next();
                    break;
            }
        }
        return searchCondition;
    }
}
