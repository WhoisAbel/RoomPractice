package ir.whoisAbel.roompractice.user.ui.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ir.whoisAbel.roompractice.R
import ir.whoisAbel.roompractice.databinding.FragmentUpdateBinding
import ir.whoisAbel.roompractice.db.entities.Address
import ir.whoisAbel.roompractice.db.entities.User
import ir.whoisAbel.roompractice.di.kodeinViewModel
import ir.whoisAbel.roompractice.user.ui.viewModel.UserViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein


class UpdateFragment : Fragment(), KodeinAware {

    private val param by navArgs<UpdateFragmentArgs>()
    private lateinit var binding: FragmentUpdateBinding
    override val kodein: Kodein by closestKodein()
    private val viewModel: UserViewModel by kodeinViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }


    private fun initView() {

        binding.user = param.user

        setHasOptionsMenu(true)

        binding.updateBtn.setOnClickListener {
            updateUser()
        }

    }

    private fun updateUser() {
        val firstName = binding.updateFirstNameEt.text?.toString().orEmpty()
        val lastName = binding.updateLastNameEt.text?.toString().orEmpty()
        val age = binding.updateAgeEt.text

        if (checkInput(firstName, lastName, age.toString())) {
            viewModel.updateData(
                User(
                    id = param.user.id,
                    name = firstName,
                    lastName = lastName,
                    age = Integer.parseInt(age.toString()),
                    address = Address("")
                )
            )
            Toast.makeText(requireContext(), "Successfully updated", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun checkInput(name: String, lName: String, age: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(lName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_munu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.id_delete -> {
                deleteUser()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yse") { _, _ ->
            viewModel.deleteUser(param.user)
            Toast.makeText(
                requireContext(),
                "Successfully removed${param.user.name}",
                Toast.LENGTH_SHORT
            ).show()

            findNavController().navigateUp()
        }

        builder.setNegativeButton("No") { _, _ ->

        }

        builder.setTitle("Delete ${param.user.name}")
        builder.setMessage("Are you sure to delete ${param.user.name}")
        builder.create().show()
    }

}

