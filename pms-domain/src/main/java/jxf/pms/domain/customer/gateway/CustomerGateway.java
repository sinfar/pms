package jxf.pms.domain.customer.gateway;

import jxf.pms.domain.customer.Customer;

public interface CustomerGateway {
    Customer getByById(String customerId);
}
