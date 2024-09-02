package org.webpki.wallet_core;

import java.util.TreeMap;

import org.webpki.cbor.CBORObject;


public class Table {

    static class TableElement {
        String name;
        String description;
        Types type;
        TableElement(String name, String description, Types type) {
            this.name = name;
            this.description = description;
            this.type = type;
        }
    }

    TreeMap<CBORObject, TableElement> elements = new TreeMap<>();

    Table add(CBORObject cborObject, String name, Types type, String description) {
        if (elements.put(cborObject, new TableElement(name, description, type)) != null) {
            throw new RuntimeException("duplicate");
        }
        return this;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("<div class='webpkifloat'>" +
             "<table class='webpkitable' style='width:52em'>" +
                "<tr><th>Label</th><th>Name</th><th>Type</th>" +
                    "<th style='width:100%'>Description</th></tr>");
        for (CBORObject key : elements.keySet()) {
            TableElement te = elements.get(key);
            s.append("<tr><td style='text-align:center'><code>")
             .append(key.toString())
             .append("</code></td><td><code>")
             .append(te.name)
             .append("</code></td><td style='text-align:center'><code>")
             .append(te.type.getHTML())
             .append("</code></td><td>")
             .append(te.description)
             .append("</td></tr>");
        }
        return s.append("</table></div>").toString();
    }
}
