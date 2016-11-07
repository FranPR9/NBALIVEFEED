package fpr9.com.nbalivefeed.entities;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by FranciscoPR on 30/10/16.
 */
public class ResponseInterceptor implements Interceptor {
    private static final String TAG = "Interceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        String requestUrl = request.url().toString();
        Response response = chain.proceed(request);
        //final Buffer buffer = new Buffer();
        //byte[] bytes = response.body().bytes();

       // try {
            String stringJson = response.body().string();
            if(stringJson.contains("g_boxscore")){
                stringJson = stringJson.replace("var g_boxscore=","");
            }
            if(stringJson.contains("var g_pbp")){
                stringJson = stringJson.replace("var g_pbp=","");
            }
            if(stringJson.contains("\"resource\":\"teaminfocommon\"")){
                stringJson = stringJson.replace("[[","[");
                stringJson = stringJson.replace("[[","[");
                stringJson = stringJson.replace("]]","]");
                stringJson = stringJson.replace("]]","]");
            }
            if(stringJson.contains("\"rowSet\":[[\"Players\"")){
                stringJson = stringJson.replace("[[","[");
                stringJson = stringJson.replace("]]","]");
                //Log.d(TAG,"first:"+stringJson);
                stringJson = playerStatsReformat(stringJson);
            }
            if(stringJson.contains("\"resource\":\"teamgamelog\"")){
                stringJson = getGamesIds(stringJson);
            }
            if (stringJson.contains("\"name\":\"LineScore\"")){
                stringJson = getScore(stringJson);
            }
            if(stringJson.contains("var g_pregame=")){
                stringJson = stringJson.replace("var g_pregame=","");
            }

            //var g_pbp=
            //JSONObject jsonObject = new JSONObject(stringJson);
            //Log.d(TAG,stringJson);

            MediaType contentType = response.body().contentType();
            ResponseBody body = ResponseBody.create(contentType,stringJson);// create(contentType, jsonObject);
            return response.newBuilder().body(body).build();
    }


    private String getScore(String stringJson) {

        int index = stringJson.indexOf("\"name\":\"LineScore\"");
        int indexrs = stringJson.indexOf("\"rowSet\":[[",index);
        int finali = stringJson.indexOf("]]",indexrs);
        String aux = stringJson.substring(indexrs,finali);
        aux = aux.replace("\"rowSet\":[[","\"TeamHome\":[");
        aux = aux.replace("],[","],\"TeamAway\":[");
        aux = aux.replace("]]","]");

        String response = "{"+aux+"]}";

        return response;
    }

    private String getGamesIds(String stringJson) {

        int index = stringJson.indexOf("\"rowSet\":[[")+11;
        String aux = stringJson.substring(index);
        aux=aux.replace("],["," & ");
        aux=aux.replace("]","");
        aux=aux.replace("}","");
        //Log.d(TAG,"gameIds:"+aux);
        String[] values = aux .split(" & ");
        //Log.d(TAG,"values:"+values.length);
        String idsArray = "\"gameIds\":[";
        for(int i = 0;i<values.length;i++){
            String row = values[i];
            //Log.d(TAG,"row:"+row);
            String[] rowvals = row.split(",");
            //Log.d(TAG,"rowvals:"+rowvals);
            if(i==0){
                idsArray = idsArray+rowvals[1];
            }
            else {
                idsArray = idsArray+","+rowvals[1];
            }
        }

        String oldstr = stringJson.substring(0,index-11);
        stringJson = oldstr+idsArray+"]}]}";

        return stringJson;
    }

    private String playerStatsReformat(String complete) {
        int index = complete.indexOf("\"rowSet\":[\"Players\"");
        String headers ="\"GROUP_SET\",\"PLAYER_ID\",\"PLAYER_NAME\",\"GP\",\"W\",\"L\",\"W_PCT\",\"MIN\",\"FGM\",\"FGA\",\"FG_PCT\",\"FG3M\",\"FG3A\",\"FG3_PCT\",\"FTM\",\"FTA\",\"FT_PCT\",\"OREB\",\"DREB\",\"REB\",\"AST\",\"TOV\",\"STL\",\"BLK\",\"BLKA\",\"PF\",\"PFD\",\"PTS\",\"PLUS_MINUS\",\"DD2\",\"TD3\"";
        String[] headersArray = headers.split(",");
        String oldStr = complete.substring(0,index);
        String newstr = complete.substring(index);
        //Log.d(TAG,"newstr:"+newstr);
        newstr = newstr.replace("\"rowSet\":[", "\"playerStats\":[");
        newstr = newstr.replace("}]}","");
        //Log.d(TAG,"newstr1:"+newstr);
        String returnedString="";
        String constructed="\"playerStats\":[";
        int count = 0;
        for(int i=15;i<newstr.length();i++){
            returnedString = newstr.charAt(i)+"";
            if(i==15){
                returnedString = "{"+headersArray[count]+":\"";
                count++;
            }
            else{
                if(newstr.charAt(i)=='['){
                    returnedString = "{"+headersArray[count]+":";
                    count++;
                }
                if(newstr.charAt(i)==','&&newstr.charAt(i+1)!='['){
                    returnedString = ","+headersArray[count]+":";
                    count++;
                }
                if(newstr.charAt(i)==']'&&i!=newstr.length()-1){
                    returnedString = "}";
                    count=0;
                }
                if(i==newstr.length()-1){
                    returnedString = "}]";
                    count=0;
                }
            }

            constructed = constructed+returnedString;
        }

        constructed = oldStr+constructed+"}]}";
        //Log.d(TAG,"constructed:"+constructed.substring(constructed.length()-30));
        //Log.d(TAG,"length:"+constructed.length());
        return constructed;
    }



}
