package by.krokam.biarescie.mvvm.ui

import android.os.Bundle
import android.support.v7.app.AppCompatDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.krokam.biarescie.R
import by.krokam.biarescie.app.Language
import kotlinx.android.synthetic.main.dialog_lang.*

class ChooseLanguageDialog : AppCompatDialogFragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_lang, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnBel.setOnClickListener {
            (activity!! as MainActivity).setLanguage(Language.BEL)
            dismiss()
        }

        btnEng.setOnClickListener {
            (activity!! as MainActivity).setLanguage(Language.ENG)
            dismiss()
        }

        btnRus.setOnClickListener {
            (activity!! as MainActivity).setLanguage(Language.RUS)
            dismiss()
        }
        btnPl.setOnClickListener {
            (activity!! as MainActivity).setLanguage(Language.PL)
            dismiss()
        }
    }
}