package com.moapp.letyouknowrecyclingapp

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import com.moapp.letyouknowrecyclingapp.databinding.FragmentMainSearchBinding
import kotlinx.android.synthetic.main.fragment_main_search.*
import kotlinx.android.synthetic.main.fragment_main_search.view.*


class MainSearchFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_search, container, false)
    }


}

