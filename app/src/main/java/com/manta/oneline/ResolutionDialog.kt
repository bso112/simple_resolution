package com.manta.oneline

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
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

    lateinit var binding: DialogResolutionBinding
    val disposables = CompositeDisposable()

    val isLoading = MutableLiveData<Boolean>()
    val isEditing = MutableLiveData<Boolean>()
    val memoList = MutableLiveData<List<Memo>>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogResolutionBinding.inflate(inflater, container, false)
        binding.apply {
            dialog = this@ResolutionDialog
            lifecycleOwner = this@ResolutionDialog
            adapter = ResolutionAdapter().also {
                it.setOnItemClick(object : ResolutionAdapter.OnClickItemListener {
                    override fun onClick() {
                        dismiss()
                    }

                    override fun onLongClik() {
                        isEditing.value = true
                    }
                })

            }

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

            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)

            Repository.memoDao.createMemo(createMemoFromInput())
                .subscribeOnBackground(disposables) {
                    isLoading.value = false
                    updateMemoText()

                }
            binding.etResolution.text.clear()
        }


        binding.card.setOnClickListener {
            dismiss()
        }

        binding.card.setOnLongClickListener {
            isEditing.value = true
            true
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
                memoList.value = it
                //binding.rvResolution.scrollToPosition(0)
                if (it.isEmpty()) {
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