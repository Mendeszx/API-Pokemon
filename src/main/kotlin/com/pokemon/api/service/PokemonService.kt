package com.pokemon.api.service

import com.pokemon.api.model.PokemonModel
import com.pokemon.api.repository.PokemonRepository
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate

@Service
class PokemonService(
    val repository: PokemonRepository
) {

    val restTemplate = RestTemplate()


    fun consultarPokeAPI(): ResponseEntity<*> {
        val url = "https://pokeapi.co/api/v2/pokemon?limit=1118"
        val headers: MultiValueMap<String, String> = LinkedMultiValueMap()
        headers.add(HttpHeaders.USER_AGENT, "Mozilla/5.0")
        headers.add(HttpHeaders.ACCEPT, "*/*")
        val entity: HttpEntity<*> = HttpEntity<Any>(headers)
        val response: ResponseEntity<String> = restTemplate.exchange(
            url, HttpMethod.GET, entity,
            String::class.java
        )
        return response
    }

    fun consultarSePokemonExiste(pokemon: PokemonModel): ResponseEntity<*>? {
        return if (repository.findByNome(pokemon.nome).isPresent || repository.findById(pokemon.id).isPresent) {
            ResponseEntity.status(400).body("POKEMON JÁ EXISTE")
        } else {
            try {
                repository.save(pokemon)
                return ResponseEntity.status(201).body("POKEMON CRIADO COM SUCESSO!")
            } catch (e: Exception) {
                 throw (Exception("ERRO AO SALVAR POKEMON"))
            }
        }
    }
}