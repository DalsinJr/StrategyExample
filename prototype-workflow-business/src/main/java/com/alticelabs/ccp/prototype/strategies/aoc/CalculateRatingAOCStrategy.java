package com.alticelabs.ccp.prototype.strategies.aoc;

import com.alticelabs.ccp.exagon_common_models.message.command.BusinessCommand;
import com.alticelabs.ccp.exagon_common_models.message.status.BusinessStatus;
import com.alticelabs.ccp.prototype.interfaces.INextCommandStrategy;
import com.alticelabs.ccp.prototype.util.PrototypeSteps;
import com.alticelabs.prototype_common_models.commands.UpdateRecordsCommand;

import java.util.List;

public class CalculateRatingAOCStrategy implements INextCommandStrategy {
    @Override
    public List<BusinessCommand> getNextCommands(BusinessStatus businessStatus) {
        return List.of(new BusinessCommand(new UpdateRecordsCommand(), PrototypeSteps.UPDATE_RECORDS_AOC.name()));
    }
}
