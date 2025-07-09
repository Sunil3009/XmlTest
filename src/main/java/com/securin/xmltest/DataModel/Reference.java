package com.securin.xmltest.DataModel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;

@Data
public class Reference {

    @JacksonXmlProperty(isAttribute = true,localName = "href")
    private String href;

    @JacksonXmlText
    private String text;
}
