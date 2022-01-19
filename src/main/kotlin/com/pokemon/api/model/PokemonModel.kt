package com.pokemon.api.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "pokemon")
data class PokemonModel(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    var nome: String = "",
    var altura: Double = 0.0,
    var peso: Double = 0.0,
    var categoria: String = "",
    var habilidades: String = "",
    var tipo: String = "",
    var imagem: String = ""

    )