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

class FollowingFragment : Fragment() {

    companion object{
        private const val KEY_NAME = "key_name"

        fun newInstance(keyword:String): FollowingFragment {
            val fragment = FollowingFragment()
            val bundle = Bundle()
            bundle.putString(KEY_NAME,keyword)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var recyclerViewFollowing : RecyclerView
    private lateinit var followAdapter : FollowAdapter
    private val detailViewModel : DetailViewModel by activityViewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val keyword = arguments?.getString(KEY_NAME)
        detailViewModel.getFollowingUser(keyword!!)
        recyclerViewFollowing = view.findViewById(R.id.rv_following)
        recyclerViewFollowing.layoutManager = LinearLayoutManager(activity)
        activity.let {
            detailViewModel.listFollowing.observe(viewLifecycleOwner){
                followAdapter = FollowAdapter(it)
                followAdapter.notifyDataSetChanged()
                recyclerViewFollowing.adapter = followAdapter
            }
        }
    }

}