package com.jdbc.statements;

public class TestCallableStatement {
	public static void main(String[] args) {
		
	}
}
//create or replace procedure p_jdbc_student(myid in number, myname in varchar2)
//as begin
//	insert into T_JDBC_BASIC_STUDENT(stuid, stuname) values (myid, myname);
//	commit;
//end p_jdbc_student;