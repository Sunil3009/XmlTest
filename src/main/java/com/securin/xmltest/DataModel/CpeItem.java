package com.securin.xmltest.DataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpeItem {

    @JacksonXmlProperty(isAttribute = true,localName = "name")
    private String cpe22Uri;

    @JacksonXmlProperty(isAttribute = true,localName = "deprecated")
    private String deprecated;

    @JacksonXmlProperty(isAttribute = true,localName = "deprecation_date")
    private String deprecationDate;

    @JacksonXmlProperty(localName = "title")
    private Title title;

    @JacksonXmlElementWrapper(localName = "references")
    @JacksonXmlProperty(localName = "reference")
    private List<Reference> references;



    @JacksonXmlProperty(localName = "cpe23-item")
    private Cpe23Item cpe23Item;

}
