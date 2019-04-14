package br.com.urpay;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import br.com.urpay.models.ListPokemon;

public class DetalhesPokemonActivity extends AppCompatActivity {

    private ArrayList<Parcelable> listPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pokemon);

        listPokemon = getIntent().getParcelableArrayListExtra("pokemons");


    }
}
