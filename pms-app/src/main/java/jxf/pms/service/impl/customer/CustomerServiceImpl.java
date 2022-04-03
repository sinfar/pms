package jxf.pms.service.impl.customer;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.catchlog.CatchAndLog;
import jxf.pms.cmd.CustomerAddCmd;
import jxf.pms.cmd.CustomerListByNameQry;
import jxf.pms.data.CustomerDTO;
import jxf.pms.service.CustomerServiceI;
import org.springframework.stereotype.Service;

import jxf.pms.service.impl.customer.executor.CustomerAddCmdExe;
import jxf.pms.service.impl.customer.executor.query.CustomerListByNameQryExe;

import javax.annotation.Resource;


@Service
@CatchAndLog
public class CustomerServiceImpl implements CustomerServiceI {

    @Resource
    private CustomerAddCmdExe customerAddCmdExe;

    @Resource
    private CustomerListByNameQryExe customerListByNameQryExe;

    public Response addCustomer(CustomerAddCmd customerAddCmd) {
        return customerAddCmdExe.execute(customerAddCmd);
    }

    @Override
    public MultiResponse<CustomerDTO> listByName(CustomerListByNameQry customerListByNameQry) {
        return customerListByNameQryExe.execute(customerListByNameQry);
    }

}
