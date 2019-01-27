package com.gauravgoyal.test.model

import androidx.databinding.ObservableField

data class ExpenseHolder(val name: String, var paid: ObservableField<String>, var share: ObservableField<String>)