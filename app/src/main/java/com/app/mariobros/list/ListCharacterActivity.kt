package com.app.mariobros.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mariobros.R
import com.app.mariobros.databinding.ActivityListCharacterBinding

class ListCharacterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListCharacterBinding
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<List>
    lateinit var imageId: Array<Int>
    lateinit var heading: Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = ""
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
            "Mario: Mario es un fontanero que pasa el día en el mundo de Mario Bros",
            "Luigi: Luigi es el hermano de Mario, un fontanero, que ayuda a Mario a resolver sus problemas",
            "Peach: La princesa Peach, es la reina del mundo champiñon, la enamorada de Mario",
            "Toad: es un chico normal que tocaba la armónica, hasta que el Rey Koopa lo transformó en un Goomba, que posteriormente salva a la Princesa Daisy",
            "Yoshi: Dinosaurio híbrido antropomórfico",
            "D.K.: Gorila que tiene capturada a la dama Paulina",
            "Wario:  Wario era el amigo de la infancia de Mario y él era su ídolo. Interesado en muchas cosas que hacía Mario, Wario soportó una gran cantidad de abusos, para ver cómo volverse como Mario, hasta que se desanimó con el proceso y juró vengarse.",
            "Bowser: Tortuga maligna que quiere hacerse con el control del Reino Champiñon"
        )

        newRecyclerView = binding.recicleviewPj
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)
        newArrayList = arrayListOf<List>()

        getUserData()

    }



    private fun getUserData() {
        for (i in imageId.indices) {
            val news = List(imageId[i], heading[i])
            newArrayList.add(news)
        }

        newRecyclerView.adapter = ListAdapter(newArrayList, this)
    }
}