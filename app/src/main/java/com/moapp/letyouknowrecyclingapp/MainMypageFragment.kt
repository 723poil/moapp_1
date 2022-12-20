package com.moapp.letyouknowrecyclingapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moapp.letyouknowrecyclingapp.databinding.FragmentMainMypageBinding


class MainMypageFragment : Fragment() {

    private lateinit var binding: FragmentMainMypageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.mypageconstraint1.setOnClickListener() {
            var intent = Intent(context, MainMypageSetting1Activity::class.java)
            startActivity(intent)
        }

        binding.mypageBtn1.setOnClickListener() {
            var intent = Intent(context, MainMypageWebview1::class.java)
            startActivity(intent)
        }

        binding.mypageBtn2.setOnClickListener() {
            var intent = Intent(context, MainMypageWebview1::class.java)
            startActivity(intent)
        }

        binding.mypageBtn3.setOnClickListener() {
            var intent = Intent(context, MainMypageWebview1::class.java)
            startActivity(intent)
        }

        binding.mypageconstraint3.setOnClickListener() {
            var intent = Intent(context, MainMypageSetting2Activity::class.java)
            startActivity(intent)
        }

        binding.mypageconstraint4.setOnClickListener() {
            var intent = Intent(context, MainMypageSetting2Activity::class.java)
            startActivity(intent)
        }
        binding.mypageconstraint5.setOnClickListener() {
            var intent = Intent(context, MainMypageSetting2Activity::class.java)
            startActivity(intent)
        }
        binding.mypageconstraint6.setOnClickListener() {
            var intent = Intent(context, MainMypageSetting2Activity::class.java)
            startActivity(intent)
        }
        binding.mypageconstraint7.setOnClickListener() {
            var intent = Intent(context, MainMypageSetting2Activity::class.java)
            startActivity(intent)
        }

    }

}