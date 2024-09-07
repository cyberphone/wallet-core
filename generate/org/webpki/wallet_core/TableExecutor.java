package org.webpki.wallet_core;

abstract class TableExecutor {

    int innerCount;

    int outerCount;

    abstract String getTableString();

    abstract String getTitle();

    String getBeforeText() {
        return null;
    }

    String getAfterText() {
        return null;
    }

    String getLink() {
        return getTitle().toLowerCase().replace(' ', '-');
    }

    String getTCLink() {
        return String.valueOf(outerCount) + "." + String.valueOf(innerCount) + "." + getLink();
    }

    public String getTCTitle() {
        return getTitle().replace(" ", "&nbsp;");
    }

}