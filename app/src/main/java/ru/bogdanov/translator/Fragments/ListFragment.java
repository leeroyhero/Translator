package ru.bogdanov.translator.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;

import ru.bogdanov.translator.FirestoreHelper;
import ru.bogdanov.translator.Item.Pair;
import ru.bogdanov.translator.Keeper;
import ru.bogdanov.translator.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {
    ListView listView;
    SwipeRefreshLayout swipeRefreshLayout;
    Spinner spinner;
    String chosenDate="";
    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView=getActivity().findViewById(R.id.listView);
        swipeRefreshLayout=getActivity().findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        spinner=getActivity().findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String date=((TextView)view.findViewById(android.R.id.text1)).getText().toString();
                chosenDate=date;
                loadFromDB(date);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        loadSpinner();


    }

    void loadSpinner(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("dates").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                ArrayList<String> list=new ArrayList<>();
                for (DocumentSnapshot documentSnapshot:task.getResult()){
                    list.add(documentSnapshot.getId());
                }
                Collections.reverse(list);
                setSpinner(list);
            }
        });
    }

    void setSpinner(ArrayList<String> list){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    void loadFromDB(String day){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(day).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                ArrayList<String> list=new ArrayList<>();
                ArrayList<Pair> listKeeper=new ArrayList<>();
                for (DocumentSnapshot documentSnapshot:task.getResult()){
                    list.add(documentSnapshot.getId());
                    listKeeper.add(documentSnapshot.toObject(Pair.class));
                }
                new Keeper().setList(listKeeper);
                setAdapterToRecycler(list);
            }
        });
    }

    void setAdapterToRecycler(ArrayList<String> list){
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String pair=((TextView) view.findViewById(android.R.id.text1)).getText().toString();
                new FirestoreHelper().deletePair(chosenDate,pair);
                Toast.makeText(getActivity(),"удалено", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        listView.setAdapter(adapter);
    }
}
