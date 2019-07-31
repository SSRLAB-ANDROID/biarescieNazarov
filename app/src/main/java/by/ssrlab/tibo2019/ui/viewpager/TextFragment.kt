package by.krokam.biarescie.ui.viewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.krokam.biarescie.R
import kotlinx.android.synthetic.main.fragment_text.view.*

class TextFragment : Fragment() {
    var text = "КАВО?"
        set(value) {
            field = value
            view?.tvText?.text = value
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_text, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.tvText.text = text
    }
}