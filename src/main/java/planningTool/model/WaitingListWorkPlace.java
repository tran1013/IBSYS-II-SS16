package planningTool.model;

import java.util.List;

/**
 * Created by minhnguyen on 13.07.16.
 */
public class WaitingListWorkPlace {
    private String workplaceId;
    private int necessaryTime;
    private List<WaitingList> waitingLists;

    public WaitingListWorkPlace(String workplaceId, int necessaryTime, List<WaitingList> waitingLists) {
        this.workplaceId = workplaceId;
        this.necessaryTime = necessaryTime;
        this.waitingLists = waitingLists;
    }

    @Override
    public String toString() {
        return "WaitingListWorkPlace{" +
                "workplaceId='" + workplaceId + '\'' +
                ", necessaryTime=" + necessaryTime +
                ", waitingLists=" + waitingLists +
                '}';
    }

    public String getWorkplaceId() {
        return workplaceId;
    }

    public void setWorkplaceId(String workplaceId) {
        this.workplaceId = workplaceId;
    }

    public int getNecessaryTime() {
        return necessaryTime;
    }

    public void setNecessaryTime(int necessaryTime) {
        this.necessaryTime = necessaryTime;
    }

    public List<WaitingList> getWaitingLists() {
        return waitingLists;
    }

    public void setWaitingLists(List<WaitingList> waitingLists) {
        this.waitingLists = waitingLists;
    }
}
