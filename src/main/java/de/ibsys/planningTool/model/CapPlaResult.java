package de.ibsys.planningTool.model;

/**
 * Created by Duc on 07.08.16.
 */
public class CapPlaResult {
    private Integer workplaceId;
    private int reqCapacity;
    private int shifts;
    private int overtime;

    public CapPlaResult(Integer workplaceId, int reqCapacity, int shifts, int overtime) {
        this.workplaceId = workplaceId;
        this.reqCapacity = reqCapacity;
        this.shifts = shifts;
        this.overtime = overtime;
    }

    public Integer getWorkplaceId() {
        return workplaceId;
    }

    public void setWorkplaceId(Integer workplaceId) {
        this.workplaceId = workplaceId;
    }

    public int getReqCapacity() {
        return reqCapacity;
    }

    public void setReqCapacity(int reqCapacity) {
        this.reqCapacity = reqCapacity;
    }

    public int getShifts() {
        return shifts;
    }

    public void setShifts(int shifts) {
        this.shifts = shifts;
    }

    public int getOvertime() {
        return overtime;
    }

    public void setOvertime(int overtime) {
        this.overtime = overtime;
    }

    @Override
    public String toString() {
        return "CapacityResult{" +
                "workplaceId=" + workplaceId +
                ", reqCapacity=" + reqCapacity +
                ", shifts=" + shifts +
                ", overtime=" + overtime +
                '}';
    }
}
