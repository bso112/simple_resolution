package com.manta.oneline

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.manta.oneline.AppExt.subscribeOnBackground
import com.manta.oneline.data.Memo
import com.manta.oneline.databinding.DialogResolutionBinding
import com.manta.oneline.datasource.Repository
import io.reactivex.disposables.CompositeDisposable
import java.text.SimpleDateFormat
import java.util.*



class ResolutionDialog() : DialogFragment() {

    val binding: DialogResolutionBinding by lazy { DialogResolutionBinding.inflate(layoutInflater) }
    val disposables  = CompositeDisposable()

    val isLoading = MutableLiveData<Boolean>()
    val isEditing = MutableLiveData<Boolean>()
    val memoList = MutableLiveData<List<Memo>>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.apply {
            dialog = this@ResolutionDialog
            lifecycleOwner = this@ResolutionDialog
            adapter = ResolutionAdapter()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.apply {
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }


        updateMemoText()

        binding.submitBtn.setOnClickListener {
            isLoading.value = true
            isEditing.value = false

            Repository.memoDao.createMemo(createMemoFromInput()).subscribeOnBackground(disposables) {
                isLoading.value = false
                updateMemoText()

            }
        }


        binding.card.setOnClickListener {
            dismiss()
        }

        binding.card.setOnLongClickListener {
            isEditing.value = true
            true
        }

        isLoading.observe(requireActivity()) {
         //   binding.tvResolution.text = ""
        }

    }


    private fun updateMemoText() {
        isLoading.value = true
        Repository.memoDao.getRandomMemo().subscribeOnBackground(disposables,
            onComplete = {
                isEditing.value = true
                isLoading.value = false
            },
            onError = { throw it },
            onSuccess = {
                isLoading.value = false
                memoList.value = listOf(Memo("sdsdsd", "kjkj"), Memo("sfdfdsdsd", "kjkj"),Memo("ssdsdsd", "kjkj"))

                if(it.isEmpty()){
                    isEditing.value = true
                }



            })
    }


    private fun createMemoFromInput() = Memo(binding.etResolution.text.toString(), getDate())

    fun getDate(): String {
        return SimpleDateFormat("yyyy-MM-dd", resources.configuration.locale).format(Date())
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        disposables.dispose()
        (requireActivity() as? DialogInterface.OnDismissListener)?.onDismiss(dialog)
    }


}