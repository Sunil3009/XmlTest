package com.securin.xmltest.Batch;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.securin.xmltest.DataModel.CpeItem;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


@Component
public class Reader implements ItemReader<CpeItem> {

    XmlMapper xmlMapper = new XmlMapper();
    XMLStreamReader reader;

    public Reader() throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        this.reader= factory.createXMLStreamReader(new FileInputStream("C:\\Users\\Sunil\\Documents\\SecurinPreparation\\XmlTest\\src\\main\\resources\\Data\\official-cpe-dictionary_v2.3.xml"));
    }

    @Override
    public CpeItem read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        int event= reader.next();
        while(event!= XMLStreamConstants.END_DOCUMENT){
            if(event==XMLStreamReader.START_ELEMENT && reader.getLocalName().equals("cpe-item")){
                return xmlMapper.readValue(reader, CpeItem.class);
            }
            event = reader.next();
        }
        return null;
    }
}
