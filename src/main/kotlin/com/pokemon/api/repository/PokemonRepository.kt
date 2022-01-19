package com.pokemon.api.repository

import com.pokemon.api.model.PokemonModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PokemonRepository : JpaRepository<PokemonModel, Long> {
    fun findByNome(nome: String): Optional<PokemonModel>
}