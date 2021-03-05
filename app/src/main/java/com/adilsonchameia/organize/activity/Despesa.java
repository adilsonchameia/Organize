package com.adilsonchameia.organize.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.adilsonchameia.organize.R;
import com.adilsonchameia.organize.helper.DateCustom;
import com.adilsonchameia.organize.model.Movimentacao;
import com.google.android.material.textfield.TextInputEditText;

public class Despesa extends AppCompatActivity {

    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;
    private Movimentacao movimentacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);

        campoValor = findViewById(R.id.editValor);
        campoData = findViewById(R.id.editData);
        campoCategoria = findViewById(R.id.editCategoria);
        campoDescricao = findViewById(R.id.editDescricao);

        //Preenche o campo data com a date atual
        campoData.setText( DateCustom.dataAtual() );

    }

    public void salvarDespesa(View view){

        if(validarCampos()) {
            movimentacao = new Movimentacao();
            String data = campoData.getText().toString();
            movimentacao.setValor(Double.parseDouble(campoValor.getText().toString()));
            movimentacao.setCategoria(campoCategoria.getText().toString());
            movimentacao.setDescricao(campoDescricao.getText().toString());
            movimentacao.setData(data);
            movimentacao.setTipo("d");

            movimentacao.salvar(data);
        }
    }

    public boolean validarCampos() {

        String textoValor = campoValor.getText().toString();
        String textoData = campoData.getText().toString();
        String textoCategoria = campoCategoria.getText().toString();
        String textoDescricao = campoDescricao.getText().toString();

        if (!textoValor.trim().isEmpty()) {
            if (!textoData.trim().isEmpty()) {
                if (!textoCategoria.trim().isEmpty()) {
                    if (!textoDescricao.trim().isEmpty()) {
                        return true;
                    } else {
                        Toast.makeText(this, "Descricao nao preenchida", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                } else {
                    Toast.makeText(this, "Categoria nao preenchida", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(this, "Data nao preenchida", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(this, "Valor nao preenchido", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}