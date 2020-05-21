package com.supersu.inventory.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "inventoryMain";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ItemModel.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ItemModel.TABLE_NAME);

        // Create tables again
        onCreate(sqLiteDatabase);
    }


    public long insertNode(String vendorName, int tpNumber, int billNumber, String recievedDate, String invoiceDate, String tpDate, String itemName ,
                           int batchNumber, int caseNumber, int packing, int typeNumber, int bottle, int exciseRate, int purchaseRate, int totalAmount) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ItemModel.COLUMN_VENDORNAME,vendorName);
        values.put(ItemModel.COLUMN_TP_NUMBER,tpNumber);
        values.put(ItemModel.COLUMN_BILL_NUMBER,billNumber);
        values.put(ItemModel.COLUMN_RECIEVED_DATE,recievedDate);
        values.put(ItemModel.COLUMN_INVOICE_DATE,invoiceDate);
        values.put(ItemModel.COLUMN_TP_DATE,tpDate);
        values.put(ItemModel.COLUMN_ITEM_NAME,itemName);
        values.put(ItemModel.COLUMN_BATCH_NUMBER,batchNumber);
        values.put(ItemModel.COLUMN_CASE_NUMBER,caseNumber);
        values.put(ItemModel.COLUMN_PACKING,packing);
        values.put(ItemModel.COLUMN_TYPE,typeNumber);
        values.put(ItemModel.COLUMN_BOTTLE,bottle);
        values.put(ItemModel.COLUMN_EXCISE_RATE,exciseRate);
        values.put(ItemModel.COLUMN_PURCHASE_RATE,purchaseRate);
        values.put(ItemModel.COLUMN_TOTAL_AMOUNT,totalAmount);


        // insert row
        long ids = db.insert(ItemModel.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return ids;
    }

    public ItemModel getItems(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(ItemModel.TABLE_NAME,
                new String[]{ItemModel.COLUMN_ID, ItemModel.COLUMN_VENDORNAME, ItemModel.COLUMN_TP_NUMBER,ItemModel.COLUMN_BILL_NUMBER,ItemModel.COLUMN_RECIEVED_DATE ,
                        ItemModel.COLUMN_INVOICE_DATE,ItemModel.COLUMN_TP_DATE,ItemModel.COLUMN_ITEM_NAME,ItemModel.COLUMN_BATCH_NUMBER,ItemModel.COLUMN_CASE_NUMBER
                ,ItemModel.COLUMN_PACKING,ItemModel.COLUMN_TYPE,ItemModel.COLUMN_BOTTLE,ItemModel.COLUMN_EXCISE_RATE,ItemModel.COLUMN_PURCHASE_RATE,ItemModel.COLUMN_TOTAL_AMOUNT},
                ItemModel.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        ItemModel itemModel = new ItemModel(
                cursor.getInt(cursor.getColumnIndex(ItemModel.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(ItemModel.COLUMN_VENDORNAME)),
                cursor.getInt(cursor.getColumnIndex(ItemModel.COLUMN_TP_NUMBER)),
                cursor.getInt(cursor.getColumnIndex(ItemModel.COLUMN_BILL_NUMBER)),
                cursor.getString(cursor.getColumnIndex(ItemModel.COLUMN_RECIEVED_DATE)),
                cursor.getString(cursor.getColumnIndex(ItemModel.COLUMN_INVOICE_DATE)),
                cursor.getString(cursor.getColumnIndex(ItemModel.COLUMN_TP_DATE)),
                cursor.getString(cursor.getColumnIndex(ItemModel.COLUMN_ITEM_NAME)),
                cursor.getInt(cursor.getColumnIndex(ItemModel.COLUMN_BATCH_NUMBER)),
                cursor.getInt(cursor.getColumnIndex(ItemModel.COLUMN_CASE_NUMBER)),
                cursor.getInt(cursor.getColumnIndex(ItemModel.COLUMN_PACKING)),
                cursor.getInt(cursor.getColumnIndex(ItemModel.COLUMN_TYPE)),
                cursor.getInt(cursor.getColumnIndex(ItemModel.COLUMN_BOTTLE)),
                cursor.getInt(cursor.getColumnIndex(ItemModel.COLUMN_EXCISE_RATE)),
                cursor.getInt(cursor.getColumnIndex(ItemModel.COLUMN_PURCHASE_RATE)),
                cursor.getInt(cursor.getColumnIndex(ItemModel.COLUMN_TOTAL_AMOUNT)));
        cursor.close();
        return itemModel;

    }

    public List<ItemModel> getAllNodes() {
        List<ItemModel> items = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + ItemModel.TABLE_NAME + " ORDER BY " +
                ItemModel.COLUMN_ID + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                    ItemModel itemModel = new ItemModel();
                       itemModel.setId(cursor.getColumnIndex(ItemModel.COLUMN_ID));
                       itemModel.setVendorName(cursor.getString(cursor.getColumnIndex(ItemModel.COLUMN_VENDORNAME)));
                       itemModel.setTpNumber(cursor.getColumnIndex(ItemModel.COLUMN_TP_NUMBER));
                       itemModel.setBillNumber(cursor.getColumnIndex(ItemModel.COLUMN_BILL_NUMBER));
                       itemModel.setRecievedDate(cursor.getString(cursor.getColumnIndex(ItemModel.COLUMN_RECIEVED_DATE)));
                       itemModel.setInvoiceDate(cursor.getString(cursor.getColumnIndex(ItemModel.COLUMN_INVOICE_DATE)));
                       itemModel.setTpDate(cursor.getString(cursor.getColumnIndex(ItemModel.COLUMN_TP_DATE)));
                       itemModel.setItemName(cursor.getString(cursor.getColumnIndex(ItemModel.COLUMN_ITEM_NAME)));
                       itemModel.setBatchNumber (cursor.getColumnIndex(ItemModel.COLUMN_BATCH_NUMBER));
                       itemModel.setCaseNumber (cursor.getColumnIndex(ItemModel.COLUMN_CASE_NUMBER));
                       itemModel.setPacking(cursor.getColumnIndex(ItemModel.COLUMN_PACKING));
                       itemModel.setTypeNumber(cursor.getColumnIndex(ItemModel.COLUMN_TYPE));
                       itemModel.setBottle(cursor.getColumnIndex(ItemModel.COLUMN_BOTTLE));
                       itemModel.setExciseRate(cursor.getColumnIndex(ItemModel.COLUMN_EXCISE_RATE));
                       itemModel.setPurchaseRate(cursor.getColumnIndex(ItemModel.COLUMN_PURCHASE_RATE));
                       itemModel.setTotalAmount(cursor.getColumnIndex(ItemModel.COLUMN_TOTAL_AMOUNT));


            }while (cursor.moveToNext());
        }
        db.close();

        // return notes list
        return items;

    }


    public int getNodesCount() {
        String countQuery = "SELECT  * FROM " + ItemModel.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateNote(ItemModel itemModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ItemModel.COLUMN_VENDORNAME,itemModel.getVendorName());
        values.put(ItemModel.COLUMN_TP_NUMBER,itemModel.getTpNumber());
        values.put(ItemModel.COLUMN_BILL_NUMBER,itemModel.getBillNumber());
        values.put(ItemModel.COLUMN_RECIEVED_DATE,itemModel.getRecievedDate());
        values.put(ItemModel.COLUMN_INVOICE_DATE,itemModel.getInvoiceDate());
        values.put(ItemModel.COLUMN_TP_DATE,itemModel.getTpDate());
        values.put(ItemModel.COLUMN_ITEM_NAME,itemModel.getItemName());
        values.put(ItemModel.COLUMN_BATCH_NUMBER,itemModel.getBatchNumber());
        values.put(ItemModel.COLUMN_CASE_NUMBER,itemModel.getCaseNumber());
        values.put(ItemModel.COLUMN_PACKING,itemModel.getPacking());
        values.put(ItemModel.COLUMN_TYPE,itemModel.getTypeNumber());
        values.put(ItemModel.COLUMN_BOTTLE,itemModel.getBottle());
        values.put(ItemModel.COLUMN_EXCISE_RATE,itemModel.getExciseRate());
        values.put(ItemModel.COLUMN_PURCHASE_RATE,itemModel.getPurchaseRate());
        values.put(ItemModel.COLUMN_TOTAL_AMOUNT,itemModel.getTotalAmount());
        // updating row
        return db.update(ItemModel.TABLE_NAME, values, ItemModel.COLUMN_ID + " = ?",
                new String[]{String.valueOf(itemModel.getId())});
    }
    public void deleteNote(ItemModel itemModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ItemModel.TABLE_NAME, ItemModel.COLUMN_ID + " = ?",
                new String[]{String.valueOf(itemModel.getId())});
        db.close();
    }
}
