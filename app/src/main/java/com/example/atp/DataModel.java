package com.example.atp;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class DataModel {
    private static DataModel instance = new DataModel();
    private DataModel(){}
    public static DataModel getInstance(){
        return instance;
    }
    public ArrayList<Product>products;
    private ProductDatabase database;

    public void createDataBase(Context context){
        database = new ProductDatabase(context);
        products = database.getProductsFromDB();
    }

    public ArrayList<Product> getProducts(){
        return products;
    }

    public Product getProduct(int pos){
        return products.get(pos);
    }

    public boolean addProduct(Product p){
        long id = database.createProductInDB(p);
        if(id > 0){
            p.setId(id);
            products.add(p);
            return true;
        }
        return false;
    }

    public boolean updateProduct(Product p, int pos){
        int count = database.updateProductInDB(p);
        if (count == 1){
            products.set(pos, p);
            return true;
        }
        return false;
    }

    public boolean removeProduct(int pos){
        int count = database.removeProductInDB(
                getProduct(pos)
        );
        if (count == 1){
            products.remove(pos);
            return true;
        }
        return false;
    }


}
