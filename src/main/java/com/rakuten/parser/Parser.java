package com.rakuten.parser;

import com.rakuten.model.Item;

public interface Parser {

    Item parse(String input);
}
