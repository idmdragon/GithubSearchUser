package com.idm.github.ui.detail.follow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idm.github.R
import com.idm.github.ui.detail.DetailViewModel


class FollowersFragment : Fragment() {

    companion object{
        private const val KEY_NAME = "key_name"

        fun newInstance(username:String): FollowersFragment {
            val fragment = FollowersFragment()
            val bundle = Bundle()
            bundle.putString(KEY_NAME,username)
            fragment.arguments = bundle
            return fragment
        }
    }

     private lateinit var recyclerViewFollowers : RecyclerView
     private lateinit var followAdapter : FollowAdapter
     private val detailViewModel : DetailViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_followers, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val keyword = arguments?.getString(KEY_NAME)

        detailViewModel.getFollowersUser(keyword!!)
        recyclerViewFollowers = view.findViewById(R.id.rv_followers)
        recyclerViewFollowers.layoutManager = LinearLayoutManager(activity)
        activity.let {
            detailViewModel.listFollowers.observe(viewLifecycleOwner){
                followAdapter = FollowAdapter(it)
                followAdapter.notifyDataSetChanged()
                recyclerViewFollowers.adapter = followAdapter
            }
        }

    }
}
