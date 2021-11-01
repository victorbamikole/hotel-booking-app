package com.example.hbapplicationgroupb.ui.bottom_nav_screens

import android.app.Activity
import android.content.*
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.dataBase.db.UserPreferences
import com.example.hbapplicationgroupb.databinding.FragmentProfileBinding
import com.example.hbapplicationgroupb.di.application.HotelApplication.Companion.application
import com.example.hbapplicationgroupb.util.constants.*
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    var binding : FragmentProfileBinding? = null
    private val roomViewModel: RoomViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
        binding?.fragmentProfileLogOutBtn?.setOnClickListener {

            //Clear user token from shared preferences
            activity?.let { it1 -> UserPreferences(it1).clearUserSession() }
            showLogOutAlert()
        }

        binding?.fragmentProfileHistoryTv?.setOnClickListener {
            findNavController()
                .navigate(R.id.action_profileFragment2_to_bookingHistoryFragment)

        }
        binding?.fragmentProfileHistoryIcon?.setOnClickListener {
            findNavController()
                .navigate(R.id.action_profileFragment2_to_bookingHistoryFragment)
        }
        binding?.fragmentProfileHelpTv?.setOnClickListener {
            findNavController()
                .navigate(R.id.action_profileFragment2_to_helpAndSupportFragment)
        }
        binding?.fragmentProfileHelpIcon?.setOnClickListener {
            findNavController()
                .navigate(R.id.action_profileFragment2_to_helpAndSupportFragment)
        }
        binding?.fragmentPrivacyPolicyTextView?.setOnClickListener {
            findNavController()
                .navigate(R.id.action_profileFragment2_to_privacyPolicyFragment)
        }
        binding?.fragmentProfilePrivacyIcon?.setOnClickListener {
            findNavController()
                .navigate(R.id.action_profileFragment2_to_privacyPolicyFragment)
        }
        binding?.fragmentProfileUploadIv?.setOnClickListener {
            val addPictureDialogue = android.app.AlertDialog.Builder(requireContext())
            addPictureDialogue.setTitle("select Action")
            val addPictureDialogueItem = arrayOf(
                "Select picture from gallery","Capture photo from camera")
            addPictureDialogue.setItems(addPictureDialogueItem){
                    _,which ->

                when(which){
                    0 -> {
                        choosePhotoFromGallery()
                    }
                    1 -> {
                        openCamera()
                    }
                }
            }
            addPictureDialogue.show()
        }
    }

    private fun openCamera() {
        checkIfCameraPermissionIsGranted()
    }

    private fun checkIfCameraPermissionIsGranted() {
        if (ContextCompat.checkSelfPermission(
                requireContext(), android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED){
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        }else{
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(android.Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
        }
    }

    private fun choosePhotoFromGallery() {
        Dexter.withActivity(requireActivity()).withPermissions(
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                if (p0!!.areAllPermissionsGranted()){
                    val galleryIntent = Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    )
                    startActivityForResult(galleryIntent,GALLERY_REQUEST_CODE)
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<PermissionRequest>?,
                p1: PermissionToken?
            ) {
                showRationalCaseDialogue()
            }

        }).onSameThread().check()    }

    private fun showRationalCaseDialogue() {
        android.app.AlertDialog.Builder(requireContext())
            .setMessage("it seems like you have turned off permission required for this feature." +
                    "it acn be enabled under application settings")
            .setPositiveButton("Go to settings"){_,_ ->
                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package",activity?.packageName,null)
                    intent.data = uri
                    startActivity(intent)
                }catch (e: ActivityNotFoundException){
                    e.printStackTrace()
                }

            }
            .setNegativeButton("Back"){dialog,_ ->
                dialog.dismiss()
            }.show()
    }


    private fun showLogOutAlert(){
        val dialogView = layoutInflater.inflate(R.layout.custom_profile_dialog, null)
        val customDialog = activity?.let {
            AlertDialog.Builder(it)
                .setView(dialogView)
                .show()
        }

        val btnProfileLogOutDialog = dialogView.findViewById<Button>(R.id.fragment_profile_log_out_btn)
        btnProfileLogOutDialog.setOnClickListener {
            customDialog?.dismiss()

            //Navigate to the login screen
            findNavController().navigate(R.id.action_profileFragment2_to_loginFragment)

        }

        val btnProfileCancelDialog = dialogView.findViewById<Button>(R.id.fragment_profile_cancel_btn)
        btnProfileCancelDialog.setOnClickListener {
            customDialog?.dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            }else{
                binding?.profileFragmentParent?.snackbar("Permission Denied")

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == CAMERA_REQUEST_CODE){
                val thumbNail: Bitmap = data!!.extras!!.get("data") as Bitmap
                binding?.fragmentProfileIv?.setImageBitmap(thumbNail)
                Log.d("imageUri", "onActivityResult: $thumbNail")
                saveImageToInternalStorage(thumbNail)

            }else if (requestCode == GALLERY_REQUEST_CODE){
                if(data != null){
                    val imageUri = data.data
                    Log.d("imageUri", "onActivityResult: $imageUri")
                    try{
                        val imageBitmap = MediaStore.Images.Media.getBitmap(this.activity?.contentResolver ,imageUri)
                        Log.d("imageUri", "onActivityResult: $imageBitmap")

//

                        saveImageToInternalStorage(imageBitmap)
                        Glide.with(this)
                            .load(imageUri)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(binding?.fragmentProfileIv!!)
                    }catch (e: IOException){
                        e.printStackTrace()
                        binding?.profileFragmentParent?.snackbar("image could not be loaded")
                    }
                }

            }
        }

    }

    private fun saveImageToInternalStorage(bitmap: Bitmap):Uri{
        val wrapper = ContextWrapper(application)
        var file = wrapper.getDir(IMAGE_DIRECTORY, Context.MODE_PRIVATE)
        file = File(file,"${UUID.randomUUID()}.jpg")
        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream)
            stream.flush()
            stream.close()
        }catch (e:IOException){
            e.printStackTrace()
        }
        val userToken = activity?.let { it1 ->
            UserPreferences(it1).getUserToken()
        }
        roomViewModel.uploadImageToAPI("Bearer $userToken","${Uri.parse(file.absolutePath)}")
        Log.d("imageUri", "saveImageToInternalStorage: ${Uri.parse(file.absolutePath)} ")
        return Uri.parse(file.absolutePath)
    }


}