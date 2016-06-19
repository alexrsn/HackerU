package com.company;

/**
 * Created on 09/03/2016.
 */
public class CarSales {

    private Car[] _cars;
    private int _numberOfCars = 0;

    public CarSales(int size) {
        _cars = new Car[size];
    }

    public int getNumberOfCars() {
        return _numberOfCars;
    }

    public boolean addCar(Car car) {
        if (_numberOfCars < _cars.length) {
            _cars[_numberOfCars] = new Car(car);
            _numberOfCars++;
            return true;
        }
        return false;
    }

    public Car getNewest() {
        if (_numberOfCars > 0) {
            int newestIndex = 0, newestYear = 0;
            for (int i = 1; i < _numberOfCars; i++) {
                if (_cars[i].getYear() > newestYear) {
                    newestYear = _cars[i].getYear();
                    newestIndex = i;
                }
            }
            return new Car(_cars[newestIndex]);
        }
        return null;
    }

    public String allAttractive() {
        String attractiveCars = "";
        for (int i = 0; i < _numberOfCars; i++) {
            if (_cars[i].isAttractive())
                attractiveCars += _cars[i] + "\n";
        }
        return attractiveCars;
    }

    public String ownersOfType(String manufacturer) {
        String manufacturerOwners = "";
        for (int i = 0; i < _numberOfCars; i++) {
            if (_cars[i].getManufacturer().equals(manufacturer))
                manufacturerOwners += _cars[i].getOwner() + "\n\n";
        }
        return manufacturerOwners;
    }

    public void makeBidsOnCheapNotOverUsed(String telNumber, int maxPrice) {
        for (int i = 0; i < _numberOfCars; i++) {
            if (!_cars[i].overUsedCar()) {
                if (_cars[i].getHighestBid().getBidPrice() < maxPrice - 100)
                    _cars[i].makeBid(new Bid(_cars[i].getHighestBid().getBidPrice() + 100, telNumber));
            }
        }
    }

    public void removeCar(String licensePlate) {
        for (int i = 0; i < _numberOfCars; i++) {
            if (_cars[i].getLicensePlate().equals(licensePlate)) {
                _cars[i] = _cars[_numberOfCars - 1];
                _cars[_numberOfCars - 1] = null;
                _numberOfCars--;
                break;
            }
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < _numberOfCars; i++) {
            str += _cars[i] + "\n";
        }
        return str;
    }
}
