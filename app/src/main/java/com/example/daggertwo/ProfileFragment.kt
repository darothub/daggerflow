package com.example.daggertwo


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.daggertwo.model.Todo
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : DaggerFragment() {

    lateinit var profileViewModel: ProfileViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        profileViewModel = ViewModelProvider(this, viewModelProviderFactory).get(ProfileViewModel::class.java)

        observeTodo()
    }


    private fun observeTodo(){
        profileViewModel.observeTodoStateToo().removeObservers(viewLifecycleOwner)
        profileViewModel.observeTodoStateToo().observe(viewLifecycleOwner, Observer {

                when(it){
                    is ResourceResponse.Error ->{
                        setErrorDetails(it.message)
                        Log.i("Error", "${it.message}")
                    }
                    is ResourceResponse.Success ->{
                        setTodoDetails(it.data)
                        Log.i("Success", "${it.data}")
                    }

                }




        })
    }

    private fun setTodoDetails(data: Todo?) {
        username.setText(data?.title)
        data?.id?.let { userId.setText(it) }
        data?.userId?.let { other.setText(it) }
    }

    private fun setErrorDetails(message: String?) {
        username.setError(message)
    }


}