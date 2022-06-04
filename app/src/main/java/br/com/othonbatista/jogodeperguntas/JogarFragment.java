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
import java.util.List;
import java.util.Random;

public class JogarFragment extends Fragment {


 private List<Questoes> mListQuestoes;


         private TextView mTextViewPergunta, mTextViewResposta;

         public JogarFragment() {

         }

         @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
Bundle savedInstanceState) {

         return inflater.inflate(R.layout.fragment_jogar, container, false);
 }

         @Override
public void onActivityCreated(@Nullable Bundle savedInstanceState) {
         super.onActivityCreated(savedInstanceState);


         Button mButtonCadastrar = getActivity().findViewById(R.id.buttonCadastrar);
         mButtonCadastrar.setOnClickListener(mCadastrarListener);

         Button mButtonExibirResposta =
                getActivity().findViewById(R.id.buttonExibirResposta);
         mButtonExibirResposta.setOnClickListener(mExibirRespostaListener);

         Button mButtonPular = getActivity().findViewById(R.id.buttonPular);
         mButtonPular.setOnClickListener(mPularListener);

         mTextViewPergunta = getActivity().findViewById(R.id.textViewPergunta);
         mTextViewResposta = getActivity().findViewById(R.id.textViewResposta);

         mListQuestoes =
                BancoDeDados.getBancoDeDados(getActivity()).getDAO().pesquisarTodasQuestoes();

         proximaQuestao();
         }

         private View.OnClickListener mCadastrarListener = new View.OnClickListener()
    {
 @Override
public void onClick(View view) {

         getActivity().getSupportFragmentManager().beginTransaction()
         .replace(R.id.frameLayout, new CadastrarFragment())
 .commit();
 }
 };

 private View.OnClickListener mExibirRespostaListener = new
            View.OnClickListener() {
 @Override
 public void onClick(View v) {

            exibirResposta();
            }
 };

 private View.OnClickListener mPularListener = new View.OnClickListener() {
 @Override
public void onClick(View v) {

             proximaQuestao();
            }
 };

         private void proximaQuestao() {

        if (!mListQuestoes.isEmpty()) {

             int totalDeQuestoes = mListQuestoes.size();

             int indexAleatorio = new Random().nextInt(totalDeQuestoes);

             Questoes questoes = mListQuestoes.get(indexAleatorio);

             mTextViewPergunta.setText(questoes.getPergunta());
             mTextViewResposta.setText(questoes.getResposta());

             mTextViewResposta.setVisibility(View.GONE);
            }
 }

         private void exibirResposta() {

         mTextViewResposta.setVisibility(View.VISIBLE);

        }

}