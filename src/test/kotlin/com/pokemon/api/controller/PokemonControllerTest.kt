package com.pokemon.api.controller

import com.pokemon.api.repository.PokemonRepository
import com.pokemon.api.service.PokemonService
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import io.mockk.*
import java.awt.print.Pageable

internal class PokemonControllerTest {

    private val respository = mockk<PokemonRepository>()
    private val service = mockk<PokemonService>()
    private val pokemonController = PokemonController(service, respository)

    @Test
    fun `verifica se API esta up`() {
        val result = pokemonController.apiUp()
        assertEquals(result, "up")
    }

    @Test
    fun pokemonList() {
        //val result = pokemonController.pokemonList().statusCode.equals(200)
    }

    @Test
    fun buscarPokemon() {
    }

    @Test
    fun pokemonSave() {
    }

    @Test
    fun pokemonEdit() {
    }

    @Test
    fun pokemonDelete() {
    }
}