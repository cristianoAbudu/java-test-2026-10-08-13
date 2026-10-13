package com.example.benefit;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.*;

@Named
@SessionScoped
public class BenefitRequestBean implements Serializable {
    @Inject
    private BenefitRequestService service;
    private List<BenefitRequest> requests;

    @PostConstruct
    public void init() {
        reload();
    }

    public void reload() {
        requests = service.findAll();
    }

    public void markReviewed(Long id) {
        if (id == null) {
            return;
        }
        service.markReviewed(id);
        reload();
    }

    public List<BenefitRequest> getRequests() {
        return requests;
    }
}