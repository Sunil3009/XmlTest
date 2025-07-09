package com.securin.xmltest.DataModel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;

@Data
public class Title {

    @JacksonXmlProperty(isAttribute = true,localName = "lang")
    private String lang;

    @JacksonXmlText
    private String text;
}
