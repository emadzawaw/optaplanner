package org.optaplanner.examples.machinereassignment.domain;

import java.util.List;

import org.optaplanner.examples.common.domain.AbstractPersistable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("MrProcess")
public class MrProcess extends AbstractPersistable {

    private MrService service;
    private int moveCost;

    // Order is equal to resourceList so resource.getIndex() can be used
    private List<MrProcessRequirement> processRequirementList;

    public MrProcess() {
    }

    public MrProcess(long id) {
        this.id = id;
    }

    public MrProcess(MrService service) {
        this.service = service;
    }

    public MrProcess(long id, MrService service) {
        super(id);
        this.service = service;
    }

    public MrService getService() {
        return service;
    }

    public void setService(MrService service) {
        this.service = service;
    }

    public int getMoveCost() {
        return moveCost;
    }

    public void setMoveCost(int moveCost) {
        this.moveCost = moveCost;
    }

    public List<MrProcessRequirement> getProcessRequirementList() {
        return processRequirementList;
    }

    public void setProcessRequirementList(List<MrProcessRequirement> processRequirementList) {
        this.processRequirementList = processRequirementList;
    }

    public MrProcessRequirement getProcessRequirement(MrResource resource) {
        return processRequirementList.get(resource.getIndex());
    }

    public long getUsage(MrResource resource) {
        return resource.getIndex() >= processRequirementList.size() ? 0L
                : processRequirementList.get(resource.getIndex()).getUsage();
    }

    public int getUsageMultiplicand() {
        int multiplicand = 1;
        for (MrProcessRequirement processRequirement : processRequirementList) {
            multiplicand *= processRequirement.getUsage();
        }
        return multiplicand;
    }

}
