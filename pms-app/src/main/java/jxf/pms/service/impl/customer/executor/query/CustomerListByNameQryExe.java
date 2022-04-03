package jxf.pms.service.impl.customer.executor.query;

import com.alibaba.cola.dto.MultiResponse;
import java.util.ArrayList;
import java.util.List;

import jxf.pms.cmd.CustomerListByNameQry;
import jxf.pms.data.CustomerDTO;
import org.springframework.stereotype.Component;


@Component
public class CustomerListByNameQryExe{
    public MultiResponse<CustomerDTO> execute(CustomerListByNameQry cmd) {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName("Frank");
        customerDTOList.add(customerDTO);
        return MultiResponse.of(customerDTOList);
    }
}
