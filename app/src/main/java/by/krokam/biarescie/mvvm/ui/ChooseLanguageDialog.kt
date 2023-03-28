package by.krokam.biarescie.mvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import by.krokam.biarescie.app.Language
import by.krokam.biarescie.databinding.DialogLangBinding

class ChooseLanguageDialog : AppCompatDialogFragment(){

    private lateinit var binding: DialogLangBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogLangBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBel.setOnClickListener {
            (requireActivity() as MainActivity).setLanguage(Language.BEL)
            dismiss()
        }

        binding.btnEng.setOnClickListener {
            (requireActivity() as MainActivity).setLanguage(Language.ENG)
            dismiss()
        }

        binding.btnRus.setOnClickListener {
            (requireActivity() as MainActivity).setLanguage(Language.RUS)
            dismiss()
        }
        binding.btnPl.setOnClickListener {
            (requireActivity() as MainActivity).setLanguage(Language.PL)
            dismiss()
        }
        binding.btnChin.setOnClickListener {
            (requireActivity() as MainActivity).setLanguage(Language.ZH)
            dismiss()
        }
    }
}