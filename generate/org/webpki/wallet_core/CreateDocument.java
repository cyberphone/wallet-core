package org.webpki.wallet_core;

import org.webpki.util.IO;
import org.webpki.util.UTF8;

public class CreateDocument {

    String template;

    void replace(String holder, TableExecutor executor) {
        String title = executor.getTitle();
        template = template.replace(holder, 
            "<h5 id='" +
            title.toLowerCase().replace(" ","-") +
            "'>" +
            title +
            "</h5>" +
            executor.getTableString());
    }

    CreateDocument(String templateFileName, String documentFileName) {
        template = UTF8.decode(IO.readFile(templateFileName));

        replace("${PAYMENT_REQUEST}", new PaymentRequest());
        replace("${PAYMENT_INFO}", new PaymentInfo());
        replace("${AUTHORIZATION_RESPONSE}", new SignedAuthorization());

        IO.writeFile(documentFileName, template);
    }
  
    public static void main(String[] args) {
        new CreateDocument(args[0], args[1]);
    }
}