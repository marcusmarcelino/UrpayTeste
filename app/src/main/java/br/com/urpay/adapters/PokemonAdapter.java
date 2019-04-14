package br.com.urpay.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.urpay.DetalhesPokemonActivity;
import br.com.urpay.R;
import br.com.urpay.models.Pokemon;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolderPokemon>{

    private List<Pokemon> pokemons;

    public PokemonAdapter (List<Pokemon> pokemons){
        this.pokemons = pokemons;
    }

    public class ViewHolderPokemon extends RecyclerView.ViewHolder{

        private TextView txtNome;

        public ViewHolderPokemon(@NonNull View itemView, final Context context) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNome);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(context, DetalhesPokemonActivity.class);
                    ((AppCompatActivity)context).startActivityForResult(it,0);
                }
            });
        }
    }

    @NonNull
    @Override
    public PokemonAdapter.ViewHolderPokemon onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.linha_pokemon, parent, false);

        ViewHolderPokemon viewHolderPokemon = new ViewHolderPokemon(view, parent.getContext());

        return viewHolderPokemon;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.ViewHolderPokemon holder, int i) {
        if((pokemons != null) && (pokemons.size() > 0)) {

            Pokemon pokemon = pokemons.get(i);
            holder.txtNome.setText(pokemon.getName());
        }
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }


}
