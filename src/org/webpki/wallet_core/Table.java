package org.webpki.wallet_core;

import java.util.TreeMap;

import org.webpki.cbor.CBORObject;


public class Table {

    static class TableElement {
        String name;
        String description;
        Types value;
        TableElement(String name, String description, Types value) {
            this.name = name;
            this.description = description;
            this.value = value;
        }
    }

    TreeMap<CBORObject, TableElement> elements = new TreeMap<>();

    Table add(CBORObject cborObject, String name, Types type, String description) {
        if (elements.put(cborObject, new TableElement(name, description, type)) != null) {
            throw new RuntimeException("duplicate");
        }
        return this;
    }

    String getTableString() {
        StringBuilder s = new StringBuilder("<div class='webpkifloat'>" +
             "<table class='webpkitable' style='width:52em'>" +
                "<tr><th>Name</th><th>Label</th><th>Value</th>" +
                    "<th style='width:100%'>Description</th></tr>");
        for (CBORObject key : elements.keySet()) {
            TableElement te = elements.get(key);
            s.append("<tr><td><kbd>")
             .append(te.name)
             .append("</kbd></td><td style='text-align:center'><code>")
             .append(key.toString())
             .append("</code></td><td style='text-align:center'><code>")
             .append(te.value.getHTML())
             .append("</code></td><td>")
             .append(te.description)
             .append("</td></tr>");
        }
        return s.append("</table></div>").toString();
    }
}
