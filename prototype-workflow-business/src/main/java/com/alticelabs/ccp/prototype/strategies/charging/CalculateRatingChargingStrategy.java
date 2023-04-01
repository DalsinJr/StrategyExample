package com.alticelabs.ccp.prototype.strategies.charging;

import com.alticelabs.ccp.exagon_common_models.message.command.BusinessCommand;
import com.alticelabs.ccp.exagon_common_models.message.status.BusinessStatus;
import com.alticelabs.ccp.prototype.interfaces.INextCommandStrategy;
import com.alticelabs.ccp.prototype.util.PrototypeSteps;
import com.alticelabs.prototype_common_models.commands.ChargingBucketCommand;

import java.util.List;

public class CalculateRatingChargingStrategy implements INextCommandStrategy {
    @Override
    public List<BusinessCommand> getNextCommands(BusinessStatus businessStatus) {
        return List.of(new BusinessCommand(new ChargingBucketCommand(), PrototypeSteps.CHARGING_BUCKETS_CHARGING.name()));
    }
}
