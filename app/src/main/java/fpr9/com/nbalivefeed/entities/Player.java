package fpr9.com.nbalivefeed.entities;

/**
 * Created by FranciscoPR on 02/11/16.
 */
public class Player {

    //"GROUP_SET","PLAYER_ID","PLAYER_NAME","GP","W","L","W_PCT","MIN","FGM","FGA","FG_PCT","FG3M","FG3A","FG3_PCT","FTM","FTA","FT_PCT","OREB","DREB","REB","AST","TOV","STL","BLK","BLKA","PF","PFD","PTS","PLUS_MINUS","DD2","TD3"
    private String GROUP_SET;
    private String PLAYER_ID;
    private String PLAYER_NAME;
    private int GP;
    private int W;
    private int L;
    private double W_PCT;
    private double MIN;
    private double FGM;
    private double FGA;
    private double FG_PCT;
    private double FG3M;
    private double FG3A;
    private double FG3_PCT;
    private double FTM;
    private double FTA;
    private double FT_PCT;
    private double OREB;
    private double DREB;
    private double REB;
    private double AST;
    private double TOV;
    private double STL;
    private double BLK;
    private double BLKA;
    private double PF;
    private double PFD;
    private double PTS;
    private double PLUS_MINUS;
    private double DD2;
    private double TD3;

    public String getPLAYER_NAME() {
        return PLAYER_NAME;
    }

    public void setPLAYER_NAME(String PLAYER_NAME) {
        this.PLAYER_NAME = PLAYER_NAME;
    }

    public String getGROUP_SET() {
        return GROUP_SET;
    }

    public void setGROUP_SET(String GROUP_SET) {
        this.GROUP_SET = GROUP_SET;
    }

    public String getPLAYER_ID() {
        return PLAYER_ID;
    }

    public void setPLAYER_ID(String PLAYER_ID) {
        this.PLAYER_ID = PLAYER_ID;
    }

    public int getGP() {
        return GP;
    }

    public void setGP(int GP) {
        this.GP = GP;
    }

    public int getW() {
        return W;
    }

    public void setW(int w) {
        W = w;
    }

    public int getL() {
        return L;
    }

    public void setL(int l) {
        L = l;
    }

    public double getW_PCT() {
        return W_PCT;
    }

    public void setW_PCT(double w_PCT) {
        W_PCT = w_PCT;
    }

    public double getMIN() {
        return MIN;
    }

    public void setMIN(double MIN) {
        this.MIN = MIN;
    }

    public double getFGM() {
        return FGM;
    }

    public void setFGM(double FGM) {
        this.FGM = FGM;
    }

    public double getFGA() {
        return FGA;
    }

    public void setFGA(double FGA) {
        this.FGA = FGA;
    }

    public double getFG_PCT() {
        return FG_PCT;
    }

    public void setFG_PCT(double FG_PCT) {
        this.FG_PCT = FG_PCT;
    }

    public double getFG3M() {
        return FG3M;
    }

    public void setFG3M(double FG3M) {
        this.FG3M = FG3M;
    }

    public double getFG3A() {
        return FG3A;
    }

    public void setFG3A(double FG3A) {
        this.FG3A = FG3A;
    }

    public double getFG3_PCT() {
        return FG3_PCT;
    }

    public void setFG3_PCT(double FG3_PCT) {
        this.FG3_PCT = FG3_PCT;
    }

    public double getFTM() {
        return FTM;
    }

    public void setFTM(double FTM) {
        this.FTM = FTM;
    }

    public double getFTA() {
        return FTA;
    }

    public void setFTA(double FTA) {
        this.FTA = FTA;
    }

    public double getFT_PCT() {
        return FT_PCT;
    }

    public void setFT_PCT(double FT_PCT) {
        this.FT_PCT = FT_PCT;
    }

    public double getOREB() {
        return OREB;
    }

    public void setOREB(double OREB) {
        this.OREB = OREB;
    }

    public double getDREB() {
        return DREB;
    }

    public void setDREB(double DREB) {
        this.DREB = DREB;
    }

    public double getREB() {
        return REB;
    }

    public void setREB(double REB) {
        this.REB = REB;
    }

    public double getAST() {
        return AST;
    }

    public void setAST(double AST) {
        this.AST = AST;
    }

    public double getTOV() {
        return TOV;
    }

    public void setTOV(double TOV) {
        this.TOV = TOV;
    }

    public double getSTL() {
        return STL;
    }

    public void setSTL(double STL) {
        this.STL = STL;
    }

    public double getBLK() {
        return BLK;
    }

    public void setBLK(double BLK) {
        this.BLK = BLK;
    }

    public double getBLKA() {
        return BLKA;
    }

    public void setBLKA(double BLKA) {
        this.BLKA = BLKA;
    }

    public double getPF() {
        return PF;
    }

    public void setPF(double PF) {
        this.PF = PF;
    }

    public double getPFD() {
        return PFD;
    }

    public void setPFD(double PFD) {
        this.PFD = PFD;
    }

    public double getPTS() {
        return PTS;
    }

    public void setPTS(double PTS) {
        this.PTS = PTS;
    }

    public double getPLUS_MINUS() {
        return PLUS_MINUS;
    }

    public void setPLUS_MINUS(double PLUS_MINUS) {
        this.PLUS_MINUS = PLUS_MINUS;
    }

    public double getDD2() {
        return DD2;
    }

    public void setDD2(double DD2) {
        this.DD2 = DD2;
    }

    public double getTD3() {
        return TD3;
    }

    public void setTD3(double TD3) {
        this.TD3 = TD3;
    }
}
