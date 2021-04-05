package com.vikas.epfi.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vikas.epfi.R
import com.vikas.epfi.databinding.ActivityLoginBinding
import dagger.android.support.DaggerAppCompatActivity
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class LoginActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: ActivityLoginBinding

    private var isPanValid = false

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        init()
        setupObserver()
    }

    private fun init() {

        binding.etPanNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.panNumber = binding.etPanNumber.text.toString()
                viewModel.validePanNumber()
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.etDd.setOnClickListener {
            viewModel.selectBirthDate(this)
        }

        binding.etmm.setOnClickListener {
            viewModel.selectBirthDate(this)
        }

        binding.etyear.setOnClickListener {
            viewModel.selectBirthDate(this)
        }

        binding.btnDonHave.setOnClickListener {
            finish()
        }


        binding.btnNext.setOnClickListener {
            showToastMessage("Details submitted successfully")
            viewModel.initFinishActivity()
        }
    }



    private fun setupObserver() {
        viewModel.routeEventPan.observe(this, Observer {
            isPanValide(it)
        })

        viewModel.routeEventDay.observe(this, Observer {
            val dd: Editable = SpannableStringBuilder(it.toString())
            binding.etDd.text = dd
        })

        viewModel.routeEventMonth.observe(this, Observer {
            val mm: Editable = SpannableStringBuilder(it)
            binding.etmm.text = mm
        })

        viewModel.routeEventYear.observe(this, Observer {
            val yyyy: Editable = SpannableStringBuilder(it)
            binding.etyear.text = yyyy
            enableNextBtn()
        })

        viewModel.routeEvent.observe(this, Observer {
            finish()
        })
    }


    private fun enableNextBtn() {
        if(binding.etDd.text?.length!! >= 2 && binding.etmm.text?.length!! >=2 && binding.etyear.text?.length!! >= 4 && isPanValid)
            binding.btnNext.isEnabled = true
    }

    private fun isPanValide(isPanValide:Boolean) {
        isPanValid = isPanValide
        enableNextBtn()
    }


    private fun finishActivity() {
        finish()
    }


    private fun showToastMessage(message:String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }
}