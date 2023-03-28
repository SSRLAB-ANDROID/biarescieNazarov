package by.krokam.biarescie.ui.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import by.krokam.biarescie.R
import by.krokam.biarescie.databinding.FragmentTextBinding
import by.krokam.biarescie.util.onParamMeasured

class TextFragment : Fragment() {

    private lateinit var tvText: TextView

    var text = "КАВО?"
        set(value) {
            field = value
        }

    var onHeigtChanged: ((Int) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_text, container, false)

        tvText = view.findViewById(R.id.tvText)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvText.text = text
        view.onParamMeasured({ view.measuredHeight }) {
            onHeigtChanged?.invoke(it)
        }
    }
}