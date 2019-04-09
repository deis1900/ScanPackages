package service;

import annotation.Component;

@Component
public class ServiceDataImpl implements ServiceData {
    public void sys(){
        System.out.println("Some data.");
    }

}
