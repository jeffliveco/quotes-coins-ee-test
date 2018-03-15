package com.quotescoins.service;

import com.quotescoins.core.BusinessFacade;

import javax.ejb.EJB;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

public @Alternative
class DataService {

    @EJB(lookup="java:app/com-quotescoins-quotes-coins-ejb-1.0-SNAPSHOT/BusinessFacadeImpl!com.quotescoins.core.BusinessFacadeLocal")
    private BusinessFacade businessFacadeLocal;
    @EJB(lookup="java:app/com-quotescoins-quotes-coins-ejb-1.0-SNAPSHOT/BusinessFacadeImpl!com.quotescoins.core.BusinessFacadeRemote")
    private BusinessFacade businessFacadeRemote;

    @Produces
    @Default
    public BusinessFacade getBusinessFacadeLocal() {
        return businessFacadeLocal;
    }

    @Produces
    @Default
    public BusinessFacade getBusinessFacadeRemote() {
        return businessFacadeRemote;
    }
}
