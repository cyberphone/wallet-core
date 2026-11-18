package org.webpki.wallet_core;

abstract class TableExecutor {

    abstract String getTableString();

    abstract String getTitle();

    String getBeforeText() {
        return null;
    }

    String getAfterText() {
        return null;
    }

}