package com.example.expensetracker;

class Expense {
    public int id;
    public String title;
    public double amount;

    public Expense(int id, String title, double amount) {
        this.id = id;
        this.title = title;
        this.amount = amount;
    }
}
