package com.example.maps_android;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class leContatosDeJSONString {

    public  List<Contato> transformaContatosDeJSONString(String resposta) { // poe os pokemons na lista
        List<Contato> listaContatos = new ArrayList<>();
        try {
            JSONObject json = new JSONObject(resposta);
            JSONArray contatos = json.getJSONArray("pessoas");
            for (int i = 0; i < contatos.length(); i++) {
                JSONObject pokemon = contatos.getJSONObject(i);
                Contato f = new Contato( // pega o pokemon do json
                        pokemon.getString("nome"),
                        pokemon.getString("email"),
                        pokemon.getDouble("longitude"),
                        pokemon.getDouble("latitude")
                );
                listaContatos.add(f); // adiciona o pokemon a  lista
            }
        } catch (JSONException e) {
            System.err.println("Erro fazendo parse de String JSON: " + e.getMessage());
        }
        return listaContatos; //retorna a lista de pokemon
    }
}
