package com.stockbit.stockbit.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.stockbit.stockbit.R
import com.stockbit.stockbit.databinding.FragmentLoginBinding
import org.koin.android.ext.android.inject


class LoginFragment: Fragment() {

    private val loginViewModel: LoginViewModel by inject()
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val TAG = "LoginFragment"
    private val RC_SIGN_IN = 9001

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding !!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity!=null){

            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

            mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

            binding.btnGoogle.setOnClickListener {
                signIn()
            }

            binding.btnLogin.setOnClickListener {

                if(binding.loginUsername.text!!.trim().toString().isEmpty()){
                    Toast.makeText(requireContext(), "Email masih kosong", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if(binding.loginPass.text!!.trim().toString().isEmpty()){
                    Toast.makeText(requireContext(), "Password masih kosong", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                moveToMain()
            }

        }
    }

    private fun moveToMain() {
        Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_mainFragment)
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            updateUI(account)
        } catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        val name = account!!.givenName
        Toast.makeText(requireContext(), "Hi $name", Toast.LENGTH_SHORT).show()
        moveToMain()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}