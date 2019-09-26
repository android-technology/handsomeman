package com.tt.handsomeman.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.fragment.app.Fragment

import com.tt.handsomeman.R

class OnBoardingSlidePageFragment : Fragment() {

    private var image: Int = 0
    private var description: Int = 0

    // Store instance variables based on arguments passed
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        image = arguments!!.getInt("image", 0)
        description = arguments!!.getInt("description")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(
                R.layout.on_boarding_fragment_slide_page, container, false) as ViewGroup

        val imgOnBoarding = rootView.findViewById<ImageView>(R.id.imageViewOnBoarding)
        val txtOnBoarding = rootView.findViewById<TextView>(R.id.textViewOnBoarding)
        imgOnBoarding.setImageResource(image)
        txtOnBoarding.setText(description)

        return rootView
    }

    companion object {

        internal fun newInstance(image: Int, description: Int): OnBoardingSlidePageFragment {
            val screenSlidePageFragment = OnBoardingSlidePageFragment()
            val args = Bundle()
            args.putInt("image", image)
            args.putInt("description", description)
            screenSlidePageFragment.arguments = args
            return screenSlidePageFragment
        }
    }
}
