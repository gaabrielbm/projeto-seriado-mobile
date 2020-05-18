package br.edu.faculdadedelta.projetoseriado.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.edu.faculdadedelta.projetoseriado.R;
import br.edu.faculdadedelta.projetoseriado.modelo.Seriado;

public class SeriadoAdapter extends BaseAdapter {

    private List<Seriado> listaSeriado;
    private Context context;

    public SeriadoAdapter(List<Seriado> listaSeriado, Context context){
        this.listaSeriado = listaSeriado;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaSeriado.size();
    }

    @Override
    public Object getItem(int i) {
        return listaSeriado.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Seriado seriado = (Seriado) getItem(i);

        View viewRetorno = view.inflate(context, R.layout.item_lista, null);

        TextView tvGenero = viewRetorno.findViewById(R.id.tvGenero);
        tvGenero.setText("Gênero: " + seriado.getGenero());

        TextView tvStatus = viewRetorno.findViewById(R.id.tvStatus);
        tvStatus.setText("Status: " + seriado.getStatus());

        TextView tvNome = viewRetorno.findViewById(R.id.tvNome);
        tvNome.setText("Nome: " + seriado.getNome());

        TextView tvComentario = viewRetorno.findViewById(R.id.tvComentario);
        tvComentario.setText("Comentario: " + seriado.getComentario());

        TextView tvNota = viewRetorno.findViewById(R.id.tvNota);
        tvNota.setText("Nota: " + seriado.getNota());

        TextView tvData = viewRetorno.findViewById(R.id.tvData);
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        tvData.setText("Data: " + sf.format(seriado.getLancamento()));

        if (i % 2 == 0){
            viewRetorno.setBackgroundColor(Color.RED);
        }

        return viewRetorno;
    }
}
