//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.01.13 at 08:24:23 PM WET 
//

package com.lyncode.xoai.dataprovider.xml.oaipmh;

import com.lyncode.xoai.dataprovider.exceptions.WritingXmlException;
import com.lyncode.xoai.dataprovider.xml.XMLWritable;
import com.lyncode.xoai.dataprovider.xml.XmlOutputContext;

import javax.xml.bind.annotation.*;
import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.List;

/**
 * A header has a unique identifier, a datestamp, and withSpec(s) in case the
 * item from which the record is disseminated belongs to set(s). the header can
 * carry a deleted status indicating that the record is deleted.
 *
 *
 * Java class for headerType complex type.
 *
 *
 * The following schema fragment specifies the expected content contained within
 * this class.
 *














 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "headerType", propOrder = {"identifier", "datestamp",
        "setSpec"})
public class HeaderType implements XMLWritable {

    @XmlElement(required = true)
    protected String identifier;
    @XmlElement(required = true)
    protected String datestamp;
    protected List<String> setSpec;
    @XmlAttribute(name = "status")
    protected StatusType status;

    /**
     * Gets the value of the identifier property.
     *
     * @return possible object is ;
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the value of the identifier property.
     *
     * @param value allowed object is ;
     */
    public void setIdentifier(String value) {
        this.identifier = value;
    }

    /**
     * Gets the value of the datestamp property.
     *
     * @return possible object is ;
     */
    public String getDatestamp() {
        return datestamp;
    }

    /**
     * Sets the value of the datestamp property.
     *
     * @param value allowed object is ;
     */
    public void setDatestamp(String value) {
        this.datestamp = value;
    }

    /**
     * Gets the value of the withSpec property.
     *
     *
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
      method for the withSpec property.
     *
     *
     * For example, to add a new item, do as follows:
     *

     * getSetSpec().add(newItem);

     *
     *
     *
     * Objects of the following type(s) are allowed in the list ;
     */
    public List<String> getSetSpec() {
        if (setSpec == null) {
            setSpec = new ArrayList<String>();
        }
        return this.setSpec;
    }

    /**
     * Gets the value of the status property.
     *
     * @return possible object is ;
     */
    public StatusType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value allowed object is ;
     */
    public void setStatus(StatusType value) {
        this.status = value;
    }

    @Override
    public void write(XmlOutputContext context) throws WritingXmlException {
        try {
            if (this.status != null)
                context.getWriter().writeAttribute("status", this.status.value());

            if (this.identifier != null) {
                context.getWriter().writeStartElement("identifier");
                context.getWriter().writeCharacters(this.identifier);
                context.getWriter().writeEndElement();
            }

            if (this.datestamp != null) {
                context.getWriter().writeStartElement("datestamp");
                context.getWriter().writeCharacters(this.datestamp);
                context.getWriter().writeEndElement();
            }

            for (String setSpec : this.getSetSpec()) {
                context.getWriter().writeStartElement("setSpec");
                context.getWriter().writeCharacters(setSpec);
                context.getWriter().writeEndElement();
            }
        } catch (XMLStreamException e) {
            throw new WritingXmlException(e);
        }
    }

}