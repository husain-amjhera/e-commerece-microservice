//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.24 at 10:45:38 AM IST 
//


package com.yash.ecom.shippingcharge.soapData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="shippingCharge" type="{http://yash.com/ecom/shippingCharge}shippingCharge"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "shippingCharge"
})
@XmlRootElement(name = "getShippingChargeResponse")
public class GetShippingChargeResponse {

    @XmlElement(required = true)
    protected ShippingCharge shippingCharge;

    /**
     * Gets the value of the shippingCharge property.
     * 
     * @return
     *     possible object is
     *     {@link ShippingCharge }
     *     
     */
    public ShippingCharge getShippingCharge() {
        return shippingCharge;
    }

    /**
     * Sets the value of the shippingCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShippingCharge }
     *     
     */
    public void setShippingCharge(ShippingCharge value) {
        this.shippingCharge = value;
    }

}
