package com.example.mynotes.View

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.App
import com.example.mynotes.DI.ViewModelFactory
import com.example.mynotes.Model.Note
import com.example.mynotes.NoteAdapter
import com.example.mynotes.OnItemClickListener
import com.example.mynotes.R
import com.example.mynotes.ViewModel.NoteViewModel
import com.example.mynotes.databinding.FragmentHomeBinding
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(),OnItemClickListener {
    lateinit var homeBinding: FragmentHomeBinding
    @Inject
    lateinit var viewModelFactory:ViewModelFactory
    lateinit var noteViewModel:NoteViewModel
    lateinit var navController:NavController
     var noteList=ArrayList<Note>()
     var colorList=ArrayList<Int>()
    lateinit var adapter:NoteAdapter

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
         homeBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return homeBinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=Navigation.findNavController(view)
        noteViewModel=ViewModelProvider(this,viewModelFactory).get(NoteViewModel::class.java)
        homeBinding.noterv.layoutManager=GridLayoutManager(context,2)
        adapter= NoteAdapter(noteList,this,requireContext())
        homeBinding.noterv.adapter=adapter
        noteViewModel.observeAllNotes().observe(viewLifecycleOwner, Observer {
            noteList.clear()
            for(item in it){
                noteList.add(item)
            }
            adapter.notifyDataSetChanged()
        })
        homeBinding.fab.setOnClickListener {

            navController.navigate(R.id.action_homeFragment_to_addNoteFragment)
        }
        itemTouchHelper.attachToRecyclerView(homeBinding.noterv)

    }

    var itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            noteViewModel.deleteNote(adapter.getNoteAt(viewHolder.adapterPosition))
            adapter.notifyDataSetChanged()
        }
    })

    override fun OnItemClick(note: Note?) {

        val action= note?.id?.let { HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(it) }

        if (action != null) {
            navController.navigate(action)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_home,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         return when(item.itemId){
            R.id.add_menu->{
                navController.navigate(R.id.action_homeFragment_to_addNoteFragment)
                true
            }
            R.id.delete_all_menu->{
                noteViewModel.deleteAllNotes()
                adapter.notifyDataSetChanged()
                true
            }
             else->super.onOptionsItemSelected(item)

        }
    }


}


