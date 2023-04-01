package com.alticelabs.ccp.prototype;

import com.alticelabs.ccp.exagon_business_interfaces_lib.workflow.IWorkFlow;
import com.alticelabs.ccp.exagon_common_models.message.command.BusinessCommand;
import com.alticelabs.ccp.exagon_common_models.message.status.BusinessStatus;
import com.alticelabs.ccp.prototype.interfaces.INextCommandStrategy;
import com.alticelabs.ccp.prototype.strategies.aoc.CalculateRatingAOCStrategy;
import com.alticelabs.ccp.prototype.strategies.aoc.CheckEligibilityOACStrategy;
import com.alticelabs.ccp.prototype.strategies.aoc.GenerateLDRAOCStrategy;
import com.alticelabs.ccp.prototype.strategies.aoc.UpdateRecordsAOCStrategy;
import com.alticelabs.ccp.prototype.strategies.charging.*;
import com.alticelabs.ccp.prototype.strategies.invalid_service.GenerateLDRInvalidServiceStrategy;
import com.alticelabs.ccp.prototype.strategies.shared.GenerateLDRNotEligibleStrategy;
import com.alticelabs.ccp.prototype.strategies.ServiceCriteriaStrategy;
import com.alticelabs.ccp.prototype.strategies.shared.UpdateRecordsNotEligibleStrategy;
import com.alticelabs.ccp.prototype.util.PrototypeSteps;
import com.alticelabs.prototype_common_models.commands.ProcessServiceCriteriaCommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrototypeWorkFlowImpl implements IWorkFlow {
    private Map<PrototypeSteps, INextCommandStrategy> strategyMap;

    public PrototypeWorkFlowImpl() {
        setupStrategies();

    }
    @Override
    public List<BusinessCommand> getNextCommands(BusinessStatus businessStatus) {
        return businessStatus == null ? null :
                strategyMap.get(PrototypeSteps.valueOf(businessStatus.getStepIdentifier())) == null ? null :
                        strategyMap.get(PrototypeSteps.valueOf(businessStatus.getStepIdentifier())).getNextCommands(businessStatus);
    }

    @Override
    public List<BusinessCommand> getFirstCommands() {
        return List.of(new BusinessCommand(new ProcessServiceCriteriaCommand(),
                PrototypeSteps.VERIFY_SERVICE_CRITERIA.name()));
    }

    private void setupStrategies() {
        strategyMap = new HashMap<>();
        strategyMap.put(PrototypeSteps.VERIFY_SERVICE_CRITERIA, new ServiceCriteriaStrategy());
        //Charging Flow
        strategyMap.put(PrototypeSteps.CHECK_ELIGIBILITY_CHARGING, new CheckEligibilityChargingStrategy());
        strategyMap.put(PrototypeSteps.CALCULATE_RATING_CHARGING, new CalculateRatingChargingStrategy());
        strategyMap.put(PrototypeSteps.CHARGING_BUCKETS_CHARGING, new ChargingBucketChargingStrategy());
        strategyMap.put(PrototypeSteps.UPDATE_RECORDS_CHARGING, new UpdateRecordsChargingStrategy());
        strategyMap.put(PrototypeSteps.GENERATE_LDR_CHARGING, new GenerateLDRChargingStrategy());
        //AOC Flow
        strategyMap.put(PrototypeSteps.CHECK_ELIGIBILITY_AOC, new CheckEligibilityOACStrategy());
        strategyMap.put(PrototypeSteps.CALCULATE_RATING_AOC, new CalculateRatingAOCStrategy());
        strategyMap.put(PrototypeSteps.UPDATE_RECORDS_AOC, new UpdateRecordsAOCStrategy());
        strategyMap.put(PrototypeSteps.GENERATE_LDR_AOC, new GenerateLDRAOCStrategy());
        //Not Eligible shared flow
        strategyMap.put(PrototypeSteps.UPDATE_RECORDS_NOT_ELIGIBLE, new UpdateRecordsNotEligibleStrategy());
        strategyMap.put(PrototypeSteps.GENERATE_LDR_NOT_ELIGIBLE, new GenerateLDRNotEligibleStrategy());
        //Invalid Service flow
        strategyMap.put(PrototypeSteps.GENERATE_LDR_INVALID_SERVICE, new GenerateLDRInvalidServiceStrategy());
    }


}
