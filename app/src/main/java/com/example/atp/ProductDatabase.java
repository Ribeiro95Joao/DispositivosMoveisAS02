package com.example.atp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ProductDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "products.sqlite";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "Product";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_VALUE = "value";

    public ProductDatabase(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "Create table if not exists " + DB_TABLE + "( " +
                COL_ID + " integer primary key autoincrement, " +
                COL_NAME + " text, " +
                COL_VALUE + " real)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long createProductInDB(Product p){
        ContentValues values = new ContentValues();
        values.put(COL_NAME, p.getName());
        values.put(COL_VALUE, p.getValue());
        SQLiteDatabase database = getWritableDatabase();
        long id = database.insert(DB_TABLE, null, values);
        database.close();
        return  id;
    }

    public ArrayList<Product> getProductsFromDB(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(DB_TABLE, null,
                null, null, null, null, null);
        ArrayList<Product> products = new ArrayList<>();
        if (cursor.moveToFirst())
            do{
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME));
                Double value = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_VALUE));
                products.add(new Product(id, name, value));
            }while (cursor.moveToNext());

        database.close();
        return products;
    }

    public int updateProductInDB(Product p){
        ContentValues values = new ContentValues();
        values.put(COL_NAME, p.getName());
        values.put(COL_VALUE, p.getValue());
        String id = String.valueOf(p.getId());
        SQLiteDatabase database = getWritableDatabase();
        int count = database.update(DB_TABLE, values, COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }

    public int removeProductInDB(Product p){
        String id = String.valueOf(p.getId());
        SQLiteDatabase database = getWritableDatabase();
        int count = database.delete(DB_TABLE, COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }


}
