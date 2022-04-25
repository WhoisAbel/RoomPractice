package ir.whoisAbel.roompractice.user.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ir.whoisAbel.roompractice.databinding.FragmentAddBinding
import ir.whoisAbel.roompractice.db.entities.User
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class AddFragment : Fragment(), KodeinAware {

    private lateinit var binding: FragmentAddBinding
    private lateinit var viewModel: UserViewModel
    override val kodein by closestKodein()

    private val factory: UserViewModelFactory by instance()

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

        viewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]

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
            Toast.makeText(requireContext(), "Successfully inserted", Toast.LENGTH_SHORT).show()
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
