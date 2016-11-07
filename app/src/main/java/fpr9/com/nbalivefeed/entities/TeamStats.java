package fpr9.com.nbalivefeed.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by FranciscoPR on 29/10/16.
 */
public class TeamStats {

    @SerializedName("id")
    private String id;

    @SerializedName("a")
    private int assists;

    @SerializedName("fn")
    private String firstName;
    @SerializedName("ln")
    private String lastName;

    @SerializedName("fgm")
    private int fieldGoalsMade;
    @SerializedName("fga")
    private int fieldGoalsAttempt;
    @SerializedName("ftm")
    private int freeThrowsMade;
    @SerializedName("fta")
    private int freeThrowsAttempt;
    @SerializedName("tm")
    private int triplesMade;
    @SerializedName("ta")
    private int triplesAttempt;

    @SerializedName("b")
    private int blocks;
    @SerializedName("ba")
    private int blocksAgainst;

    @SerializedName("dr")
    private int defenseRebounds;
    @SerializedName("or")
    private int offensiveRebounds;

    @SerializedName("f")
    private int fouls;

    @SerializedName("s")
    private int steals;

    @SerializedName("to")
    private int turnovers;
    @SerializedName("p")
    private int points;

    private double fieldGoalsP;
    private double freeThrowsP;
    private double triplesP;
    private int rebounds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFieldGoalsP() {
        fieldGoalsP = (double) fieldGoalsMade / fieldGoalsAttempt;
        fieldGoalsP = fieldGoalsP * 100;
        String result = Double.toString(fieldGoalsP);
        return setOneDecimal(result);
    }

    private String setOneDecimal(String result){
        if(result.length()>=4)
            result = result.substring(0,4);
        else{
            if(result.length()==3)
                result = result.replace(".","");
            else
                result = result.substring(0,2);
        }

        return result;
    }

    public String getFreeThrowsP() {
        freeThrowsP = (double) freeThrowsMade / freeThrowsAttempt;
        freeThrowsP = freeThrowsP * 100;

        String result = Double.toString(freeThrowsP);

        return  setOneDecimal(result);
    }

    public String getTriplesP() {
        triplesP = (double) triplesMade / triplesAttempt;
        triplesP = triplesP * 100;
        String result = Double.toString(triplesP);
        return setOneDecimal(result);
    }

    public int getRebounds() {
        rebounds = offensiveRebounds+defenseRebounds;
        return rebounds;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getFieldGoalsMade() {
        return fieldGoalsMade;
    }

    public void setFieldGoalsMade(int fieldGoalsMade) {
        this.fieldGoalsMade = fieldGoalsMade;
    }

    public int getFieldGoalsAttempt() {
        return fieldGoalsAttempt;
    }

    public void setFieldGoalsAttempt(int fieldGoalsAttempt) {
        this.fieldGoalsAttempt = fieldGoalsAttempt;
    }

    public int getFreeThrowsMade() {
        return freeThrowsMade;
    }

    public void setFreeThrowsMade(int freeThrowsMade) {
        this.freeThrowsMade = freeThrowsMade;
    }

    public int getFreeThrowsAttempt() {
        return freeThrowsAttempt;
    }

    public void setFreeThrowsAttempt(int freeThrowsAttempt) {
        this.freeThrowsAttempt = freeThrowsAttempt;
    }

    public int getTriplesMade() {
        return triplesMade;
    }

    public void setTriplesMade(int triplesMade) {
        this.triplesMade = triplesMade;
    }

    public int getTriplesAttempt() {
        return triplesAttempt;
    }

    public void setTriplesAttempt(int triplesAttempt) {
        this.triplesAttempt = triplesAttempt;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getBlocksAgainst() {
        return blocksAgainst;
    }

    public void setBlocksAgainst(int blocksAgainst) {
        this.blocksAgainst = blocksAgainst;
    }

    public int getDefenseRebounds() {
        return defenseRebounds;
    }

    public void setDefenseRebounds(int defenseRebounds) {
        this.defenseRebounds = defenseRebounds;
    }

    public int getOffensiveRebounds() {
        return offensiveRebounds;
    }

    public void setOffensiveRebounds(int offensiveRebounds) {
        this.offensiveRebounds = offensiveRebounds;
    }

    public int getFouls() {
        return fouls;
    }

    public void setFouls(int fouls) {
        this.fouls = fouls;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(int turnovers) {
        this.turnovers = turnovers;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getCompleteName(){
        return firstName+" "+lastName;
    }
}
