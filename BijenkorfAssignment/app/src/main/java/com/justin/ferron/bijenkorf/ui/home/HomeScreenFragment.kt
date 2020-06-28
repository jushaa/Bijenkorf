package com.justin.ferron.bijenkorf.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.justin.ferron.bijenkorf.R
import com.justin.ferron.bijenkorf.ui.product.list.ProductListFragment


class HomeScreenFragment : Fragment() {

    companion object {
        fun newInstance() =
            HomeScreenFragment()
    }

    private lateinit var viewModel: HomeScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_screen_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeScreenViewModel::class.java)

        //Init button and search field
        val searchText = view?.findViewById<EditText>(R.id.searchText)
        val searchButton = view?.findViewById<Button>(R.id.searchButton)

        //Check for changes in the search field
        searchText?.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (s.toString().trim { it <= ' ' }.isEmpty()) {
                    searchButton?.isEnabled = false
                    searchButton?.isClickable = false
                } else {
                    searchButton?.isEnabled = true
                    searchButton?.isClickable = true
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
                // TODO Implement(not for this assignment)
            }

            override fun afterTextChanged(s: Editable) {
                //TODO Implement(not for this assignment)
            }
        })

        //This on setOnClickListener is called when the search button has been clicked
        searchButton?.setOnClickListener {
            // Give the search string to the new fragment
            val arguments = Bundle()
            arguments.putString("search", searchText?.text.toString())
            //Init the next Fragment
            val fragment = ProductListFragment()
            fragment.arguments = arguments
            val fragmentManager = activity!!.supportFragmentManager
            //Go to next Fragment
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.mainLayout, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

}