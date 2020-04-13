package com.rakuten.parser;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CarParser extends GeneralParser {

    final static private String CAR_CATEGORY = "BMW";

    @Override
    protected String getName(Document document) {
        Element title = document.getElementById("product-title");
        if (Objects.nonNull(title)) {
            return getOrEmpty(document.getElementById("product-title").children().first());
        }
        return StringUtils.EMPTY;
    }

    @Override
    protected String getCategory(Document document) {
        return CAR_CATEGORY;
    }

    @Override
    protected String getPrice(Document document) {
        Element price = document.getElementById("product-price");
        if (Objects.nonNull(price)) {
            return getOrEmpty(price.select("span:containsOwn($)").last());
        }
        return StringUtils.EMPTY;
    }
}
