package jxf.pms.domain.customer.gateway;

import jxf.pms.domain.customer.Credit;

//Assume that the credit info is in another distributed Service
public interface CreditGateway {
    Credit getCredit(String customerId);
}
