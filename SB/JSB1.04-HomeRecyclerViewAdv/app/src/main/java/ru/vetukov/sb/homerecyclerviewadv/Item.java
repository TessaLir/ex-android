package ru.vetukov.sb.homerecyclerviewadv;

public class Item {

    private String mLogo;
    private int mLogoColor;

    public Item(String mLogo, int mLogoColor) {
        this.mLogo = mLogo;
        this.mLogoColor = mLogoColor;
    }

    public String getmLogo() {
        return mLogo;
    }

    public void setmLogo(String mLogo) {
        this.mLogo = mLogo;
    }

    public int getmLogoColor() {
        return mLogoColor;
    }

    public void setmLogoColor(int mLogoColor) {
        this.mLogoColor = mLogoColor;
    }
}
