package com.gestao.reise.reisecommon.source;

import android.util.Log;

import com.gestao.reise.reisecommon.model.Passageiro;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/**
 * Created by bruno on 31/08/17.
 */

public class DA {
    private FirebaseDatabase database;
    private DatabaseReference root;

    public DA() {
        database = FirebaseDatabase.getInstance();
        root = database.getReference();
    }

    public void buscarPassageiros(Function0<Unit> function) {
        ArrayList<Passageiro> passageiros = new ArrayList<>();

        root.child("passageiros").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("DATASSS", "OK");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("DATASSS", "error");
            }
        });
    }
}
