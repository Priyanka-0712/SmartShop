package com.customexception;

public class MyException extends Exception {
	
	private static final long serialVersionUID = 1L; //for suppress warning only
	
//============================ Constructors ============================================
	
	public MyException(String message)
	{
		super(message);
	}
	
//================================= End =========================================================================
}
