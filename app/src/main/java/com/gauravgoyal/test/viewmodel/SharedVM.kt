package com.gauravgoyal.test.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gauravgoyal.test.model.ExpenseHolder

class SharedVM : ViewModel() {


    var expenses = arrayListOf<ExpenseHolder>()


    fun addExpense(data: ExpenseHolder) {
        expenses.add(data)
    }

    fun addExpenses(data: List<ExpenseHolder>) {
        for (item in data) {
            expenses.add(item)
        }
    }

}


object SharedModelFactory : ViewModelProvider.Factory {

    private val viewModel = SharedVM()

    @Throws(ClassCastException::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedVM::class.java)) {
            return viewModel as T
        }
        throw ClassCastException("class can't be assigned")
    }
}