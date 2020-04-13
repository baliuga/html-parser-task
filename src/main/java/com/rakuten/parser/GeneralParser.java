package com.rakuten.parser;

import com.rakuten.model.Item;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GeneralParser implements Parser {

    @Override
    public Item parse(String input) {
        Document document = Jsoup.parse(input);

        String name = getName(document);
        String category = getCategory(document);
        String price = getPrice(document);

        return new Item(name, category, price);
    }

    protected String getName(Document document) {
        Element name = document.getElementById("productTitle");
        return getOrEmpty(name);
    }

    protected String getCategory(Document document) {
        StringBuilder category = new StringBuilder();
        Element path = document.getElementById("wayfinding-breadcrumbs_feature_div");
        if (Objects.isNull(path)) {
            return StringUtils.EMPTY;
        }
        path.getElementsByClass("a-list-item").forEach(item -> category.append(item.text()).append(" "));

        return category.toString().trim();
    }

    protected String getPrice(Document document) {
        Element element = document.select("*:containsOwn(Buy new)").first();
        if (Objects.nonNull(element)) {
            if (Objects.isNull(element.nextElementSibling())) {
                element = element.parent();
            }
            return getOrEmpty(element.nextElementSibling().getElementsByTag("span").last());
        }
        return StringUtils.EMPTY;
    }

    protected String getOrEmpty(Element element) {
        return Objects.nonNull(element) ? element.text() : StringUtils.EMPTY;
    }
}
