package com.pokemon.api.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "pokemon")
data class PokemonModel(

    @Id
    var id: Long = 0,
    var nome: String = "",
    var altura: Double = 0.0,
    var peso: Double = 0.0,
    var habilidades: String = "",
    var tipo: String = "",
    var imagem: String = ""

    )