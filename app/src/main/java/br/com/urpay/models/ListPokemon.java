package br.com.urpay.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class ListPokemon implements Parcelable {
    public ArrayList<Pokemon> listaDePokemons;

    protected ListPokemon(Parcel in) {
        listaDePokemons = in.createTypedArrayList(Pokemon.CREATOR);
    }

    public static final Creator<ListPokemon> CREATOR = new Creator<ListPokemon>() {
        @Override
        public ListPokemon createFromParcel(Parcel in) {
            return new ListPokemon(in);
        }

        @Override
        public ListPokemon[] newArray(int size) {
            return new ListPokemon[size];
        }
    };

    public ArrayList<Pokemon> getResults() {
        return listaDePokemons;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(listaDePokemons);
    }

    public static final Parcelable.Creator<ListPokemon> LIST_POKEMON_CREATOR = new Creator<ListPokemon>() {
        @Override
        public ListPokemon createFromParcel(Parcel source) {
            return new ListPokemon(source);
        }

        @Override
        public ListPokemon[] newArray(int size) {
            return new ListPokemon[size];
        }
    };
}
