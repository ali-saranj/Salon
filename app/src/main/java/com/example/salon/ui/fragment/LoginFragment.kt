package com.example.salon.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.salon.data.api.Iclient
import com.example.salon.data.model.retrofit.login.Body
import com.example.salon.data.model.retrofit.login.Response
import com.example.salon.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var client: Retrofit
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

     fun login() {
        if (edtIsNotNull()) {
            val iclient: Iclient = client.create(Iclient::class.java)
            iclient.login(
                Body(
                    binding.passwordLogin.text.toString(),
                    binding.usernameLogin.text.toString()
                )
            ).enqueue(object : Callback<Response> {
                override fun onResponse(
                    call: Call<Response>,
                    response: retrofit2.Response<Response>
                ) {
                    Toast.makeText(context, response.body()!!.statos.toString(), Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onFailure(call: Call<Response>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun edtIsNotNull(): Boolean {
        if (binding!!.usernameLogin.text.toString().isEmpty()) {
            Toast.makeText(context, "نام کاربری را وارد کنید", Toast.LENGTH_SHORT).show()
            return false
        }
        return if (binding!!.passwordLogin.text.toString().isEmpty()) {
            Toast.makeText(context, "رمز عبور را وارد کنید", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }
}