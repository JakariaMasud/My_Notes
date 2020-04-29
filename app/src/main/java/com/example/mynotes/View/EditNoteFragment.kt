package com.example.mynotes.View

import android.database.DatabaseUtils
import android.os.Bundle
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
import com.example.mynotes.databinding.FragmentEditNoteBinding

import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class EditNoteFragment : Fragment() {
    lateinit var editNoteBinding: FragmentEditNoteBinding
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
        editNoteBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_edit_note, container, false)
        return editNoteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)
        noteViewModel= ViewModelProvider(this,viewModelFactory).get(NoteViewModel::class.java)
        note_id= arguments?.getInt("note_id")
        note_id?.let { updateUi(it) }
        editNoteBinding.updateBTN.setOnClickListener{
            checkUserInput()
        }
    }
    private fun updateUi(id:Int) {
        var note: Note = noteViewModel.getNoteById(id)
        editNoteBinding.editTitleET.setText(note.title)
        editNoteBinding.editDescriptionET.setText(note.description)






    }

    private fun checkUserInput() {
        var title=editNoteBinding.editTitleET.text.toString().trim()
        var description=editNoteBinding.editDescriptionET.text.toString().trim()
        if(title.isEmpty()){
            editNoteBinding.editTitleET.error="Please Enter a valid Title"
            return
        }
        else{
            if(description.isEmpty()){
                editNoteBinding.editDescriptionET.error="Description can not be Empty"
            }
            else{

                var modificationTime= System.currentTimeMillis()
                var note=Note(title,description,modificationTime)
                note.id=note_id
                saveToDatabase(note)

            }
        }

    }
    private fun saveToDatabase(note : Note) {
        noteViewModel.updateNote(note)
        navController.navigate(R.id.action_editNoteFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_edit,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.update_menu->{
               checkUserInput()
                true
            }
            R.id.delete_menu->{
                note_id?.let { noteViewModel.deleteNoteById(it) }
                navController.navigate(R.id.action_editNoteFragment_to_homeFragment)
                true
            }
            else->super.onOptionsItemSelected(item)

        }
    }

}
