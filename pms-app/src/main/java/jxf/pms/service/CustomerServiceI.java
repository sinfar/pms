package jxf.pms.service;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import jxf.pms.cmd.CustomerAddCmd;
import jxf.pms.cmd.CustomerListByNameQry;
import jxf.pms.data.CustomerDTO;

public interface CustomerServiceI {

    Response addCustomer(CustomerAddCmd customerAddCmd);

    MultiResponse<CustomerDTO> listByName(CustomerListByNameQry customerListByNameQry);
}
