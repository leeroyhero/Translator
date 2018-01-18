package ru.bogdanov.translator.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import ru.bogdanov.translator.FirestoreHelper;
import ru.bogdanov.translator.Item.Pair;
import ru.bogdanov.translator.R;
import ru.bogdanov.translator.TranslateAPI;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewFragment extends Fragment {
EditText editText;
TextView textView;
String english, russian;


    public NewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        editText=getActivity().findViewById(R.id.editText);
        (getActivity().findViewById(R.id.buttonSave)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSaveClicked();
            }
        });
        (getActivity().findViewById(R.id.buttonTranslate)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonTranslateClicked();
            }
        });
        textView=getActivity().findViewById(R.id.textViewVerb);
    }

    private void buttonTranslateClicked() {
        String word=editText.getText().toString();
        word=word.trim();
        if (!word.equals("")){
            editText.setText("");
            translate(word);
        }
    }

    private void buttonSaveClicked() {
        new FirestoreHelper().sendPair(new Pair(russian, english));
        Toast.makeText(getActivity(),"Сохранено "+english+" - "+russian, Toast.LENGTH_SHORT).show();
    }


    void translate(String word){
        english=word;
        new AsyncTranslate().execute(word);
    }

    class AsyncTranslate extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            String s= null;
            try {
                s = TranslateAPI.translate(objects[0].toString());
                return s;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            russian=o.toString();
            textView.setText(o.toString());
        }
    }


}
