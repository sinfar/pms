package jxf.pms.customer;

import jxf.pms.dbo.CustomerDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper{

  CustomerDO getById(String customerId);
}
