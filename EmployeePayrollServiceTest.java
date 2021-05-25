package com.bridgelabz;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.bridgelabz.EmployeePayrollService.IOService;

public class EmployeePayrollServiceTest {

	@Test
	public void given3EmployeesWhenWrittenToFileShouldMatchEmployeeEntries() {
		EmployeePayrollData[] arrayOfEmps = {
				new EmployeePayrollData(1, "Bill", 1000000.00),
				new EmployeePayrollData(2, "Mark", 2000000.00),
				new EmployeePayrollData(3, "Charlie", 3000000.00)
		};
		EmployeePayrollService employeePayrollService;
		employeePayrollService = new EmployeePayrollService(Arrays.asList(arrayOfEmps));
		IOService FILE_IO = null;
		employeePayrollService.writeEmployeePayrollData(FILE_IO);
		employeePayrollService.printData(FILE_IO);
		long entries = employeePayrollService.countEntries(FILE_IO);
		Assert.assertEquals(3, entries);
	}

 	@Test
	public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(DB_IO);
		Assert.assertEquals(3, employeePayrollData.size());
	}
 	
 	@Test
	public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDB() {
 		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
 		IOService DB_IO = null;
		List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(DB_IO);
 		employeePayrollService.updateEmployeeSalary("Terisa", 3000000.00);
 		boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Terisa");
 		Assert.assertTrue(result);
 	}
 	
 	@Test
	public void givenDateRange_WhenRetrieved_ShouldMatchEmployeeCount() {
 		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
 		IOService DB_IO;
		employeePayrollService.readEmployeePayrollData(DB_IO);
 		LocalDate startDate = LocalDate.of(2018, 01, 01);
 		LocalDate endDate = LocalDate.now();
 		List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollForDateRange(DB_IO, startDate, endDate);
 	    Assert.assertEquals(3, employeePayrollData.size())
 	}
 	
 	@Test
	public void givenPayrollData_WhenAverageSalaryRetrivedByGender_ShouldReturnPropertyValue() {
 		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
 		Object DB_IO;
		employeePayrollService.readEmployeePayrollData(DB_IO);
 		Map<String, Double> averageSalaryByGender = employeePayrollService.readAverageSalaryByGender(DB_IO);
 		Assert.assertTrue(averageSalaryByGender.get("M").equals(2000000.00) &&
 				averageSalaryByGender.get("F").equals(3000000.00));
 	}

 	@Test
	public void givenNewEmployee_WhenAdded_ShouldSynchWithDB() {
 		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
 		Object DB_IO;
		employeePayrollService.readEmployeePayrollData(DB_IO);
		employeePayrollService.addEmployeeToPayroll("Mark", 5000000.00, LocalDate.now(), "M");
		boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Mark");
		Assert.assertTrue(result);
 	}
 	
}
