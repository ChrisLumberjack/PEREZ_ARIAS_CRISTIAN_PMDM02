package com.app.mariobros

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mariobros.databinding.FragmentListcharactersBinding
import com.app.mariobros.list.ListAdapter
import com.google.android.material.snackbar.Snackbar
import com.app.mariobros.list.List

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListCharactersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListCharactersFragment : Fragment() {
    private var _binding: FragmentListcharactersBinding? = null
    private val binding get() = _binding!!
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<List>
    lateinit var imageId: Array<Int>
    lateinit var heading: Array<String>
    lateinit var skill: Array<String>

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListcharactersBinding.inflate(inflater, container, false)

        // Mostrar un Snackbar con un mensaje de bienvenida
        Snackbar.make(binding.root, getString(R.string.welcome_message), Snackbar.LENGTH_LONG).show()

        // Configuración de los datos para el RecyclerView
        imageId = arrayOf(
            R.drawable.mario,
            R.drawable.luigi,
            R.drawable.peach,
            R.drawable.toad,
            R.drawable.yoshi,
            R.drawable.dk,
            R.drawable.wario,
            R.drawable.browser
        )
        heading = arrayOf(
            getString(R.string.mario_heading),
            getString(R.string.luigi_heading),
            getString(R.string.peach_heading),
            getString(R.string.toad_heading),
            getString(R.string.yoshi_heading),
            getString(R.string.dk_heading),
            getString(R.string.wario_heading),
            getString(R.string.bowser_heading)
        )

        skill = arrayOf(
            getString(R.string.mario_skill),
            getString(R.string.luigi_skill),
            getString(R.string.peach_skill),
            getString(R.string.toad_skill),
            getString(R.string.yoshi_skill),
            getString(R.string.dk_skill),
            getString(R.string.wario_skill),
            getString(R.string.bowser_skill)
        )

        // Configurar el RecyclerView
        newRecyclerView = binding.recicleviewPj
        newRecyclerView.layoutManager = LinearLayoutManager(activity)
        newRecyclerView.setHasFixedSize(true)
        newArrayList = arrayListOf<List>()
        getUserData()

        return binding.root
    }

    private fun getUserData() {
        for (i in imageId.indices) {
            val news = List(imageId[i], heading[i], skill[i])
            newArrayList.add(news)
        }

        newRecyclerView.adapter = ListAdapter(newArrayList, parentFragmentManager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListCharactersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
