package ru.netology.nmedia1022.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment

import androidx.navigation.fragment.findNavController

import ru.netology.nmedia1022.R

import ru.netology.nmedia1022.databinding.EditPostBinding



class EditPostFragment : Fragment(R.layout.edit_post) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = EditPostBinding.bind(view)






        //не понимаю как это работает
        binding.edit.setText(Intent().getStringExtra(Intent.EXTRA_TEXT))


        binding.ok.setOnClickListener {
            val intent = Intent()

            val text = binding.edit.text

            if (TextUtils.isEmpty(text)) {
                requireActivity().setResult(Activity.RESULT_CANCELED, intent)
            } else {
                val content = text.toString()
                intent.putExtra(Intent.EXTRA_TEXT, content)
                requireActivity().setResult(Activity.RESULT_OK, intent)
            }
            findNavController().navigateUp()
        }
    }
}
