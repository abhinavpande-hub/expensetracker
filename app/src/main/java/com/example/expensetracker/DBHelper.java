package com.example.expensetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "ExpenseDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE expenses(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, amount REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS expenses");
        onCreate(db);
    }

    public void addExpense(String title, double amount) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("title", title);
        cv.put("amount", amount);

        db.insert("expenses", null, cv);
    }

    public ArrayList<Expense> getExpenses() {
        ArrayList<Expense> list = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM expenses", null);

        while (c.moveToNext()) {
            list.add(new Expense(
                    c.getInt(0),
                    c.getString(1),
                    c.getDouble(2)
            ));
        }
        c.close();
        return list;
    }

    public double getTotal() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT SUM(amount) FROM expenses", null);

        double total = 0;

        if (c.moveToFirst())
            total = c.getDouble(0);

        c.close();
        return total;
    }

    public void deleteExpense(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("expenses", "id=?", new String[]{String.valueOf(id)});
    }
}
