package com.seng31323.assignment_01.ui.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.seng31323.assignment_01.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    @SuppressLint("ServiceCast")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var view =  inflater.inflate(R.layout.fragment_main, container, false)


        //findViewById(We can also use binding)
        var message = view.findViewById<TextView>(R.id.message)
        var clearButton = view.findViewById<Button>(R.id.clearButton)
        var number1EditText = view.findViewById<EditText>(R.id.number1)
        var number2EditText = view.findViewById<EditText>(R.id.number2)
        var parentLayout: LinearLayout = view.findViewById(R.id.parentLayout)
        var addButton = view.findViewById<Button>(R.id.addButton)
        var minusButton = view.findViewById<Button>(R.id.minusButton)
        var multipleButton = view.findViewById<Button>(R.id.multipleButton)
        var divideButton = view.findViewById<Button>(R.id.divideButton)


        //Observer
        viewModel.result.observe(viewLifecycleOwner, Observer {
            message.text = it.toString()
        })

        //Set the result value to the message space
        if (message != null) {
            message.text = viewModel.result.value.toString()
        }


        //function to hide keyboard
        fun View.hideKeyboard() {
            val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(windowToken, 0)
        }


        //function to perform certain actions when click operator buttons
        fun performOperation(operation: (Double, Double) -> Unit) {
            val number1String = number1EditText?.text.toString()
            val number1 = number1String?.toDoubleOrNull()
            val number2String = number2EditText?.text.toString()
            val number2 = number2String?.toDoubleOrNull()

            if ((number1 != null) && (number2 != null)) {
                operation(number1, number2)
            }

            view.hideKeyboard()
        }

        //OnClickListeners for buttons
        addButton.setOnClickListener{
            performOperation { number1, number2 -> viewModel.add(number1, number2) }
        }

        minusButton.setOnClickListener {
            performOperation { number1, number2 -> viewModel.subtraction(number1, number2) }
        }

        multipleButton.setOnClickListener {
            performOperation { number1, number2 -> viewModel.multiplication(number1, number2) }
        }

        divideButton.setOnClickListener {
            performOperation { number1, number2 -> viewModel.divide(number1, number2) }
        }

        clearButton.setOnClickListener {
            number1EditText.setText("")
            number2EditText.setText("")
            message.text = "Result"
        }

        parentLayout.setOnClickListener {
            // Hide the keyboard
            it.hideKeyboard()
        }





        return view
    }

}