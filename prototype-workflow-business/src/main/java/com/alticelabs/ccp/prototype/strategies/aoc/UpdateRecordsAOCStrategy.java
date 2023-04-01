package com.alticelabs.ccp.prototype.strategies.aoc;

import com.alticelabs.ccp.exagon_common_models.message.command.BusinessCommand;
import com.alticelabs.ccp.exagon_common_models.message.status.BusinessStatus;
import com.alticelabs.ccp.prototype.interfaces.INextCommandStrategy;
import com.alticelabs.ccp.prototype.util.PrototypeSteps;
import com.alticelabs.prototype_common_models.commands.GenerateLDRCommand;

import java.util.List;

public class UpdateRecordsAOCStrategy implements INextCommandStrategy {
    @Override
    public List<BusinessCommand> getNextCommands(BusinessStatus businessStatus) {
        return List.of(new BusinessCommand(new GenerateLDRCommand(), PrototypeSteps.GENERATE_LDR_AOC.name()));
    }
}
