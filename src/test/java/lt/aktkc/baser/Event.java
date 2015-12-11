package lt.aktkc.baser;

import java.util.Date;


public class Event {

    public Integer carId;
    public Date eventDatetime;
    public Double lon;
    public Double lat;
    public Boolean ign;
    public Integer speed;
    public String locality;

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getCarId() {
        System.out.println("Getting carId");
        return carId;
    }

    @Override
    public String toString() {
        return "Event{" +
                "carId=" + carId +
                ", eventDatetime=" + eventDatetime +
                ", lon=" + lon +
                ", lat=" + lat +
                ", ign=" + ign +
                ", speed=" + speed +
                ", locality='" + locality + '\'' +
                '}';
    }
}
