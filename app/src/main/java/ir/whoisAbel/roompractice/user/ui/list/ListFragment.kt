package ir.whoisAbel.roompractice.user.ui.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ir.whoisAbel.roompractice.R
import ir.whoisAbel.roompractice.databinding.FragmentListBinding
import ir.whoisAbel.roompractice.di.kodeinViewModel
import ir.whoisAbel.roompractice.user.ui.viewModel.UserViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein

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

        setHasOptionsMenu(true)
        binding.fbAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_munu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.id_delete -> {
                deleteAllUser()
            }
        }

        return super.onOptionsItemSelected(item)
    }


    private fun deleteAllUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yse") { _, _ ->
            viewModel.deleteAllUser()
            Toast.makeText(
                requireContext(),
                "Successfully removed everything",
                Toast.LENGTH_SHORT
            ).show()

            findNavController().navigateUp()
        }

        builder.setNegativeButton("No") { _, _ ->

        }

        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure to delete everything ?")
        builder.create().show()
    }

}