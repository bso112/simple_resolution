package com.manta.oneline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.manta.domain.data.MemoData
import com.manta.domain.usecase.CreateMemoUsecase
import com.manta.oneline.databinding.DialogResolutionBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ResolutionDialog() : DialogFragment() {

    @Inject lateinit var createMemousecase : CreateMemoUsecase

    val binding: DialogResolutionBinding by lazy { DialogResolutionBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.apply {
            window?.setBackgroundDrawableResource(android.R.color.transparent);
        }



    }



}