package com.securin.xmltest.DataModel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Cpe23Item {

    @JacksonXmlProperty(isAttribute = true,localName = "name")
    private String cpe23Uri;

    @JacksonXmlProperty(localName = "deprecation")
    private Deprecation deprecation;

}
