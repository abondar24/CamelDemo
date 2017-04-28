package org.abondar.experimental.cameldemo;


import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;


//comment jaxb annotations for CSV usage
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@CsvRecord(separator = ",",crlf = "UNIX")
public class TextBean {
    @XmlAttribute
    @DataField(pos=1)
    private String id ;

    @XmlAttribute
    @DataField(pos=2, precision = 2)
    private String customerId;

    @XmlAttribute
    @DataField(pos=3)
    private String date;

    @XmlAttribute
    @DataField(pos=4)
    private String items;

    @XmlAttribute
    private String[] itemIds;

    public TextBean(String id, String customerId, String date, String items, String[] itemIds) {
        this.id = id;
        this.customerId = customerId;
        this.date = date;
        this.items = items;
        this.itemIds = itemIds;
    }

    public TextBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String[] getItemIds() {
        return itemIds;
    }

    public void setItemIds(String[] itemIds) {
        this.itemIds = itemIds;
    }

    @Override
    public String toString() {
        return id + customerId + date +  items + Arrays.toString(itemIds);
    }
}
