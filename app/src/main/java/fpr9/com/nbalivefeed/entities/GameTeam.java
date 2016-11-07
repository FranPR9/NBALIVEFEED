package fpr9.com.nbalivefeed.entities;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import fpr9.com.nbalivefeed.BuildConfig;

/**
 * Created by FranciscoPR on 29/10/16.
 */
public class GameTeam {

    @SerializedName("ta")
    private String acronym;

    private String date;

    private int q1;
    private int q2;
    private int q3;
    private int q4;
    private int ot1;
    private int ot2;
    private int ot3;
    private int ot4;
    private int ot5;
    private int ot6;
    private int ot7;
    private int ot8;
    private int ot9;
    private int ot10;
    private String img= BuildConfig.img_url;

    @SerializedName("s")
    private int score;

    @SerializedName("tn")
    private String name;

    @SerializedName("tc")
    private String city;

    @SerializedName("tid")
    private String id;

    public String getDate() {
        return date;
    }

    public String getDatefromET(String gameDate) throws ParseException {
        String date = gameDate.replace("T","");
        date = date.substring(0,10);
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
        //SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        sourceFormat.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
        Date parsed = null; // => Date is in UTC now
        parsed = sourceFormat.parse(date);
        TimeZone tz = TimeZone.getDefault();
        SimpleDateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd");
        destFormat.setTimeZone(tz);
        String result = destFormat.format(parsed);


        return result;
    }

    public Date getGameDate(String gameDate) throws ParseException {
        TimeZone tz = TimeZone.getDefault();
        SimpleDateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd");
        destFormat.setTimeZone(tz);
        Date result = destFormat.parse(gameDate);
        return result;
    }

    public String getTimefromET(String gameDate){
        String date = gameDate.replace("T"," ");
        //date = date.substring(0,date.length()-4);
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        sourceFormat.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
        Date parsed = null; // => Date is in UTC now

        TimeZone tz = TimeZone.getDefault();
        SimpleDateFormat destFormat = new SimpleDateFormat("HH:mm");
        destFormat.setTimeZone(tz);
        String result = destFormat.format(parsed);


        return result;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public int getQ1() {
        return q1;
    }

    public void setQ1(int q1) {
        this.q1 = q1;
    }

    public int getQ2() {
        return q2;
    }

    public void setQ2(int q2) {
        this.q2 = q2;
    }

    public int getQ3() {
        return q3;
    }

    public void setQ3(int q3) {
        this.q3 = q3;
    }

    public int getQ4() {
        return q4;
    }

    public void setQ4(int q4) {
        this.q4 = q4;
    }

    public int getOt1() {
        return ot1;
    }

    public void setOt1(int ot1) {
        this.ot1 = ot1;
    }

    public int getOt2() {
        return ot2;
    }

    public void setOt2(int ot2) {
        this.ot2 = ot2;
    }

    public int getOt3() {
        return ot3;
    }

    public void setOt3(int ot3) {
        this.ot3 = ot3;
    }

    public int getOt4() {
        return ot4;
    }

    public void setOt4(int ot4) {
        this.ot4 = ot4;
    }

    public int getOt5() {
        return ot5;
    }

    public void setOt5(int ot5) {
        this.ot5 = ot5;
    }

    public int getOt6() {
        return ot6;
    }

    public void setOt6(int ot6) {
        this.ot6 = ot6;
    }

    public int getOt7() {
        return ot7;
    }

    public void setOt7(int ot7) {
        this.ot7 = ot7;
    }

    public int getOt8() {
        return ot8;
    }

    public void setOt8(int ot8) {
        this.ot8 = ot8;
    }

    public int getOt9() {
        return ot9;
    }

    public void setOt9(int ot9) {
        this.ot9 = ot9;
    }

    public int getOt10() {
        return ot10;
    }

    public void setOt10(int ot10) {
        this.ot10 = ot10;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg(){
        if(acronym.equals("NOP")){
            return img+"no"+".png";
        }
        if(acronym.equals("UTA")){
            return img+"utah"+".png";
        }
        return img+acronym+".png";
    }
}
