import com.xuanlocle.usermanager.R

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object ActivityUtils {

    fun addFragmentToActivity(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        frameId: Int
    ) {
        val transaction = fragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.slide_from_right, R.anim.slide_to_left,
            R.anim.slide_from_left, R.anim.slide_to_right
        ) //default
        transaction.add(frameId, fragment, fragment::class.java.simpleName).addToBackStack(null)
        transaction.commit()
    }

    fun replaceFragment(supportFragmentManager: FragmentManager, fragment: Fragment, frame: Int) {
        supportFragmentManager.beginTransaction()
            .replace(frame, fragment)
            .commit()
    }


    fun addFragmentToActivityWithTag(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        frameId: Int, tag: String
    ) {
        val transaction =
            fragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.slide_from_right, R.anim.slide_to_left,
            R.anim.slide_from_left, R.anim.slide_to_right
        ) //default
        transaction.add(frameId, fragment, tag)
        transaction.commit()
    }

}
