package mollah.yamin.apptoursample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mollah.yamin.apptoursample.R
import mollah.yamin.apptoursample.databinding.NextActivityBinding

class NextActivity : AppCompatActivity() {
    private lateinit var binding: NextActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = NextActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGoPrevious.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
        }
    }
}