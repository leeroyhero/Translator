package ru.bogdanov.translator.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import ru.bogdanov.translator.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFragment extends Fragment {
TextView  textViewTranslate, textViewGroup, textViewVerb;
Button buttonGerung, buttonInfinitive, buttonBothNotChange, buttonBothChange;
ArrayList<Verb> list, thatList, list1, list2, tempList;
Button buttonList1, buttonList2, buttonListAll;

String TAG="other";

    public OtherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_other, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textViewTranslate=getActivity().findViewById(R.id.textViewTranslate);
        textViewGroup=getActivity().findViewById(R.id.textViewGroup);
        textViewVerb=getActivity().findViewById(R.id.textViewThatVerb);

        buttonGerung=getActivity().findViewById(R.id.buttonGerund);
        buttonInfinitive=getActivity().findViewById(R.id.buttonInfinitive);
        buttonBothChange=getActivity().findViewById(R.id.buttonBothChange);
        buttonBothNotChange=getActivity().findViewById(R.id.buttonBothNotChange);
        buttonGerung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonAnswerClicked(2);
            }
        });
        buttonInfinitive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonAnswerClicked(1);
            }
        });
        buttonBothChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonAnswerClicked(4);
            }
        });
        buttonBothNotChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonAnswerClicked(3);
            }
        });

        buttonList1=getActivity().findViewById(R.id.buttonList1);
        buttonList2=getActivity().findViewById(R.id.buttonList2);
        buttonListAll=getActivity().findViewById(R.id.buttonListAll);
        buttonList1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thatList=new ArrayList<>(list1);
                Toast.makeText(getActivity(), "Лист 1 загружен", Toast.LENGTH_SHORT).show();
                tempList=new ArrayList<>(thatList);
            }
        });
        buttonList2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thatList=new ArrayList<>(list2);
                Toast.makeText(getActivity(), "Лист 2 загружен", Toast.LENGTH_SHORT).show();
                tempList=new ArrayList<>(thatList);
            }
        });
        buttonListAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thatList=new ArrayList<>(list);
                Toast.makeText(getActivity(), "Весь список", Toast.LENGTH_SHORT).show();
                tempList=new ArrayList<>(thatList);
            }
        });

        setUpList();
    }

    Verb thatVerb=null;
    void buttonAnswerClicked(int group){
        if (thatVerb==null){
            textViewGroup.setBackgroundColor(Color.TRANSPARENT);
            thatVerb=tempList.get(new Random().nextInt(tempList.size()));

            tempList.remove(thatVerb);
            if (tempList.isEmpty()) tempList=new ArrayList<>(thatList);

            textViewTranslate.setText(thatVerb.getTranslate());

            textViewGroup.setText("----------");
            textViewVerb.setText("----------");
            Log.d("other", "thatVerb==null new "+thatVerb.getVerb()+" "+thatVerb.getTranslate());
        } else {
            if (group==thatVerb.getGroup()) textViewGroup.setBackgroundColor(Color.GREEN);
            else textViewGroup.setBackgroundColor(Color.RED);

            textViewVerb.setText(thatVerb.getVerb());
            String textGroup="Глагол+инфинитив";
            switch (thatVerb.getGroup()){
                case 2:
                    textGroup="Глагол+герундий";
                    break;
                case 3:
                    textGroup="Оба без изменений";
                    break;
                case 4:
                    textGroup="Оба с изменениями";
                    break;
            }

            Log.d("other", "thatVerb!=null "+thatVerb.getVerb());
            textViewGroup.setText(textGroup);
            thatVerb=null;
        }
    }

    private void setUpList() {
        list=new ArrayList<>();
        list.add(new Verb("afford","позволить", 1));
        list.add(new Verb("agree","соглашаться", 1));
        list.add(new Verb("appear","появиться", 1));
        list.add(new Verb("ask","спросить", 1));
        list.add(new Verb("be glad/pleased","радоваться/быть довольным", 1));
        list.add(new Verb("be able","быть способным\n", 1));
        list.add(new Verb("choose","выбирать", 1));
        list.add(new Verb("decide","решать", 1));
        list.add(new Verb("expect","ожидать", 1));
        list.add(new Verb("fail","потерпеть неудачу", 1));
        list.add(new Verb("help","помогать", 1));
        list.add(new Verb("hope","надеятся", 1));
        list.add(new Verb("intend","намереваться", 1));
        list.add(new Verb("learn","учить", 1));
        list.add(new Verb("manage","управлять", 1));
        list.add(new Verb("offer","предлагать", 1));
        list.add(new Verb("prepare","готовиться", 1));
        list.add(new Verb("plan","планировать", 1));
        list.add(new Verb("pretend","притворяться", 1));
        list.add(new Verb("promise","обещать", 1));
        list.add(new Verb("refuse","отказывать", 1));
        list.add(new Verb("seem","казаться", 1));
        list.add(new Verb("used to","что-то было раньше", 1));
        list.add(new Verb("want","хотеть", 1));
        list.add(new Verb("wish","желать", 1));
        list.add(new Verb("would","должен", 1));

        list.add(new Verb("admit","признавать", 2));
        list.add(new Verb("adore","обожать", 2));
        list.add(new Verb("avoid","избегать", 2));
        list.add(new Verb("be worth","стоить чего-то", 2));
        list.add(new Verb("can't help","я не могу не", 2));
        list.add(new Verb("can't stand","трпеть не могу", 2));
        list.add(new Verb("consider","считать/предпологать", 2));
        list.add(new Verb("deny","отрицать", 2));
        list.add(new Verb("dislike","не нравится", 2));
        list.add(new Verb("enjoy","наслаждаться", 2));
        list.add(new Verb("fancy","воображать", 2));
        list.add(new Verb("feel like","такое чувство, что", 2));
        list.add(new Verb("finish","закончить", 2));
        list.add(new Verb("forgive","простить", 2));
        list.add(new Verb("give up","сдаваться", 2));
        list.add(new Verb("imagine","представлять", 2));
        list.add(new Verb("involve","вовлекать", 2));
        list.add(new Verb("keep","держать", 2));
        list.add(new Verb("mind","возражать", 2));
        list.add(new Verb("miss","скучать", 2));
        list.add(new Verb("postpone","откладывать", 2));
        list.add(new Verb("practice","практиковать", 2));
        list.add(new Verb("resist","сопротивляться", 2));
        list.add(new Verb("risk","рисковать", 2));
        list.add(new Verb("spend time","тратить время", 2));
        list.add(new Verb("suggest","предлагать", 2));
        list.add(new Verb("what about","", 2));
        list.add(new Verb("it's no use","бесполезно", 2));
        list.add(new Verb("there is no point","нет смысла", 2));

        list.add(new Verb("begin","начинать", 3));
        list.add(new Verb("continue","продолжать", 3));
        list.add(new Verb("hate","ненавидеть", 3));
        list.add(new Verb("like","нравится", 3));
        list.add(new Verb("love","любить", 3));
        list.add(new Verb("prefer","предпочитать", 3));
        list.add(new Verb("start","начинать", 3));

        list.add(new Verb("forget","забыть", 4));
        list.add(new Verb("regret","сожалеть", 4));
        list.add(new Verb("stop","остановиться", 4));
        list.add(new Verb("try","пробовать", 4));
        list.add(new Verb("go on","продолжать", 4));
        list.add(new Verb("need","нуждаться", 4));

        thatList=new ArrayList<>(list);
        list1=new ArrayList<>(list);
        list2=new ArrayList<>();

        while (list2.size()<list1.size()){
            Verb verb=list1.get(new Random().nextInt(list1.size()));
            list2.add(verb);
            list1.remove(verb);
        }

        Log.d(TAG, "list size "+list.size()+" list1 size "+list1.size()+" list2 size "+list2.size());
        tempList=new ArrayList<>(thatList);
    }


    class Verb{
        String verb, translate;
        int group;

        public Verb(String verb, String translate, int group) {
            this.verb = verb;
            this.translate = translate;
            this.group = group;
        }

        public String getVerb() {
            return verb;
        }

        public void setVerb(String verb) {
            this.verb = verb;
        }

        public String getTranslate() {
            return translate;
        }

        public void setTranslate(String translate) {
            this.translate = translate;
        }

        public int getGroup() {
            return group;
        }

        public void setGroup(int group) {
            this.group = group;
        }
    }
}
