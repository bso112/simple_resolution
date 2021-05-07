package com.manta.oneline

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.manta.domain.data.MemoData
import com.manta.domain.usecase.CreateMemoUsecase
import com.manta.domain.usecase.GetRandomMemoUsecase
import com.manta.oneline.databinding.DialogResolutionBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class ResolutionDialog() : DialogFragment() {
    @Inject
    lateinit var createMemousecase: CreateMemoUsecase

    @Inject
    lateinit var getMemoUsecase: GetRandomMemoUsecase


    val binding: DialogResolutionBinding by lazy { DialogResolutionBinding.inflate(layoutInflater) }

    var isLoading = MutableLiveData<Boolean>()
    var isEditing = MutableLiveData<Boolean>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.dialog = this
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.apply {
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        isLoading.value = true
        getMemoUsecase.getRandomMemo()
            .observe(requireActivity()) { memoData ->
                isLoading.value = false
                binding.tvResolution.text = memoData?.content ?: ""
            }

//
//
//        binding.submitBtn.setOnClickListener {
//            isLoading.value = true
//            isEditing.value = false
//            createMemousecase.createMemo(createMemoFromInput())
//                .observe(requireActivity()) { isLoading.value = false }
//        }

        binding.card.setOnClickListener {
            dismiss()
        }

        binding.card.setOnLongClickListener {
            isEditing.value = true
            true
        }

        isLoading.observe(requireActivity()) {
            binding.tvResolution.text = ""
        }

    }

    private fun createMemoFromInput() = MemoData(binding.etResolution.text.toString(), getDate())

    fun getDate(): String {
        return SimpleDateFormat("yyyy-MM-dd", resources.configuration.locale).format(Date())
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        (requireActivity() as? DialogInterface.OnDismissListener)?.onDismiss(dialog)
    }



}