package com.rakuten.controller;

import com.rakuten.model.Item;
import com.rakuten.parser.*;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ParserController {

    @Autowired
    private ParserFactory parserFactory;

    @GetMapping("/parseHtml")
    public Item parseHtml(@RequestParam(name="type") String type, @RequestParam(name="url") String url) {
        return parserFactory.getParser(ParserType.valueOf(type.toUpperCase())).parse(getPageHtml(url));
    }

    private String getPageHtml(String url) {
        try {
            return Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36")
                    .get().html();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }
}
