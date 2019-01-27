package com.gauravgoyal.test.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.widget.EditText


class EditTextBackEvent : EditText {


    private var mOnImeBack: EditTextImeBackListener? = null


    constructor(context: Context) : super(context) {

    }


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

    }


    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {

    }


    override fun onKeyPreIme(keyCode: Int, event: KeyEvent): Boolean {

        if (event.getKeyCode() === KeyEvent.KEYCODE_BACK && event.getAction() === KeyEvent.ACTION_UP) {

            if (mOnImeBack != null) mOnImeBack!!.onImeBack(this, this.getText().toString())

        }

        return super.dispatchKeyEvent(event)

    }


    fun setOnEditTextImeBackListener(listener: EditTextImeBackListener) {

        mOnImeBack = listener

    }


    interface EditTextImeBackListener {

        fun onImeBack(ctrl: EditTextBackEvent, text: String)

    }


    override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection {

        val connection = super.onCreateInputConnection(outAttrs)

        val imeActions = outAttrs.imeOptions and EditorInfo.IME_MASK_ACTION

        if (imeActions and EditorInfo.IME_ACTION_DONE != 0) {

            // clear the existing action

            outAttrs.imeOptions = outAttrs.imeOptions xor imeActions

            // set the DONE action

            outAttrs.imeOptions = outAttrs.imeOptions or EditorInfo.IME_ACTION_DONE

        }

        if (outAttrs.imeOptions and EditorInfo.IME_FLAG_NO_ENTER_ACTION != 0) {

            outAttrs.imeOptions = outAttrs.imeOptions and EditorInfo.IME_FLAG_NO_ENTER_ACTION.inv()

        }

        return connection

    }


}
