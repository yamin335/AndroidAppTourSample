package mollah.yamin.apptoursample.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import mollah.yamin.apptoursample.R
import mollah.yamin.apptoursample.databinding.MainActivityBinding
import mollah.yamin.apptoursample.models.AppTourModel
import mollah.yamin.apptoursample.ui.adapters.AppTourSliderAdapter
import mollah.yamin.apptoursample.ui.adapters.AppTourSliderIndicatorAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding

    private lateinit var appTourSlides: ArrayList<AppTourModel>
    private lateinit var appTourSliderAdapter: AppTourSliderAdapter
    private lateinit var appTourSliderIndicatorAdapter: AppTourSliderIndicatorAdapter
    private lateinit var sliderPageChangeCallback: SliderPageChangeCallback
    private var noOfSlides = 0
    private var sliderCurrentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appTourSlides = arrayListOf(
            AppTourModel(getString(R.string.sliderTitle1), getString(R.string.sliderDescription1), R.drawable.img_baby),
            AppTourModel(getString(R.string.sliderTitle2), getString(R.string.sliderDescription2), R.drawable.img_pregnant_mother),
            AppTourModel(getString(R.string.sliderTitle3), getString(R.string.sliderDescription3), R.drawable.img_mother_and_baby)
        )

        noOfSlides = appTourSlides.size

        appTourSliderIndicatorAdapter = AppTourSliderIndicatorAdapter(noOfSlides)
        binding.indicatorView.adapter = appTourSliderIndicatorAdapter

        sliderPageChangeCallback = SliderPageChangeCallback {
            appTourSliderIndicatorAdapter.setIndicatorAt(it)
            sliderCurrentPosition = it
            if (sliderCurrentPosition + 1 == noOfSlides) {
                binding.skipButton.visibility = View.INVISIBLE
                binding.btnNext.text = getString(R.string.done)
            } else {
                binding.skipButton.visibility = View.VISIBLE
                binding.btnNext.text = getString(R.string.next)
            }
        }

        appTourSliderAdapter = AppTourSliderAdapter()
        binding.sliderView.apply {
            adapter = appTourSliderAdapter
            registerOnPageChangeCallback(sliderPageChangeCallback)
            isUserInputEnabled = true // Make it false to disable sliding by user
        }
        appTourSliderAdapter.submitList(appTourSlides)

        binding.skipButton.setOnClickListener {
            finishTour()
        }

        binding.btnNext.setOnClickListener {
            if (++sliderCurrentPosition < noOfSlides) {
                binding.sliderView.setCurrentItem(sliderCurrentPosition, true)
                if (sliderCurrentPosition + 1 == noOfSlides) {
                    binding.skipButton.visibility = View.INVISIBLE
                    binding.btnNext.text = getString(R.string.done)
                }
            } else {
                finishTour()
            }
        }
    }

    private fun finishTour() {
        startActivity(Intent(this, NextActivity::class.java))
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
    }

    inner class SliderPageChangeCallback(private val listener: (Int) -> Unit) : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            listener.invoke(position)
        }
    }
}