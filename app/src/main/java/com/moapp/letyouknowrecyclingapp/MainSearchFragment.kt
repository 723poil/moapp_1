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
import android.widget.Toast
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


        var searchdata : String = ""
        var view = inflater.inflate(R.layout.fragment_main_search, container, false)
        var EditText = view.edittextView1
        var Button = view.search_button


        Button.setOnClickListener {
            searchdata = EditText.text.toString()
            if(searchdata.contains("aluminum") || searchdata.contains("알루미늄")) {
                var intent = Intent(context, TabActivity::class.java)
                intent.putExtra("data2", 1)
                startActivity(intent)
            }
            else if(searchdata.contains("carton") || searchdata.contains("팩")) {
                var intent = Intent(context, TabActivity::class.java)
                intent.putExtra("data2", 2)
                startActivity(intent)
            }
            else if(searchdata.contains("glass") || searchdata.contains("유리")) {
                var intent = Intent(context, TabActivity::class.java)
                intent.putExtra("data2", 3)
                startActivity(intent)
            }
            else if(searchdata.contains("plastic") || searchdata.contains("플라스틱")) {
                var intent = Intent(context, TabActivity::class.java)
                intent.putExtra("data2", 4)
                startActivity(intent)
            }
            else if(searchdata.contains("textiles") || searchdata.contains("의류")) {
                var intent = Intent(context, TabActivity::class.java)
                intent.putExtra("data2", 5)
                startActivity(intent)
            }
            else if(searchdata.contains("paper") || searchdata.contains("종이")) {
                var intent = Intent(context, TabActivity::class.java)
                intent.putExtra("data2", 6)
                startActivity(intent)
            }
            else if(searchdata.contains("wood") || searchdata.contains("목재")) {
                var intent = Intent(context, TabActivity::class.java)
                intent.putExtra("data2", 7)
                startActivity(intent)
            }
            else if(searchdata.contains("organic waste") || searchdata.contains("음식")) {
                var intent = Intent(context, TabActivity::class.java)
                intent.putExtra("data2", 8)
                startActivity(intent)
            }
            else {
                Toast.makeText(context, "없는 품목입니다.", Toast.LENGTH_SHORT).show()
            }

        }
        return view
    }



}

