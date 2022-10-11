package com.pokemon.api.controller

import com.pokemon.api.model.BuscaPokemonModel
import com.pokemon.api.model.PokemonModel
import com.pokemon.api.repository.PokemonRepository
import com.pokemon.api.service.PokemonService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api")
class PokemonController(
    private val service: PokemonService,
    private val repository: PokemonRepository
) {

    @GetMapping("/up")
    fun apiUp(): String {
        return "up"
    }

    @GetMapping("/pokemon")
    fun pokemonList(@PageableDefault (sort = ["id"]) paginacao: Pageable? ): ResponseEntity<*>? {
        val bodyPokemonList = paginacao?.let { repository.findAll(it) }
        return ResponseEntity.status(200).body(bodyPokemonList)
    }

//    @GetMapping("/pokemon/{id}")
//    fun buscaPorId(@PathVariable id : Long) : Optional<PokemonModel> {
//        return repository.findById(id)
//    }

//    @GetMapping("/pokemon/{nome}")
//    fun buscarPorNome(@PathVariable nome : String): Optional<PokemonModel> {
//        return repository.findByNome(nome)
//    }

    @GetMapping("/pokemon/buscar")
    fun buscarPokemon(@RequestBody pokemon: BuscaPokemonModel): Optional<PokemonModel>{
        return if(pokemon.nomePokemon.isEmpty()){
            repository.findById(pokemon.idPokemon)
        }else{
            repository.findByNome(pokemon.nomePokemon)
        }
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