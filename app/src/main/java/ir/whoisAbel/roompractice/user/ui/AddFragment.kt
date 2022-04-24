package ir.whoisAbel.roompractice.user.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ir.whoisAbel.roompractice.R
import ir.whoisAbel.roompractice.databinding.FragmentAddBinding
import ir.whoisAbel.roompractice.db.UserDatabase
import ir.whoisAbel.roompractice.db.entities.User
import ir.whoisAbel.roompractice.user.data.UserLocalDataSource
import ir.whoisAbel.roompractice.user.data.UserRepository


class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initValue()

        initView()

    }


    private fun initValue() {

        val userDatabase = UserDatabase(requireContext())
        val userLocalDataSource = UserLocalDataSource(userDatabase)
        val userRepository = UserRepository(userLocalDataSource)
        val factory = UserViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this,factory)[UserViewModel::class.java]

    }

    private fun initView() {


        binding.addBtn.setOnClickListener {

            insertDataToDatabase()

        }

    }

    private fun insertDataToDatabase() {
        val firstName = binding.addFirstNameEt.text?.toString().orEmpty()
        val lastName = binding.addLastNameEt.text?.toString().orEmpty()
        val age = binding.addAgeEt.text?.toString().orEmpty()

        if (checkInput(firstName, lastName, age)) {
            viewModel.addUserA(
                User(
                    id = 0,
                    name = firstName,
                    lastName = lastName,
                    age = Integer.parseInt(age)
                )
            )
            Toast.makeText( requireContext(),"Successfully inserted", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }else{
            Toast.makeText( requireContext(),"Please fill out all fields", Toast.LENGTH_SHORT).show()
        }

    }

    private fun checkInput(name: String, lName: String, age: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(lName) && age.isEmpty())
    }


}
