package com.example.demo.entity;

*@author Sujith

*@created Date24-Apr-2023
*
*/

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDate;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Employee {

	private long employeeID;

	private String employeeName;

	private LocalDate dateOfBirth;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final String ALGORITHM = "AES";
	private static final String KEY = "employeeencrp345";

	private static String decrypt(String encryptedText, String key) {
		try {
			Key aesKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
			return new String(decrypted, StandardCharsets.UTF_8);
		} catch (Exception e) {
			throw new RuntimeException("Error decrypting text", e);
		}
	}

	public Employee(long employeeId, String employeeName, String dateOfBirth) {
		super();
		this.employeeID = employeeId;
		this.employeeName = employeeName;
		this.dateOfBirth = LocalDate.parse(decrypt(dateOfBirth, KEY));
	}

	public long getEmployeeId() {
		return employeeID;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeID = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = LocalDate.parse(decrypt(dateOfBirth, KEY));
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeID + ", employeeName=" + employeeName + ", dateOfBirth=" + dateOfBirth
				+ "]";
	}

}
