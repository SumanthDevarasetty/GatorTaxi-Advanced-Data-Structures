
public class Ride {
    private int rideNumber;
    private int rideCost;
    private int tripDuration;

    public Ride(int rideNumber, int rideCost, int tripDuration) {
        this.setrideNumber(rideNumber);
        this.setrideCost(rideCost);
        this.settripDuration(tripDuration);
    }

    public int getrideNumber() {
        return rideNumber;
    }

    private void setrideNumber(int rideNumber) {
        this.rideNumber = rideNumber;
    }

    public int getrideCost() {
        return rideCost;
    }

    public void setrideCost(int rideCost) {
        this.rideCost = rideCost;
    }

    public int gettripDuration() {
        return tripDuration;
    }

    public void settripDuration(int tripDuration) {
        this.tripDuration = tripDuration;
    }

    @Override
    public String toString() {
        return "(" + this.getrideNumber() + "," + this.getrideCost() + "," + this.gettripDuration() + ")";
    }
}