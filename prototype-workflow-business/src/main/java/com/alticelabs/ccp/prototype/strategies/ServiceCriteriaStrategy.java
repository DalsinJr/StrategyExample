package com.alticelabs.ccp.prototype.strategies;

import com.alticelabs.ccp.exagon_common_models.message.command.BusinessCommand;
import com.alticelabs.ccp.exagon_common_models.message.status.BusinessStatus;
import com.alticelabs.ccp.prototype.interfaces.INextCommandStrategy;
import com.alticelabs.ccp.prototype.util.PrototypeSteps;
import com.alticelabs.prototype_common_models.commands.CheckEligibilityCommand;
import com.alticelabs.prototype_common_models.commands.GenerateLDRCommand;
import com.alticelabs.prototype_common_models.service_criteria.ServiceCriteriaWorkFlowStatus;

import java.util.List;


public class ServiceCriteriaStrategy implements INextCommandStrategy {
    @Override
    public List<BusinessCommand> getNextCommands(BusinessStatus businessStatus) {
        ServiceCriteriaWorkFlowStatus status = (ServiceCriteriaWorkFlowStatus) businessStatus.getStatus();
        return switch (status) {
            case  CHARGING -> List.of(new BusinessCommand(new CheckEligibilityCommand(), PrototypeSteps.CHECK_ELIGIBILITY_CHARGING.name()));
            case  AOC -> List.of(new BusinessCommand(new CheckEligibilityCommand(),PrototypeSteps.CHECK_ELIGIBILITY_AOC.name()));
            case  INVALID -> List.of(new BusinessCommand(new GenerateLDRCommand(),PrototypeSteps.GENERATE_LDR_INVALID_SERVICE.name()));
        };
    }
}
