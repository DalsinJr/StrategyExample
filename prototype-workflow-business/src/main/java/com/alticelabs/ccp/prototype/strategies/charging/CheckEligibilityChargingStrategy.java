package com.alticelabs.ccp.prototype.strategies.charging;

import com.alticelabs.ccp.exagon_common_models.message.command.BusinessCommand;
import com.alticelabs.ccp.exagon_common_models.message.status.BusinessStatus;
import com.alticelabs.ccp.prototype.interfaces.INextCommandStrategy;
import com.alticelabs.ccp.prototype.util.PrototypeSteps;
import com.alticelabs.prototype_common_models.commands.CalculateRatingCommand;
import com.alticelabs.prototype_common_models.commands.UpdateRecordsCommand;
import com.alticelabs.prototype_common_models.eligibility.EligibilityWorkFlowStatus;

import java.util.List;

public class CheckEligibilityChargingStrategy implements INextCommandStrategy {
    @Override
    public List<BusinessCommand> getNextCommands(BusinessStatus businessStatus) {
        EligibilityWorkFlowStatus status = (EligibilityWorkFlowStatus) businessStatus.getStatus();
        return switch (status) {
            case  ELIGIBLE -> List.of(new BusinessCommand(new CalculateRatingCommand(), PrototypeSteps.CALCULATE_RATING_CHARGING.name()));
            case NOT_ELIGIBLE -> List.of(new BusinessCommand(new UpdateRecordsCommand(),PrototypeSteps.UPDATE_RECORDS_NOT_ELIGIBLE.name()));
        };
    }
}
