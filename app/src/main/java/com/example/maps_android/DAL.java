package com.example.maps_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DAL {
    private static final String TAG = "DAL";

    private SQLiteDatabase db;
    private CreateDatabase database;

    public DAL(Context context) {
        database = new CreateDatabase(context);
    }

    public boolean insert(String nome, String email, Double longitude, Double latitude) {
        ContentValues values;
        long result;
        db = database.getWritableDatabase();
        values = new ContentValues();
        values.put(CreateDatabase.NOME, nome);
        values.put(CreateDatabase.EMAIL, email);
        values.put(CreateDatabase.LATITUDE, latitude);
        values.put(CreateDatabase.LONGITUDE, longitude);
        result = db.insert(CreateDatabase.TABLE, null, values);
        db.close();
        if (result == -1) {
            Log.e(TAG, "insert: Erro inserindo registro");
            return false;
        }
        return true;
    }

    public boolean update(int id,String nome, String email, Double longitude, Double latitude) {
        ContentValues values;
        long result;
        String where = "_id = ?";
        String[] args = { String.valueOf(id) };
        db = database.getWritableDatabase();
        values = new ContentValues();
        values.put(CreateDatabase.NOME, nome);
        values.put(CreateDatabase.EMAIL, email);
        values.put(CreateDatabase.LATITUDE, latitude);
        values.put(CreateDatabase.LONGITUDE, longitude);
        result = db.update(CreateDatabase.TABLE, values, where, args);
        db.close();
        if (result == -1) {
            Log.e(TAG, "insert: Erro atualizando registro");
            return false;
        }
        return true;
    }

    public boolean delete(int id) {
       // ContentValues values;
        long result;
        String where = "_id = ?";
        String[] args = { String.valueOf(id) };
        db = database.getWritableDatabase();
        result = db.delete(CreateDatabase.TABLE, where, args);
        db.close();
        if (result == -1) {
            Log.e(TAG, "delete: Erro deletando registro");
            return false;
        }
        return true;
    }

    public boolean drop(int id) {
        ContentValues values;
        long result;
        String where = "_id = ?";
        String[] args = { String.valueOf(id) };
        db = database.getWritableDatabase();

        result = db.delete(CreateDatabase.TABLE, where, args);
        db.close();
        if (result == -1) {
            Log.e(TAG, "insert: Erro atualizando registro");
            return false;
        }
        return true;
    }

    public Cursor findById(int id) {
        Cursor cursor;
        String where = "_id = ?";
        String[] args = { String.valueOf(id) };
        db = database.getReadableDatabase();
        cursor = db.query(CreateDatabase.TABLE, null,
                where, args, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor loadAll() {
        Cursor cursor;
        String[] fields = {CreateDatabase.NOME,CreateDatabase.EMAIL,CreateDatabase.LATITUDE,CreateDatabase.LONGITUDE};
        db = database.getReadableDatabase();
        // SELECT _id, title FROM book
        // String sql = "SELECT _id, title FROM book";
        //cursor = db.rawQuery(sql, null);
        cursor = db.query(CreateDatabase.TABLE, fields, null,
                null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}
