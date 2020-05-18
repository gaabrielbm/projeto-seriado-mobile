package br.edu.faculdadedelta.projetoseriado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.faculdadedelta.projetoseriado.dao.SeriadoDAO;
import br.edu.faculdadedelta.projetoseriado.modelo.Seriado;

public class MainActivity extends AppCompatActivity {

    private EditText etGenero;
    private EditText etStatus;
    private EditText etNome;
    private EditText etComentario;
    private EditText etNota;
    private EditText etData;

    Date dataConvert = new Date();

    private Seriado seriado = new Seriado();
    private SeriadoDAO dao = new SeriadoDAO();
    private SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etGenero = findViewById(R.id.etGenero);
        etStatus = findViewById(R.id.etStatus);
        etNome = findViewById(R.id.etNome);
        etComentario = findViewById(R.id.etComentario);
        etNota = findViewById(R.id.etNota);
        etData = findViewById(R.id.etData);

        Intent intent = getIntent();

        if (intent != null){
            Seriado seriadoSelecionado = (Seriado)intent.getSerializableExtra("seriadoSelecionado");
            if (seriadoSelecionado != null){
                popularFormulario(seriadoSelecionado);
            }
        }
    }

    private void popularFormulario(Seriado seriadoSelecionado){
        etGenero.setText(seriadoSelecionado.getGenero());
        etStatus.setText(seriadoSelecionado.getStatus());
        etNome.setText(seriadoSelecionado.getNome());
        etComentario.setText(seriadoSelecionado.getComentario());
        etNota.setText(String.valueOf(seriadoSelecionado.getNota()));
        etData.setText(sf.format(seriadoSelecionado.getLancamento()));
        seriado.setId(seriadoSelecionado.getId());
    }

        public void popularModelo () {

            seriado.setGenero(etGenero.getText().toString());
            seriado.setStatus(etStatus.getText().toString());
            seriado.setNome(etNome.getText().toString());
            seriado.setComentario(etComentario.getText().toString());
            seriado.setNota(Integer.parseInt(etNota.getText().toString()));

            Date data = null;
            try{
                data = sf.parse(etData.getText().toString());
            } catch (ParseException e){
                e.printStackTrace();
            }
            seriado.setLancamento(data);
        }

    public void salvar(View view){

        String genero = etGenero.getText().toString();
        String status = etStatus.getText().toString();
        String nome = etNome.getText().toString();
        String comentario = etComentario.getText().toString();
        String nota = etNota.getText().toString();
        String lancamento = etData.getText().toString();

        Date dataConvertida = null;

        try {
            dataConvertida = sf.parse(etData.getText().toString());
        } catch (ParseException e){
            e.printStackTrace();
        }

        if (genero.isEmpty()){
            Toast.makeText(getBaseContext(), "O campo Genero é obrigatório!",
                    Toast.LENGTH_LONG).show();
        } else if (status.isEmpty()) {
            Toast.makeText(getBaseContext(), "O campo Status é obrigatório!",
                    Toast.LENGTH_LONG).show();
        } else if (nome.isEmpty()) {
            Toast.makeText(getBaseContext(), "O campo Nome é obrigatório!",
                    Toast.LENGTH_LONG).show();
        } else if (comentario.isEmpty()) {
            Toast.makeText(getBaseContext(), "O campo Comentario é obrigatório!",
                    Toast.LENGTH_LONG).show();
        } else if (nota.isEmpty()){
            Toast.makeText(getBaseContext(), "O campo Nota é obrigatório!",
                    Toast.LENGTH_LONG).show();
        } else if (lancamento.isEmpty()) {
            Toast.makeText(getBaseContext(), "O campo Data de Lançamento é obrigatório!",
                    Toast.LENGTH_LONG).show();
        } else if (dataConvertida.after(dataConvert)) {
            Toast.makeText(getBaseContext(), "A Data de Lançamento não poder ser maior que a data atual!",
                    Toast.LENGTH_LONG).show();
        } else {

        //INCLUIR
        popularModelo();
        if (seriado.getId() == null){
            dao.incluir(seriado);
            Toast.makeText(getBaseContext(),
                    "Inclusão realizada com sucesso!", Toast.LENGTH_LONG).show();
            limparCampos();

        } else {
        //ALTERAR
                dao.alterar(seriado);
                Toast.makeText(getBaseContext(),
                        "Alteração realizada com sucesso!", Toast.LENGTH_LONG).show();
                limparCampos();
            }
        }
    }

        public void listar (View view){
            Intent intent = new Intent(getBaseContext(), ListaActivity.class);
            startActivity(intent);
        }

        public  void limpar (View view){
            limparCampos();
        }

        private void limparCampos(){
            etGenero.setText("");
            etStatus.setText("");
            etNome.setText("");
            etComentario.setText("");
            etNota.setText("");
            etData.setText("");
            seriado = new Seriado();
        }
}
