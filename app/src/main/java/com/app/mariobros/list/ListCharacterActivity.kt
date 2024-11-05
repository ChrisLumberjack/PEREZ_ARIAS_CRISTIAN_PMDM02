package com.app.mariobros.list

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mariobros.R
import com.app.mariobros.databinding.ActivityListCharacterBinding
import com.app.mariobros.toolbar.BaseActivity
import com.google.android.material.snackbar.Snackbar

class ListCharacterActivity : BaseActivity() {
    private lateinit var binding: ActivityListCharacterBinding
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<List>
    lateinit var imageId: Array<Int>
    lateinit var heading: Array<String>
    lateinit var skill: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = ""

        Snackbar.make(binding.root, getString(R.string.welcome_message), Snackbar.LENGTH_LONG).show()

        binding.toolbar.toolbarArrow.setOnClickListener {
            finish()
        }
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

        newRecyclerView = binding.recicleviewPj
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)
        newArrayList = arrayListOf<List>()

        getUserData()
    }

    private fun getUserData() {
        for (i in imageId.indices) {
            val news = List(imageId[i], heading[i], skill[i])
            newArrayList.add(news)
        }

        newRecyclerView.adapter = ListAdapter(newArrayList, this)
    }
}
