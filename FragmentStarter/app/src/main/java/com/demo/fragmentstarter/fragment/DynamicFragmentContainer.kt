package com.demo.fragmentstarter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.demo.fragmentstarter.R
import java.util.*

class DynamicFragmentContainer: Fragment(), View.OnClickListener {

    lateinit var add: Button
    lateinit var remove: Button
    lateinit var replace: Button
    private var mReplaceTimes = 0
    private val mFragmentStack: Stack<Fragment> = Stack()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_dynamic_container, container, false)
        add = root.findViewById(R.id.add)
        remove = root.findViewById(R.id.remove)
        replace = root.findViewById(R.id.replace)

        add.setOnClickListener(this)
        remove.setOnClickListener(this)
        replace.setOnClickListener(this)

        return root
    }

    override fun onClick(v: View?) {

        val transaction: FragmentTransaction

        if (v?.id == R.id.add) {
            val fragmentItem = FragmentItem()
            mFragmentStack.push(fragmentItem)

            transaction = parentFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_add_container, fragmentItem)
            transaction.commit()
        } else if (v?.id == R.id.remove) {
            transaction = parentFragmentManager.beginTransaction()
            if (!mFragmentStack.empty()) {
                transaction.remove(mFragmentStack.pop())
            }
            transaction.commit()
        } else if (v?.id == R.id.replace) {
            val fragmentItem = FragmentItem()

            transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_replace_container, fragmentItem)
            transaction.commit()

//            mReplaceTimes++
//            fragmentItem.textView.text = "replaced fragment $mReplaceTimes"
        }
    }
}