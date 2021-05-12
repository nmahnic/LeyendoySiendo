package com.nicomahnic.dadm.leyendoysiendo.ui.fragments.mainactivity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.nicomahnic.dadm.leyendoysiendo.R
import com.nicomahnic.dadm.leyendoysiendo.databinding.FragmentSplashBinding

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var binding: FragmentSplashBinding
    private val SPLASH_TIME_OUT:Long = 2000 // 3 sec

    lateinit var v: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)

        v = view
        Run.after(SPLASH_TIME_OUT) {
            val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
            v.findNavController().navigate(action)
        }

    }

    companion object Run {
        fun after(delay: Long, process: () -> Unit) {
            Handler(Looper.getMainLooper()).postDelayed({
                process()
            }, delay)
        }
    }

}