package com.app.mariobros.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        Snackbar.make(binding.root, "Bienvenidos al mundo de Mario", Snackbar.LENGTH_LONG).show()

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

        skill = arrayOf(
            "Salto alto, fuerza y habilidades acrobáticas, puede lanzar fuego con una Flor de Fuego",
            "Salto más alto que Mario, habilidad para correr más rápido, utiliza una aspiradora para atrapar fantasmas en Luigi's Mansion",
            "Puede flotar temporalmente en el aire, es experta en parasoles y corazones de curación",
            "Alta velocidad y fuerza, es rápido en carreras y ayuda con recursos en el Reino Champiñón",
            "Puede lanzar su lengua para atrapar enemigos, puede transformar enemigos en huevos y usarlos como proyectiles",
            "Superfuerza, habilidad para trepar y golpear fuerte, puede lanzar barriles",
            "Superfuerza, invulnerabilidad temporal al cargar, habilidad para romper bloques pesados",
            "Lanzar fuego, gran resistencia y fuerza, puede crear terremotos al caer"
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