package com.example.mynotes.View

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.example.mynotes.App
import com.example.mynotes.DI.ViewModelFactory
import com.example.mynotes.Model.Note
import com.example.mynotes.R
import com.example.mynotes.Utility.Utility
import com.example.mynotes.ViewModel.NoteViewModel
import com.example.mynotes.databinding.FragmentAddNoteBinding

import javax.inject.Inject

class AddNoteFragment : Fragment() {
 lateinit var addNoteBinding: FragmentAddNoteBinding
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var noteViewModel: NoteViewModel
    lateinit var navController: NavController
    var note_id:Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as App).applicationComponent.inject(this)
        setHasOptionsMenu(true)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addNoteBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_add_note, container, false)
        return addNoteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)
        noteViewModel= ViewModelProvider(this,viewModelFactory).get(NoteViewModel::class.java)
        addNoteBinding.savebtn.setOnClickListener{
            checkUserInput()
        }


    }



    private fun checkUserInput() {
        Log.e("check","checking user input")
        var title=addNoteBinding.titleET.text.toString().trim()
        var description=addNoteBinding.descriptionET.text.toString().trim()
        if(title.isEmpty()){
            addNoteBinding.titleET.error="Please Enter a valid Title"
            return
        }
        else{
            if(description.isEmpty()){
                addNoteBinding.descriptionET.error="Description can not be Empty"
            }
            else{
                var modificationTime=System.currentTimeMillis()
                    saveToDatabase(Note(title,description,modificationTime))




            }
        }

    }

    private fun saveToDatabase(note : Note) {
        Log.e("database","saving to the database")
        noteViewModel.insertNote(note)
        navController.navigate(R.id.action_addNoteFragment_to_homeFragment)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_add,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.save_menu->{
                checkUserInput()
                true
            }

            else->super.onOptionsItemSelected(item)

        }
    }

}
