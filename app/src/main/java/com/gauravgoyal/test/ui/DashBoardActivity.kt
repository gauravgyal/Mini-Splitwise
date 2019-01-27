package com.gauravgoyal.test.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.gauravgoyal.test.R
import com.gauravgoyal.test.ui.adapter.ContactsAdapter
import com.gauravgoyal.test.ui.adapter.DashBoardAdapter
import com.gauravgoyal.test.utils.Split
import com.gauravgoyal.test.viewmodel.SharedModelFactory
import com.gauravgoyal.test.viewmodel.SharedVM
import kotlinx.android.synthetic.main.activity_dash_board.*

class DashBoardActivity : AppCompatActivity() {

    lateinit var sharedVm: SharedVM

    private val adapter: DashBoardAdapter by lazy { DashBoardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        fab.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        sharedVm = ViewModelProviders.of(this, SharedModelFactory)
            .get(SharedVM::class.java)

        splits.adapter = adapter

        val expenses = sharedVm.expenses

        val split = Split()
        val finaltext = split.getText(expenses)

        adapter.setData(finaltext)


    }
}
