package ir.whoisAbel.roompractice.user.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ir.whoisAbel.roompractice.databinding.FragmentUpdateBinding
import ir.whoisAbel.roompractice.db.entities.User
import ir.whoisAbel.roompractice.di.kodeinViewModel
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
                    age = Integer.parseInt(age.toString())
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

}