package Model;

public class Geolocalisation {
    private double  _Latitude;
    private double  _Longitude;
    
    public Geolocalisation (double latitude, double longitude){
        _Latitude = latitude;
        _Longitude = longitude;
    }
    
    public double getLatitude(){
        return _Latitude;
    }

    public void setLatitude(double latitude){
        _Latitude = latitude;
    }
    
    public double getLongitude(){
        return _Longitude;
    }

    public void setLongitude(double longitude){
        _Longitude = longitude;
    }
}
