package com.example.newfilemanager;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ResourceBundle;

public class Gestore {
    public String leggiFile(String nomeFile, Context c) {
        StringBuilder sb =new StringBuilder();
        String righeLette="";
        try{
            BufferedReader fileDaLeggere = new BufferedReader(new InputStreamReader(
                    c.openFileInput(nomeFile)));

            while ((righeLette=fileDaLeggere.readLine())!=null) {

                sb.append(righeLette);

            }
        } catch (FileNotFoundException fileNotFoundException) {
            Log.e("gestore","File inesistente");
        } catch (IOException e) {
            Log.e("gestore","Errore IO");
        }

        return  sb.toString();
    }

    public String scriviFile(String nomeFile,Context c ){
        String esito;
        FileOutputStream fileO;
        String testoDaScrivere="Questo è il testo del file creato!";
        try {
            fileO = c.openFileOutput(nomeFile,Context.MODE_PRIVATE);
            fileO.write(testoDaScrivere.getBytes());
            fileO.close();
            esito = " file scritto corretamente";
        } catch (FileNotFoundException e) {
            esito = "Attenzione non sono riuscito a creare il file";

        } catch (IOException e) {
            esito = "Errore in fase di scritturs del file";
        }

        return esito;

    }

    public String leggiRawFile(Context c,int id){
        String testo;
        StringBuilder sb = new StringBuilder();

        Resources res = c.getResources();
        InputStream is =  res.openRawResource(R.raw.prova);



        try{
            BufferedReader fileDaLeggere = new BufferedReader(new InputStreamReader(is));

            while ((testo=fileDaLeggere.readLine())!=null) {

                sb.append(testo);

            }
        } catch (FileNotFoundException fileNotFoundException) {
            Log.e("gestore","File inesistente");
        } catch (IOException e) {
            Log.e("gestore","Errore IO");
        }
        return sb.toString();
    }
    public String leggiAssetFile(Context c){
        String testo;
        StringBuilder sb = new StringBuilder();

        AssetManager as = c.getAssets();


        try{
           InputStream is = as.open("asso");
            BufferedReader fileDaLeggere = new BufferedReader(new InputStreamReader(is));

            while ((testo=fileDaLeggere.readLine())!=null) {

                sb.append(testo);

            }
        } catch (FileNotFoundException fileNotFoundException) {
            Log.e("gestore","File inesistente");
        } catch (IOException e) {
            Log.e("gestore","Errore IO");
        }
        return sb.toString();
    }
    public Brano leggiRawJson( Context c,int id) {
        String data = leggiRawFile(c,id);
        Brano b =null;

        try {
            JSONObject jo = new JSONObject(data);
            b =new Brano(jo.getString("titolo"),jo.getString("genere"),jo.getString("autore"),jo.getString("durata"),jo.getString("datapu"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    return b;

    }

}

