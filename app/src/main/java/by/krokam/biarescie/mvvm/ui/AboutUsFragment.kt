package by.krokam.biarescie.mvvm.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import by.krokam.biarescie.R
import by.krokam.biarescie.databinding.FragmentAboutBinding
import by.krokam.biarescie.mvvm.viewmodels.AboutUsVM

class AboutUsFragment : BaseFragment<AboutUsVM>() {

    override val contentLayoutID = R.layout.fragment_about
    private lateinit var text: TextView
    private lateinit var mail: TextView

    override fun initVM() {
        vm = ViewModelProvider(this)[AboutUsVM::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text = view.findViewById(R.id.text)
        mail = view.findViewById(R.id.mail)

        text.text = Html.fromHtml(resources.getString(R.string.about_us_text))

        mail.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_SENDTO,
                    Uri.parse("mailto:ssrlab221@gmail.com")
                )
            )
        }
    }
}