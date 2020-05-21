package com.supersu.inventory.models;

import java.net.IDN;

public class ItemModel {
    public static final String TABLE_NAME = "dailyAddProduct";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_VENDORNAME = "vendorName";
    public static final String COLUMN_TP_NUMBER= "tpNumber";
    public static final String COLUMN_BILL_NUMBER= "billNumber";
    public static final String COLUMN_RECIEVED_DATE= "recievedDate";
    public static final String COLUMN_INVOICE_DATE= "invoiceDate";
    public static final String COLUMN_TP_DATE= "tpDate";
    public static final String COLUMN_ITEM_NAME= "itemName";
    public static final String COLUMN_BATCH_NUMBER= "batchNumber";
    public static final String COLUMN_CASE_NUMBER= "caseNumber";
    public static final String COLUMN_PACKING= "packing";
    public static final String COLUMN_TYPE= "typeNumber";
    public static final String COLUMN_BOTTLE= "bottle";
    public static final String COLUMN_EXCISE_RATE= "exciseRate";
    public static final String COLUMN_PURCHASE_RATE= "purchaseRate";
    public static final String COLUMN_TOTAL_AMOUNT = "totalAmount";

    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" "+
            "("+COLUMN_ID+" INT(20)  PRIMARY KEY AUTOINCREMENT, " +COLUMN_VENDORNAME+
            " VARCHAR(100) NOT NULL , " + COLUMN_TP_NUMBER +
            " INT(100) NOT NULL ," + COLUMN_BILL_NUMBER+
            " INT NOT NULL , " + COLUMN_RECIEVED_DATE+
            " DATE NOT NULL , " + COLUMN_INVOICE_DATE +
            " DATE NOT NULL , " + COLUMN_TP_DATE +
            " DATE NOT NULL , " + COLUMN_ITEM_NAME +
            " VARCHAR(100) NOT NULL , " + COLUMN_BATCH_NUMBER +
            " INT NOT NULL , " + COLUMN_CASE_NUMBER +
            " INT NOT NULL , " + COLUMN_PACKING+
            " INT NOT NULL , " + COLUMN_TYPE +
            " VARCHAR(100) NOT NULL ," + COLUMN_BOTTLE+
            " INT NOT NULL , " + COLUMN_EXCISE_RATE+
            " INT NOT NULL , " +COLUMN_PURCHASE_RATE+
            " INT NOT NULL , " +COLUMN_TOTAL_AMOUNT+
            " INT NOT NULL )";

    public int id,tpNumber,billNumber,batchNumber,caseNumber,packing,typeNumber,bottle,exciseRate,purchaseRate,totalAmount;
    public String vendorName,recievedDate,invoiceDate,tpDate,itemName;

    public ItemModel() {
    }

    public ItemModel(int id, String vendorName, int tpNumber, int billNumber, String recievedDate, String invoiceDate, String tpDate, String itemName ,
                     int batchNumber, int caseNumber, int packing, int typeNumber, int bottle, int exciseRate, int purchaseRate, int totalAmount) {
        this.id = id;
        this.vendorName = vendorName;
        this.tpNumber = tpNumber;
        this.billNumber = billNumber;
        this.recievedDate = recievedDate;
        this.invoiceDate = invoiceDate;
        this.tpDate = tpDate;
        this.itemName = itemName;
        this.batchNumber = batchNumber;
        this.caseNumber = caseNumber;
        this.packing = packing;
        this.typeNumber = typeNumber;
        this.bottle = bottle;
        this.exciseRate = exciseRate;
        this.purchaseRate = purchaseRate;
        this.totalAmount = totalAmount;

    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getColumnId() {
        return COLUMN_ID;
    }

    public static String getColumnVendorname() {
        return COLUMN_VENDORNAME;
    }

    public static String getColumnTpNumber() {
        return COLUMN_TP_NUMBER;
    }

    public static String getColumnBillNumber() {
        return COLUMN_BILL_NUMBER;
    }

    public static String getColumnRecievedDate() {
        return COLUMN_RECIEVED_DATE;
    }

    public static String getColumnInvoiceDate() {
        return COLUMN_INVOICE_DATE;
    }

    public static String getColumnTpDate() {
        return COLUMN_TP_DATE;
    }

    public static String getColumnItemName() {
        return COLUMN_ITEM_NAME;
    }

    public static String getColumnBatchNumber() {
        return COLUMN_BATCH_NUMBER;
    }

    public static String getColumnCaseNumber() {
        return COLUMN_CASE_NUMBER;
    }

    public static String getColumnPacking() {
        return COLUMN_PACKING;
    }

    public static String getColumnType() {
        return COLUMN_TYPE;
    }

    public static String getColumnBottle() {
        return COLUMN_BOTTLE;
    }

    public static String getColumnExciseRate() {
        return COLUMN_EXCISE_RATE;
    }

    public static String getColumnPurchaseRate() {
        return COLUMN_PURCHASE_RATE;
    }

    public static String getColumnTotalAmount() {
        return COLUMN_TOTAL_AMOUNT;
    }

    public static String getCreateTable() {
        return CREATE_TABLE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTpNumber() {
        return tpNumber;
    }

    public void setTpNumber(int tpNumber) {
        this.tpNumber = tpNumber;
    }

    public int getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(int billNumber) {
        this.billNumber = billNumber;
    }

    public int getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }

    public int getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(int caseNumber) {
        this.caseNumber = caseNumber;
    }

    public int getPacking() {
        return packing;
    }

    public void setPacking(int packing) {
        this.packing = packing;
    }

    public int getTypeNumber() {
        return typeNumber;
    }

    public void setTypeNumber(int typeNumber) {
        this.typeNumber = typeNumber;
    }

    public int getBottle() {
        return bottle;
    }

    public void setBottle(int bottle) {
        this.bottle = bottle;
    }

    public int getExciseRate() {
        return exciseRate;
    }

    public void setExciseRate(int exciseRate) {
        this.exciseRate = exciseRate;
    }

    public int getPurchaseRate() {
        return purchaseRate;
    }

    public void setPurchaseRate(int purchaseRate) {
        this.purchaseRate = purchaseRate;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getRecievedDate() {
        return recievedDate;
    }

    public void setRecievedDate(String recievedDate) {
        this.recievedDate = recievedDate;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getTpDate() {
        return tpDate;
    }

    public void setTpDate(String tpDate) {
        this.tpDate = tpDate;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
