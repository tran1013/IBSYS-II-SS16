package de.ibsys.planningTool.model.xmlExportModel;

/**
 * Created by minhnguyen on 18.07.16.
 */
public class WorkTime {
    private int station;
    private int shift;
    private int overtime;

    public WorkTime(int station, int shift, int overtime) {
        this.station = station;
        this.shift = shift;
        this.overtime = overtime;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public int getOvertime() {
        return overtime;
    }

    public void setOvertime(int overtime) {
        this.overtime = overtime;
    }

    @Override
    public String toString() {
        return "WorkTime{" +
                "station=" + station +
                ", shift=" + shift +
                ", overtime=" + overtime +
                '}';
    }
}
