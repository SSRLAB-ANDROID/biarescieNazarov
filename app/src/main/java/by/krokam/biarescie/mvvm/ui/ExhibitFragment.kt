package by.krokam.biarescie.mvvm.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.krokam.biarescie.R
import by.krokam.biarescie.databinding.FragmentExhibitBinding
import by.krokam.biarescie.mvvm.viewmodels.ExhibitVM
import by.krokam.biarescie.mvvm.viewmodels.MainViewModel
import by.krokam.biarescie.ui.viewpager.CustomPagerAdapter
import by.krokam.biarescie.ui.viewpager.TextFragment
import by.krokam.biarescie.util.isGone
import by.krokam.biarescie.util.mainViewModel
import com.bumptech.glide.Glide
import io.reactivex.disposables.CompositeDisposable

class ExhibitFragment : Fragment() {

    private lateinit var binding: FragmentExhibitBinding

    private val isToolbarVisible = true

    private lateinit var mainVM: MainViewModel
    private lateinit var vm: ExhibitVM
    private val subscriptions = CompositeDisposable()

    private fun initVM() {
        vm = ViewModelProvider(this)[ExhibitVM::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainVM = mainViewModel!!
        initVM()
        vm.setMainVM(mainVM)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        if (vm.checkIfBindingSaved()){
            binding = FragmentExhibitBinding.inflate(layoutInflater)
            vm.saveBinding(binding)
        } else {
            binding = vm.getBinding()
        }

        binding.toolbar.apply {
            requireActivity().let { act ->
                (act as AppCompatActivity).setSupportActionBar(this)
                setNavigationOnClickListener { requireActivity().onBackPressed() }
            }
            isGone(!isToolbarVisible)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.toolbarTitle.observe(viewLifecycleOwner) {
            binding.tvTitle.text = it
        }

        setupExhibitObserver()
    }

    private fun setupExhibitObserver() {
        binding.pager.isSaveEnabled = false

        vm.selectedExhibit.observe(viewLifecycleOwner) {
            Glide.with(requireContext()).load(it!!.photo).into(binding.ivPhoto)
            vm.toolbarTitle.value = resources.getString(R.string.opis)

            if (it.sound.isNotEmpty()) {
                binding.viewPLayer.visibility = View.VISIBLE
                vm.initializeMediaPlayer(Uri.parse(it.sound), requireContext())
            } else {
                binding.viewPLayer.visibility = View.GONE
            }

            binding.tvName.text = it.name
            binding.tvPlace.text = it.pointMuseum

            binding.apPlayButton.setOnClickListener {
                vm.playAudio()
            }

            binding.pager.adapter = null

            binding.pager.adapter = CustomPagerAdapter(childFragmentManager).apply {
                if (it.text.isNotBlank())
                    addFragment(
                        TextFragment().apply {
                            text = it.text
                            onHeigtChanged = {
                                binding.pager.apply {
                                    layoutParams.height = it
                                    requestLayout()
                                }
                            }
                        },
                        ""
                    )
                else
                    if (it.textLong.isNotBlank() && it.textLong != it.text) {
                        addFragment(
                            TextFragment().apply {
                                text = it.textLong
                                onHeigtChanged = {
                                    binding.pager.apply {
                                        layoutParams.height = it
                                        requestLayout()
                                    }
                                }
                            },
                            ""
                        )
                    }
            }
            binding.tabs.setupWithViewPager(binding.pager)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
    }
}