package com.zomercorporation.teste_lucas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Conversor {


    public Pessoa getInformacao(String end){
        String json = ConexaoApi.getJsonFromApi(end);
        Pessoa retorno = parseJson(json);
        return retorno;
    }

    private Pessoa parseJson(String json){
        try {
            Pessoa pessoa = new Pessoa();

            JSONObject jsonObj = new JSONObject(json);
            JSONObject jsonObj2 = new JSONObject(jsonObj.getString("data"));

            pessoa.setEmail(jsonObj2.getString("email"));
            pessoa.setNome(jsonObj2.getString("first_name"));

            return pessoa;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }
}
