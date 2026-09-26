package com.example.expensetracker

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var etTitle: EditText? = null
    private var etAmount: EditText? = null
    private var btnAdd: Button? = null
    private var tvTotal: TextView? = null
    private var recycler: RecyclerView? = null

    private var db: DBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etTitle = findViewById(R.id.etTitle)
        etAmount = findViewById(R.id.etAmount)
        btnAdd = findViewById(R.id.btnAdd)
        tvTotal = findViewById(R.id.tvTotal)
        recycler = findViewById(R.id.recycler)

        db = DBHelper(this)

        recycler?.layoutManager = LinearLayoutManager(this)

        loadData()

        btnAdd?.setOnClickListener {
            val title = etTitle?.text.toString()
            val amountStr = etAmount?.text.toString()
            if (title.isNotEmpty() && amountStr.isNotEmpty()) {
                val amount = amountStr.toDoubleOrNull() ?: 0.0
                db?.addExpense(title, amount)

                etTitle?.text?.clear()
                etAmount?.text?.clear()

                loadData()
            }
        }
    }

    fun loadData() {
        val expenses = db?.getExpenses() ?: ArrayList()
        val adapter = ExpenseAdapter(
            this,
            expenses,
            db,
            { loadData() })

        recycler?.adapter = adapter

        tvTotal?.text = "Total: ₹${db?.getTotal() ?: 0.0}"
    }
}
