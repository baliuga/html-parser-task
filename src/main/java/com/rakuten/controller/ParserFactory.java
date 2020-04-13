package com.rakuten.controller;

import com.rakuten.parser.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParserFactory {

    @Autowired
    private GeneralParser generalParser;
    @Autowired
    private AppleParser appleParser;
    @Autowired
    private CarParser carParser;

    public Parser getParser(ParserType type) {
        if (type == ParserType.APPLE) {
            return appleParser;
        } else if (type == ParserType.CAR) {
            return carParser;
        } else {
            return generalParser;
        }
    }
}
