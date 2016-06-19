package com.company;

/**
 * Created on 09/03/2016.
 */
public class Owner {

    private String _name, _telNumber;

    public Owner(String _name, String _telNumber) {
        this._name = _name;
        this._telNumber = _telNumber;
    }

    public Owner(Owner owner) {
        this._name = owner._name;
        this._telNumber = owner._telNumber;
    }

    public String getName() {
        return _name;
    }

    public String getTelNumber() {
        return _telNumber;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(obj == this)
            return true;
        if(obj instanceof Owner){
            Owner other = (Owner) obj;
            return other._name.equals(this._name) && other._telNumber.equals(this._telNumber);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Owner name: " + _name + ", Tel: " + _telNumber;
    }
}
