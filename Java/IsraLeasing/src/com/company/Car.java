package com.company;

/**
 * Created on 09/03/2016.
 */
public class Car {

    private Owner _owner;
    private String _manufacturer, _licensePlate;
    private boolean _airbags, _leasingOrRental;
    private int _year, _seats, _km, _price, _LevyPrice;
    private Bid _highestBid;
    public final int thisYear = 2016;


    public Car(Owner owner, String manufacturer, boolean airbags, boolean leasingOrRental,
               int year, int seats, int km, int price, int LevyPrice, String licensePlate) {
        this._owner = new Owner(owner);
        this._manufacturer = manufacturer;
        this._airbags = airbags;
        this._leasingOrRental = leasingOrRental;
        this._year = year;
        this._seats = seats;
        this._km = km;
        this._price = price;
        this._LevyPrice = LevyPrice;
        this._licensePlate = licensePlate;
        this._highestBid = new Bid(0, "");
    }

    public Car(Car car) {
        this(car._owner, car._manufacturer, car._airbags, car._leasingOrRental,
                car._year, car._seats, car._km, car._price, car._LevyPrice, car._licensePlate);
        this._highestBid.setTelNum(car._highestBid.getTelNum());
        this._highestBid.setBidPrice(car._highestBid.getBidPrice());
    }

    public Owner getOwner() {
        return new Owner(_owner);
    }

    public String getManufacturer() {
        return _manufacturer;
    }

    public boolean hasAirbags() {
        return _airbags;
    }

    public boolean isLeasingOrRental() {
        return _leasingOrRental;
    }

    public int getYear() {
        return _year;
    }

    public int getSeats() {
        return _seats;
    }

    public int getKm() {
        return _km;
    }

    public int getPrice() {
        return _price;
    }

    public int getLevyPrice() {
        return _LevyPrice;
    }

    public String getLicensePlate() {
        return _licensePlate;
    }

    public Bid getHighestBid() {
        Bid bid = new Bid(this._highestBid);
        return bid;
    }

    public void makeBid(Bid bid) {
        if (bid.getBidPrice() > this._highestBid.getBidPrice())
            this._highestBid = new Bid(bid);
    }

    public boolean isAttractive() {
        if (_airbags && _km < 20000 && !_leasingOrRental && (thisYear - _year < 4))
            return true;
        return false;
    }

    public boolean fitForFamily(int kids) {
        if (_seats - kids - 2 >= 0)
            return true;
        return false;
    }

    public boolean overUsedCar() {
        int kmPerYear = 12000;
        if (_km > kmPerYear * (thisYear - _year))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "\nManufacturer: " + _manufacturer +
                "\nYear: " + _year +
                "\nOwner: " + _owner +
                "\nHigher bid: " + _highestBid.getBidPrice();
    }
}
