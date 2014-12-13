package com.zml.oa.ProcessTask.ServiceTask;

import java.math.BigDecimal;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import com.zml.oa.entity.Salary;
import com.zml.oa.entity.SalaryAdjust;
import com.zml.oa.service.ISalaryAdjustService;
import com.zml.oa.service.ISalaryService;

/**
 * 记录薪资调整
 * @author ZML
 *
 */
public class ContentSalary implements JavaDelegate {

	@Autowired
    protected RuntimeService runtimeService;
	 
	@Autowired
	private ISalaryService salaryService;
	
	@Autowired
	private ISalaryAdjustService saService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Integer businessKey = (Integer) execution.getVariable("businessKey");
		System.out.println("%%%%%%%%%%%%%%businessKey: "+ businessKey);
		SalaryAdjust salaryAdjust = this.saService.findById(businessKey);
		
		Salary salary = this.salaryService.findByUserId(salaryAdjust.getUserId().toString());
		BigDecimal newMoney = salaryAdjust.getAdjustMoney();
		salary.setBaseMoney(newMoney);
		this.salaryService.update(salary);
		
	}

}