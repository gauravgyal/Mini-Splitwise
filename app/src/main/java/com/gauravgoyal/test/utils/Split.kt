package com.gauravgoyal.test.utils

import com.gauravgoyal.test.model.ExpenseHolder


class Split {

    private val buffer by lazy {  ArrayList<String>()}



    fun getMin(arr: IntArray): Int {
        var minInd = 0
        for (i in 1 until arr.size)
            if (arr[i] < arr[minInd])
                minInd = i
        return minInd
    }

    fun getMax(arr: IntArray): Int {
        var maxInd = 0
        for (i in 1 until arr.size)
            if (arr[i] > arr[maxInd])
                maxInd = i
        return maxInd
    }

    fun minOf2(x: Int, y: Int): Int {
        return if (x < y) x else y
    }


    fun minCashFlowRec(amount: IntArray, person: ArrayList<String>) : ArrayList<String>{
        val mxCredit = getMax(amount)
        val mxDebit = getMin(amount)

        println((amount[mxCredit]).toString() + "")
        println((amount[mxDebit]).toString() + "")

        if (amount[mxCredit] == 0 && amount[mxDebit] == 0)
            return buffer

        // Find the minimum of two amounts
        val min = minOf2(-amount[mxDebit], amount[mxCredit])
        amount[mxCredit] -= min
        amount[mxDebit] += min

        // If minimum is the maximum amount to be
        buffer.add(
            "" + person.get(mxDebit) + " pays " + min
                    + " to " + "" + person.get(mxCredit) + "\n"
        )
        minCashFlowRec(amount, person)

        return buffer
    }

    fun getText(expenses: List<ExpenseHolder>) : ArrayList<String>{

        val map = HashMap<String, Int>()

        for (expense in expenses) {
            var amount = 0;
            if (map.containsKey(expense.name)) {
                amount = map.get(expense.name) ?: 0
            }

            val paid = expense.paid.get()?.toInt() ?: 0
            val share = expense.share.get()?.toInt() ?: 0

            val curr = paid - share
            map.put(expense.name, amount + curr)
        }


        val amountArr = IntArray(map.size)
        val personArr = ArrayList<String>()

        var i = 0
        map.map {
            amountArr[i] = it.value
            personArr.add(i, it.key)
            i++
        }

        return minCashFlowRec(amountArr, personArr)

    }




}
