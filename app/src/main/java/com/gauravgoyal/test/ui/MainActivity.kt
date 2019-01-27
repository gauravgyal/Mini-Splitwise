package com.gauravgoyal.test.ui

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModelProviders
import com.gauravgoyal.test.R
import com.gauravgoyal.test.model.ExpenseHolder
import com.gauravgoyal.test.ui.adapter.ContactsAdapter
import com.gauravgoyal.test.viewmodel.SharedModelFactory
import com.gauravgoyal.test.viewmodel.SharedVM
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val REQUEST_READ_CONTACTS = 79
    lateinit var sharedVm: SharedVM

    private val adapter: ContactsAdapter by lazy { ContactsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedVm = ViewModelProviders.of(this, SharedModelFactory)
            .get(SharedVM::class.java)

        add_participants.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED
            ) {
                startContactsActivity()
            } else {
                requestPermission();
            }
        }

        add.setOnClickListener {


            val data = adapter.getExpenseData()

//            val totalAmount = amount.text.toString().toInt()
//
//            val split = 0;
//            val share = 0
//
//            for(expense: )



            data?.let {

                sharedVm.addExpenses(it)
                val intent = Intent(this, DashBoardActivity::class.java)
                startActivity(intent)
            }
        }

        contacts.adapter = adapter

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_READ_CONTACTS -> {

                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startContactsActivity()
                } else {
                    // permission denied,Disable the
                    // functionality that depends on this permission.
                }
                return
            }
        }
    }

    protected fun requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.READ_CONTACTS
            )
        ) {
            // show UI part if you want here to show some rationale !!!

        } else {

            ActivityCompat.requestPermissions(
                this, arrayOf(android.Manifest.permission.READ_CONTACTS),
                REQUEST_READ_CONTACTS
            )
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                val contactData = data!!.data
                val cursor = contentResolver.query(contactData, null, null, null, null)
                cursor.moveToFirst()

                val contactId = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                val name =
                    cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME))

                val paid = ObservableField<String>()
                val share = ObservableField<String>()
                val holder = ExpenseHolder(name, paid, share)
                adapter.setData(arrayListOf(holder))
                Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
            }
        }


    }


    fun startContactsActivity() {

        val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        startActivityForResult(intent, 1)

//        val intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
//        startActivityForResult(intent, 1)
    }
}
