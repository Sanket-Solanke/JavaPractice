package com.sunbeam;
public class CustomException extends Exception{
private String message; //
private String value; //  value

public CustomException() {
super("Invalid Input");
}

public CustomException(String message) {
super(message);
this.message=message;
}
   
public String getMessage() {
return message;
}
public void setMessage(String msg) {
this.value = msg;
}

       
}

