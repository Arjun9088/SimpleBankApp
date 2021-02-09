package com.example.bank;

import java.io.IOException;

import com.example.service.Execution;

public class MainClass 
{
	public static void main(String[] args) throws IOException {
		Execution exec = new Execution();
		exec.executeProgram();
	}

}
