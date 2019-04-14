package br.com.urpay;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import br.com.urpay.adapters.PokemonAdapter;
import br.com.urpay.interfaces.ListagemApiService;
import br.com.urpay.models.ListPokemon;
import br.com.urpay.models.Pokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListPokemon listPokemon;

    private Pokemon p;
    private ArrayList<Pokemon> pokemons;
    private RecyclerView recyclerView;
    private PokemonAdapter pokemonAdapter;
    private static final String TAG="Poldex";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);

        /*pokemons = new ArrayList<Pokemon>();
        pokemons.add(new Pokemon("bulbasaur","https://pokeapi.co/api/v2/pokemon/1/"));
        pokemons.add(new Pokemon("ivysaur","https://pokeapi.co/api/v2/pokemon/2/"));
        pokemons.add(new Pokemon("venusaur","https://pokeapi.co/api/v2/pokemon/3/"));
        pokemons.add(new Pokemon("charmander","https://pokeapi.co/api/v2/pokemon/4/"));*/

        retrofit();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        pokemonAdapter = new PokemonAdapter(pokemons);
        recyclerView.setAdapter(pokemonAdapter);



    }

    public void retrofit(){
        //https://pokeapi.co/api/v2/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ListagemApiService listagemApiService = retrofit.create(ListagemApiService.class);
        Call<List<Pokemon>> call = listagemApiService.getPokemons();

        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                if(!response.isSuccessful()){
                    Log.i("TAG", "Erro: " + response.code());
                }
                ListPokemon listPokemon = (ListPokemon) response.body();
                pokemons = listPokemon.getResults();
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                Log.e(TAG,"Erro: " + t.getMessage());
            }
        });
    }

    public void enviarListaDePokemons(View view){
        Intent intent = new Intent(this, DetalhesPokemonActivity.class);
        intent.putParcelableArrayListExtra("pokemons",listPokemon.getResults());//corrigir o carrgemaneto da lista de pokemons
        startActivity(intent);
    }
}
