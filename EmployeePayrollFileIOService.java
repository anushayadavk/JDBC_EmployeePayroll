package com.bridgelabz;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class EmployeePayrollFileIOService {

	public void writeData(List<EmployeePayrollData> employeePayrollList) {
		// TODO Auto-generated method stub	
	}

	public void printDate() {
		// TODO Auto-generated method stub
		try {
            Files.lines(new File("payroll_service")).toPath()).forEach(System.out ::println);
        }catch (IOException e) {
            e.printStackTrace();
        }
		
	}

	public long countEntries() {
		// TODO Auto-generated method stub
		long entries = 0;
        try {
            entries = Files.lines(new File("payroll_service").toPath()).count();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
	}

}
