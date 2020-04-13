package com.rakuten.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class AppleParser extends GeneralParser {

    final static private String APPLE_CATEGORY = "Apple";

    @Override
    protected String getCategory(Document document) {
        return APPLE_CATEGORY;
    }

    @Override
    protected String getPrice(Document document) {
        Element price = document.getElementById("priceblock_ourprice");
        return getOrEmpty(price);
    }
}
