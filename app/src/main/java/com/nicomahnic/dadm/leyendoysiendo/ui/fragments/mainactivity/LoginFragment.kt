package com.nicomahnic.dadm.leyendoysiendo.ui.fragments.mainactivity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.nicomahnic.dadm.leyendoysiendo.data.database.appDatabase
import com.nicomahnic.dadm.leyendoysiendo.R
import com.nicomahnic.dadm.leyendoysiendo.databinding.FragmentLoginBinding
import com.nicomahnic.dadm.leyendoysiendo.domain.UserDao
import com.nicomahnic.dadm.leyendoysiendo.data.entities.UserEntity
import com.nicomahnic.dadm.leyendoysiendo.ui.activities.SecondActivity
import kotlinx.android.synthetic.main.fragment_login.*


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private var db: appDatabase? = null
    private var userDao: UserDao? = null

    var btnUser: Boolean = false
    var btnPasswd: Boolean = false

    lateinit var v: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        v = view

        binding.edtUser.apply { addTextChangedListener(userWatcher) }

        binding.edtPasswd.apply { addTextChangedListener(passwdWatcher) }

        binding.btnEnter.isEnabled = false
    }

    private val userWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            println("afterTextChanged -> $s")

            btnUser = s.toString().isNotEmpty()
            binding.btnEnter.isEnabled = btnUser && btnPasswd

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    private val passwdWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            println("afterTextChanged -> $s")

            btnPasswd = s.toString().isNotEmpty()
            binding.btnEnter.isEnabled = btnUser && btnPasswd

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    override fun onStart() {
        super.onStart()

        db = appDatabase.getAppDataBase(v.context)
        userDao = db?.userDao()

        if (userDao?.loadAllPersons()?.size == 0) {
            userDao?.insertPerson(
                UserEntity(
                    id = 0,
                    name = "Mash",
                    password = "1234",
                    img = "https://yt3.ggpht.com/ytc/AAUvwnigE2XbXLQMkJNuNnJEYvdUixTSMUQWTT_qLoxh6tQ=s900-c-k-c0x00ffffff-no-rj"
                )
            )
            userDao?.insertPerson(
                UserEntity(
                    id = 0,
                    name = "Luli",
                    password = "1234",
                    img = "https://media-exp1.licdn.com/dms/image/C4D35AQHLjJeP2QR5FQ/profile-framedphoto-shrink_200_200/0/1613432312275?e=1621058400&v=beta&t=o0dW6MV15_aRm1X7Uh42a_UzmWrjkpkUAzUs0RszqLo"                )
            )
        }
        val usersList = userDao?.loadAllPersons()
        Log.d("NM", "userList = ${usersList}")


        binding.btnEnter.setOnClickListener {
            val validUser = usersList!!.find { it!!.name == binding.edtUser.text.toString() }

            validateUser(validUser)?.let {
                val sendIntent = Intent(context, SecondActivity::class.java)
                sendIntent.putExtra("name", validUser!!.name)
                sendIntent.putExtra("imgUri", validUser!!.img)
                startActivity(sendIntent)
                requireActivity().finish()
            }
        }
    }

    private fun validateUser(validUser: UserEntity?): Boolean? {
        validUser?.let { user ->
            if (user.password == binding.edtPasswd.text.toString()) user.checked = true
            if (user.checked) {
                return true
            } else {
                Snackbar.make(v, "Password no valida", Snackbar.LENGTH_SHORT).show()
            }
        } ?: run {
            Snackbar.make(v, "Usuario no registrado", Snackbar.LENGTH_SHORT).show()
        }
        return null
    }
}