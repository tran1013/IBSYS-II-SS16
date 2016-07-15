package de.ibsys.planningTool.model;

import java.util.List;

/**
 * Missing Parts 
 * Created by minhnguyen on 13.07.16.
 */
public class WaitingListMissingParts {
    private String missingPartsId;
    private List<WaitingList> waitingLists;

    public String getMissingPartsId() {
        return missingPartsId;
    }

    public void setMissingPartsId(String missingPartsId) {
        this.missingPartsId = missingPartsId;
    }

    public List<WaitingList> getWaitingLists() {
        return waitingLists;
    }

    public void setWaitingLists(List<WaitingList> waitingLists) {
        this.waitingLists = waitingLists;
    }

    public WaitingListMissingParts(String missingPartsId, List<WaitingList> waitingLists) {

        this.missingPartsId = missingPartsId;
        this.waitingLists = waitingLists;
    }

    @Override
    public String toString() {
        return "WaitingListMissingParts{" +
                "missingPartsId='" + missingPartsId + '\'' +
                ", waitingLists=" + waitingLists +
                '}';
    }
}
