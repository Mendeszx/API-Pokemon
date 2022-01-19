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

    @GetMapping("/pokemon/{id}")
    fun buscaPorId(@PathVariable id : Long) : Optional<PokemonModel> {
        return repository.findById(id)

    }

    @GetMapping("/pokemon/{nome}")
    fun buscarPorNome(@PathVariable nome : String): Optional<PokemonModel> {
        return repository.findByNome(nome)
    }

    @PostMapping("/pokemon")
    fun pokemonSave(@RequestBody pokemon : PokemonModel): String {
        repository.save(pokemon)
        return "Pokemon salvo com sucesso!"

    }

    @PutMapping("/pokemon")
    fun pokemonEdit(@RequestBody pokemon: PokemonModel) : String {
        if(repository.existsById(pokemon.id)) {
            repository.save(pokemon)
            return "Pokemon atualizado"
        }

        return "Pokemon não encontrado"
    }

    @DeleteMapping("/pokemon/{id}")
    fun pokemonDelete(@PathVariable id : Long) : String {
        if(repository.existsById(id)) {
            repository.deleteById(id)
            return "Pokemon deletado"
        }

        return "Pokemon não encontrado"
    }
}