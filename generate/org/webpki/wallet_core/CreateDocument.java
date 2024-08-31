package org.webpki.wallet_core;

import org.webpki.util.IO;
import org.webpki.util.UTF8;

public class CreateDocument {

    String template;

    void replace(TableExecutor executor) {
        String oldTemplate = template;
        String holder = "${" + executor.getLink() + "}";
        template = template.replace(holder, 
            "<h5 id='" +
            executor.getLink() +
            "'>" +
            executor.getTitle() +
            "</h5>" +
            executor.getTableString());
        if (oldTemplate.length() == template.length()) {
            throw new RuntimeException("Did not find: " +holder);
        }
    }

    CreateDocument(String templateFileName, String documentFileName) {
        template = UTF8.decode(IO.readFile(templateFileName));

        replace(new PaymentRequest());
        replace(new SignedAuthorization());
        replace(new AuthorizationResponse());
        replace(new PassThroughData());
        replace(new ServiceProvider());

        IO.writeFile(documentFileName, template);
    }
  
    public static void main(String[] args) {
        new CreateDocument(args[0], args[1]);
    }
}