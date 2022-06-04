package br.com.othonbatista.jogodeperguntas;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.othonbatista.jogodeperguntas.R;
import br.com.othonbatista.jogodeperguntas.BancoDeDados;
import br.com.othonbatista.jogodeperguntas.Questoes;
import java.util.List;
import java.util.Random;


public class CadastrarFragment extends Fragment {
    private EditText mEditTextPergunta, mEditTextResposta;

         public CadastrarFragment() {
         }

         @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
 Bundle savedInstanceState) {

         return inflater.inflate(R.layout.fragment_cadastrar, container, false);
         }

         @Override
         public void onActivityCreated(@Nullable Bundle savedInstanceState) {
         super.onActivityCreated(savedInstanceState);

         Button mButtonJogar = getActivity().findViewById(R.id.buttonJogar);
         mButtonJogar.setOnClickListener(mJogarListener);

         mEditTextPergunta = getActivity().findViewById(R.id.editTextPergunta);
         mEditTextResposta = getActivity().findViewById(R.id.editTextResposta);

         Button mButtonInserir = getActivity().findViewById(R.id.buttonInserir);
         mButtonInserir.setOnClickListener(mInserirListener);
         }

            private View.OnClickListener mJogarListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             getActivity().getSupportFragmentManager().beginTransaction()
             .replace(R.id.frameLayout, new JogarFragment())
             .commit();
             }
 };

             private View.OnClickListener mInserirListener = new View.OnClickListener()
    {
            @Override
            public void onClick(View v) {

         String pergunta = mEditTextPergunta.getText().toString();
         String resposta = mEditTextResposta.getText().toString();


         if ((!pergunta.isEmpty()) && (!resposta.isEmpty())) {

             Questoes questoes = new Questoes(pergunta, resposta);

            BancoDeDados.getBancoDeDados(getActivity())
             .getDAO()
             .inserirQuestao(questoes);

             mEditTextPergunta.setText("");
             mEditTextResposta.setText("");

             Toast.makeText(getActivity(), "Inserido com sucesso!",
                    Toast.LENGTH_SHORT).show();
             }
        }
 };
}