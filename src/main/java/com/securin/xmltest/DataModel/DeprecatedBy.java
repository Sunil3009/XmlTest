package com.securin.xmltest.DataModel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class DeprecatedBy {

    @JacksonXmlProperty(isAttribute = true,localName = "name")
    private String name;

    @JacksonXmlProperty(isAttribute = true,localName = "type")
    private String type;
}
