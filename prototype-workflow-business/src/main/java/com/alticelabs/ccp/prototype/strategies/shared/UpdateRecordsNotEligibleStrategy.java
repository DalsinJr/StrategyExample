package com.alticelabs.ccp.prototype.strategies.shared;

import com.alticelabs.ccp.exagon_common_models.message.command.BusinessCommand;
import com.alticelabs.ccp.exagon_common_models.message.command.ExagonCommandQualifier;
import com.alticelabs.ccp.exagon_common_models.message.status.BusinessStatus;
import com.alticelabs.ccp.prototype.interfaces.INextCommandStrategy;
import com.alticelabs.ccp.prototype.util.PrototypeSteps;
import com.alticelabs.prototype_common_models.commands.GenerateLDRCommand;

import java.util.List;

public class UpdateRecordsNotEligibleStrategy implements INextCommandStrategy {
    @Override
    public List<BusinessCommand> getNextCommands(BusinessStatus businessStatus) {
        return List.of(new BusinessCommand(new GenerateLDRCommand(),
                PrototypeSteps.UPDATE_RECORDS_NOT_ELIGIBLE.name(),
                List.of(ExagonCommandQualifier.PoNR, ExagonCommandQualifier.FINAL)));
    }
}
