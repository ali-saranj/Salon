package com.example.salon.ui.fragment

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.Toast
import com.example.salon.data.api.Client
import com.example.salon.data.api.Iclient
import com.example.salon.data.model.retrofit.getsalon.Response
import com.example.salon.data.viewmodel.SalonCard
import com.example.salon.data.viewmodel.SalonSingel
import com.example.salon.databinding.FragmentSalonBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback


class SalonFragment(val id: String) : BottomSheetDialogFragment() {


    lateinit var binding: FragmentSalonBinding
    var salonCard: SalonCard? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSalonBinding.inflate(inflater, container, false)

        binding.btnGps.setOnClickListener(openGps())

        return binding.root
    }

    private fun openGps(): View.OnClickListener {
        return View.OnClickListener {
            if (salonCard!=null) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(salonCard!!.location)))
            }
        }
    }

    private fun getData(id: String) {
        val iclient: Iclient = Client.getClient().create(Iclient::class.java)

        iclient.getSalon(id).enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        setData(response.body()!!)
                    }
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(context, "" + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setData(body: Response) {
        val salonSingel = SalonSingel(body)
        binding.salon = salonSingel
        Picasso.get().load("${Client.BASE_URL}${salonSingel.image}").into(binding.imageTitle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData(id)

        binding.imageBack.setOnClickListener {
            it.startAnimation(AnimationUtils.loadAnimation(context,android.R.anim.fade_in))
            dialog!!.dismiss()
        }

        binding.tvBack.setOnClickListener {
            it.startAnimation(AnimationUtils.loadAnimation(context,android.R.anim.fade_in))
            dialog!!.dismiss()
        }

    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupFullHeight(bottomSheetDialog)
        }
        return dialog
    }


    private fun setupFullHeight(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet =
            bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
        val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet!!)
        val layoutParams = bottomSheet.layoutParams
        val windowHeight = getWindowHeight()
        if (layoutParams != null) {
            layoutParams.height = windowHeight
        }
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

}