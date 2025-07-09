package com.securin.xmltest.DataModel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Deprecation {

    @JacksonXmlProperty(isAttribute = true,localName = "date")
    private String date;

    @JacksonXmlProperty(localName = "deprecated-by")
    private DeprecatedBy deprecatedBy;
}
