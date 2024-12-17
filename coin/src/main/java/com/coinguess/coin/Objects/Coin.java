package com.coinguess.coin.Objects;

import java.io.Serializable;

// ID,Obverse Copyright,Obverse License,Obverse Picture,Reverse Copyright,Reverse License,Reverse Picture,Issuer
public class Coin implements Serializable {
    public int ID;
    public String obverseCopyright;
    public String obverseLiscense;
    public String obversePic;
    public String reverseCopyright;
    public String reverseliscense;
    public String reversePic;
    public String issuer;

    public Coin(int iD, String obverseCopyright, String obverseLiscense, String obversePic, String reverseCopyright, String reverseliscense,
            String reversePic, String issuer) {
        ID = iD;
        this.obverseCopyright = obverseCopyright;
        this.obverseLiscense = obverseLiscense;
        this.obversePic = obversePic;
        this.reverseCopyright = reverseCopyright;
        this.reverseliscense = reverseliscense;
        this.reversePic = reversePic;
        this.issuer = issuer;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getObverseCopyright() {
        return obverseCopyright;
    }

    public void setObverseCopyright(String obverseCopyright) {
        this.obverseCopyright = obverseCopyright;
    }

    public String getObverseLiscense() {
        return obverseLiscense;
    }

    public void setObverseLiscense(String obverseLiscense) {
        this.obverseLiscense = obverseLiscense;
    }

    public String getObversePic() {
        return obversePic;
    }

    public void setObversePic(String obversePic) {
        this.obversePic = obversePic;
    }

    public String getReverseCopyright() {
        return reverseCopyright;
    }

    public void setReverseCopyright(String reverseCopyright) {
        this.reverseCopyright = reverseCopyright;
    }

    public String getReversePic() {
        return reversePic;
    }

    public void setReversePic(String reversePic) {
        this.reversePic = reversePic;
    }

    public String getReverseliscense() {
        return reverseliscense;
    }

    public void setReverseliscense(String reverseliscense) {
        this.reverseliscense = reverseliscense;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

}