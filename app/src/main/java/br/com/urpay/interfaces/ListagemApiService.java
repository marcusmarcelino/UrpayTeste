package br.com.urpay.interfaces;

import java.util.List;

import br.com.urpay.models.Pokemon;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ListagemApiService {

    @GET("pokemon/")
    Call<List<Pokemon>> getPokemons();
}
