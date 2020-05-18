package br.edu.faculdadedelta.projetoseriado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import br.edu.faculdadedelta.projetoseriado.adapter.SeriadoAdapter;
import br.edu.faculdadedelta.projetoseriado.dao.SeriadoDAO;
import br.edu.faculdadedelta.projetoseriado.modelo.Seriado;

public class ListaActivity extends AppCompatActivity {

    public static final int RESULT_CODE_SUCESS = 1;
    public static final int RESULT_CODE_ERROR = 0;
    private String genero, status, nome, comentario, nota, data;

    private ListView lvLista;
    private SeriadoDAO dao = new SeriadoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvLista = findViewById(R.id.lvLista);

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Seriado seriadoSelecionado = (Seriado) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("seriadoSelecionado", seriadoSelecionado);
                startActivity(intent);
            }
        });

        lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Seriado seriadoSelecionado = (Seriado) adapterView.getItemAtPosition(i);
                dao.excluir(seriadoSelecionado);
                carregarLista();
                return false;
            }
        });
        }

        @Override
        protected void onResume() {
            super.onResume();
            carregarLista();
        }

        private void carregarLista(){
            SeriadoAdapter adapter =
                    new SeriadoAdapter(dao.listar(), getBaseContext());
            lvLista.setAdapter(adapter);
        }
    }
