package com.alticelabs.ccp.prototype.interfaces;

import com.alticelabs.ccp.exagon_common_models.message.command.BusinessCommand;
import com.alticelabs.ccp.exagon_common_models.message.status.BusinessStatus;

import java.util.List;

public interface INextCommandStrategy {
    public abstract List<BusinessCommand> getNextCommands(BusinessStatus businessStatus);
}
