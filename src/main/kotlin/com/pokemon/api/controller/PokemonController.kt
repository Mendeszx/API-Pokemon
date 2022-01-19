package com.pokemon.api.controller

import com.pokemon.api.model.PokemonModel
import com.pokemon.api.repository.PokemonRepository
import com.pokemon.api.service.PokemonService
import org.springframework.http.HttpEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api")
class PokemonController(
    private val service: PokemonService,
    private val repository: PokemonRepository
) {

    @GetMapping("/pokeapi")
    fun createDataBase(): HttpEntity<*> {
        return service.consultarPokeAPI()

    }

    @GetMapping("/pokemon")
    fun pokemonList(): List<PokemonModel> {
        return repository.findAll()
    }

    @PostMapping("/pokemon")
    fun pokemonSave(@RequestBody pokemon : PokemonModel): Optional<PokemonModel> {
        repository.save(pokemon)
        return repository.findByNome(pokemon.nome)
    }

    @PutMapping("/pokemon")
    fun pokemonEdit(@RequestBody pokemon: PokemonModel) : String {
        var idPokemon : Long = pokemon.id
        if(repository.existsById(idPokemon)) {
            repository.save(pokemon)
            return "Pokemon Atualizado"
        }

        return "Pokemon não encontrado"
    }

    @DeleteMapping("/pokemon/{id}")
    fun pokemonDelete(@PathVariable id : Long) : String {

        var idPokemon : Long = id
        if(repository.existsById(idPokemon)) {
            repository.deleteById(id)
            return "Pokemon deletado"
        }

        return "Pokemon não encontrado"
    }
}