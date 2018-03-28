package ru.bogdanov.translator.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import ru.bogdanov.translator.Item.Pair;
import ru.bogdanov.translator.Keeper;
import ru.bogdanov.translator.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LearnFragment extends Fragment {
String TAG="lea_fr";
Button buttonClick;
TextView textView1,textView2;
ArrayList<Pair> list;
Switch switchRussian;

    public LearnFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learn, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        buttonClick=getActivity().findViewById(R.id.buttonClick);
        ((Button) getActivity().findViewById(R.id.buttonLoad)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list=new Keeper().getList();
                Toast.makeText(getActivity(),"Загружено",Toast.LENGTH_SHORT).show();
            }
        });
        textView1=getActivity().findViewById(R.id.textViewWord1);
        textView2=getActivity().findViewById(R.id.textViewWord2);

        switchRussian=getActivity().findViewById(R.id.switchRussian);

        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClickClicked();
            }
        });
    }


    Pair currentPair=null;
    boolean isRussianFirst=true;

    private void buttonClickClicked() {
        if (currentPair==null){
            buttonClick.setText("Показать");

            currentPair=list.get(new Random().nextInt(list.size()));
            isRussianFirst=true;
            if (!switchRussian.isChecked())
            isRussianFirst=new Random().nextBoolean();

            textView2.setText("---------");
            if (isRussianFirst) textView1.setText(currentPair.getRussian());
            else textView1.setText(currentPair.getEnglish());
        }else {
            if (isRussianFirst) textView2.setText(currentPair.getEnglish());
            else textView2.setText(currentPair.getRussian());
            buttonClick.setText("Новое");
            currentPair=null;

        }
    }


}
