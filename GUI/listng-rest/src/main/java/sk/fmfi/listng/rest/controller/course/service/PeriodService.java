package sk.fmfi.listng.rest.controller.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.rest.proxy.course.PeriodApiProxy;

@Service
public class PeriodService {
    
    @Autowired
    private PeriodApiProxy periodApi;
}
