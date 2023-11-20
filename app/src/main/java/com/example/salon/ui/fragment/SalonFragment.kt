package com.example.salon.ui.fragment

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.salon.data.api.Client
import com.example.salon.data.api.Iclient
import com.example.salon.data.application.SalonApplication
import com.example.salon.data.model.retrofit.getsalon.Response
import com.example.salon.data.model.app.SalonSingel
import com.example.salon.databinding.FragmentSalonBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import javax.inject.Inject


@AndroidEntryPoint
@Suppress("DEPRECATION")
class SalonFragment(val id: String) : BottomSheetDialogFragment() {


    @Inject
    lateinit var client: Retrofit

    lateinit var binding: FragmentSalonBinding
    var salonSingel: SalonSingel? = null;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSalonBinding.inflate(inflater, container, false)
        binding.fragmentsalon = this
        return binding.root
    }

    fun callToSalon(view: View) {

        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED -> {
                startActivity(Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:${salonSingel!!.phone}")))
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.CALL_PHONE
            ) -> {
                requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), 1)

            }

            else -> {
                requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), 1)
            }
        }

    }

    fun openGps(view: View) {
        if (salonSingel != null) {
            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(salonSingel!!.location))
            )
        }
    }

    private fun getData(id: String) {
        val iclient: Iclient = client.create(Iclient::class.java)

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
        salonSingel = SalonSingel(body)
        binding.salon = salonSingel
        Picasso.get().load("${Client.BASE_URL}${salonSingel!!.image}").into(binding.imageTitle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData(id)

    }

    fun back(view: View){
        view.startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in))
        dismiss()
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
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            // Check if the permission was granted
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, so make the phone call
            } else {
                startActivity(Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:${salonSingel!!.phone}")))
            }
        }
    }

}