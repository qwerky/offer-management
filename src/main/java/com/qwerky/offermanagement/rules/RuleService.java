package com.qwerky.offermanagement.rules;

import com.qwerky.offermanagement.model.Offer;
import org.springframework.stereotype.Component;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

import java.util.List;

@Component
public class RuleService {

    private final KieContainer kieContainer;

    public RuleService() {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource("com/qwerky/offermanagement/rules/buyBoxRule.drl"));

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        Results results = kieBuilder.buildAll().getResults();

        kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
    }

    public void fireRules(List<Offer> offers) {
        KieSession kSession = kieContainer.newKieSession();

        offers.forEach(kSession::insert);
        kSession.fireAllRules();

        kSession.dispose();
    }

}
