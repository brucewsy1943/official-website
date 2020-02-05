package com.official.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.official.bean.Employee;
import com.official.bean.EmployeeExample;
import com.official.bean.EmployeeExample.Criteria;
import com.official.dao.EmployeeMapper;
import com.official.service.EmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Override
	public Employee findEmployeeByWorkNumber(String workNumber) {
		// TODO Auto-generated method stub
		EmployeeExample employeeExample = new EmployeeExample();
		Criteria c = employeeExample.createCriteria();
		c.andWorknumberEqualTo(workNumber);
		List<Employee> list = employeeMapper.selectByExample(employeeExample);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * 判断该员工是否存在
	 * @param workNumber
	 * @return
	 */
	@Override
	public Employee selectByWorkNumber(String workNumber,String phone) {
		Employee count = employeeMapper.selectByWorkNumber(workNumber,phone);
		return count;
	}
}
