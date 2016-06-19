package com.company;

/**
 * Created on 09/03/2016.
 */
public class Bid {

    private int _bidPrice;
    private String _telNum;

    public Bid(int bidPrice, String telNum) {
        setBidPrice(bidPrice);
        setTelNum(telNum);
    }

    public Bid(Bid bid) {
        this(bid.getBidPrice(),bid.getTelNum());
    }

    public int getBidPrice() {
        return _bidPrice;
    }

    public void setBidPrice(int bidPrice) {
        if (_bidPrice > 0)
            this._bidPrice = bidPrice;
    }

    public String getTelNum() {
        return _telNum;
    }

    public void setTelNum(String telNum) {
        this._telNum = telNum;
    }

    @Override
    public String toString() {
        return "Bid price: " + _bidPrice + " Tel: " + _telNum;
    }

}
