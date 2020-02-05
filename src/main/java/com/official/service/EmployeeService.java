package com.official.service;

import com.official.bean.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {


	public Employee findEmployeeByWorkNumber(String workNumber);

	/**
	 * 根据工号判断该员工是否存在
	 * @param workNumber
	 * @return
	 */
	Employee selectByWorkNumber(String workNumber,String phone);

}
