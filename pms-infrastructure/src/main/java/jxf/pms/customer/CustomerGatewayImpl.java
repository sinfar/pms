package jxf.pms.customer;


import jxf.pms.dbo.CustomerDO;
import jxf.pms.domain.customer.Customer;
import jxf.pms.domain.customer.gateway.CustomerGateway;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerGatewayImpl implements CustomerGateway {
    @Autowired
    private CustomerMapper customerMapper;

    public Customer getByById(String customerId){
      CustomerDO customerDO = customerMapper.getById(customerId);
      //Convert to Customer
      return null;
    }
}
