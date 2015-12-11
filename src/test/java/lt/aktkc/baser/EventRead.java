package lt.aktkc.baser;

import java.util.Date;


public class EventRead {

    public Integer car_id;
    public Date eventDatetime;
    public Double lon2;
    public Double lat2;
    public Integer speed;
    public Integer ign;
    public String locality;

    public void setCar_id(Integer car_id) {
        System.out.println("Setting car_id");
        this.car_id = car_id;
    }

    @Override
    public String toString() {
        return "EventRead{" +
                "car_id=" + car_id +
                ", eventDatetime=" + eventDatetime +
                ", lon2=" + lon2 +
                ", lat2=" + lat2 +
                ", speed=" + speed +
                ", ign=" + ign +
                ", locality='" + locality + '\'' +
                '}';
    }
}
