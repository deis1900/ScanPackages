package service;

import annotation.Autowired;
import annotation.Component;
import annotation.Qualifier;

@Component
public class ServiceImpl implements Service {
    @Autowired
    private ServiceDataImpl serviceData;


    @Override
    @Qualifier
    public void print() {
        serviceData.sys();
    }
}

