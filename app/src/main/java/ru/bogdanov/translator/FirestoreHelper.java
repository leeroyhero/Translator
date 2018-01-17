package ru.bogdanov.translator;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

import ru.bogdanov.translator.Item.Pair;

/**
 * Created by Владимир on 16.01.2018.
 */

public class FirestoreHelper {

    public void sendPair(Pair pair){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd.MM");
        db.collection(dateFormat.format(new Date())).document(pair.getEnglish()+"-"+pair.getRussian()).set(pair);
        db.collection("dates").document(dateFormat.format(new Date())).set(pair);
    }

    public void deletePair(String day, String pair){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(day).document(pair).delete();
    }
}
