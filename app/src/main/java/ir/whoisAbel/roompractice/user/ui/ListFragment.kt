package ir.whoisAbel.roompractice.user.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ir.whoisAbel.roompractice.R
import ir.whoisAbel.roompractice.databinding.FragmentListBinding
import ir.whoisAbel.roompractice.di.kodeinViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class ListFragment : Fragment(), KodeinAware {

    private lateinit var binding: FragmentListBinding
    private  val viewModel: UserViewModel by kodeinViewModel()
    override val kodein by closestKodein()
    private lateinit var mAdapter: ListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initValue()
        initView()
        initObservation()
    }

    private fun initObservation() {
        viewModel.users.observe(viewLifecycleOwner, Observer {
            mAdapter.setData(it)
        })
    }


    private fun initValue() {

        mAdapter = ListAdapter()
        binding.rvUser.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    private fun initView() {
        binding.fbAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

    }


}