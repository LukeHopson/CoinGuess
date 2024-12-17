package com.coinguess.coin.Objects;

import java.io.Serializable;

// ID,Obverse Copyright,Obverse License,Obverse Picture,Reverse Copyright,Reverse License,Reverse Picture,Issuer
public class Coin implements Serializable {
    public int ID;
    public String obverseCopyright;
    public String obverseLicense;
    public String obversePic;
    public String reverseCopyright;
    public String reverseLicense;
    public String reversePic;
    public String issuer;

    public Coin(int iD, String obverseCopyright, String obverseLicense, String obversePic, String reverseCopyright, String reverseLicense,
            String reversePic, String issuer) {
        ID = iD;
        this.obverseCopyright = obverseCopyright;
        this.obverseLicense = obverseLicense;
        this.obversePic = obversePic;
        this.reverseCopyright = reverseCopyright;
        this.reverseLicense = reverseLicense;
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

    public String getObverseLicense() {
        return obverseLicense;
    }

    public void setObverselicense(String obverselicense) {
        this.obverseLicense = obverselicense;
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

    public String getReverselicense() {
        return reverseLicense;
    }

    public void setReverselicense(String reverselicense) {
        this.reverseLicense = reverselicense;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

}