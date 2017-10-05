//
// Program Name: chatterbot2
// Description: this is an improved version of the previous chatterbot program "chatterbot1"
// this one will try a littlebit more to understand what the user is trying to say
//
// Author: Gonzales Cenelia
//

import java.io.*;
import java.util.*;

public class Chatterbot2 {
	
	static String[][] KnowledgeBase = {
		{"WHAT IS YOUR NAME", 
		 "MY NAME IS CHATTERBOT2."
		},
		
		{"HI", 
		 "HI THERE!",
		},
		
		{"HOW ARE YOU", 
		 "I'M DOING FINE!"
		},
		  
		{"WHO ARE YOU", 
		 "I'M AN A.I PROGRAM."
		},

		{"ARE YOU INTELLIGENT", 
		 "YES,OFCORSE."
		},
		   
		{"ARE YOU REAL", 
		 "DOES THAT QUESTION REALLY MATERS TO YOU?"
		},
		
		{"HOW'S THE WEATHER",
		 "MY GUESS IS 50% CHANCE OF RAIN."
		},
		
		{"WHAT IS THE MEANING OF LIFE",
		 "42."
		},
		
		{"THIS SENTENCE IS FALSE",
		 "THIS CHATTERBOT2 IS TOO DUMB TO ANALYZE THE PARADOX."
		},
	};
	
	static String findMatch(String str) {
		String result = "";
		for(int i = 0; i < KnowledgeBase.length; ++i) {
			if(KnowledgeBase[i][0].equalsIgnoreCase(str)) {
				result = KnowledgeBase[i][1];
				break;
			}
		}
		return result;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		chatbotFn.greeting();
		while(true) {
			System.out.print(">");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String sInput = in.readLine();
			String sResponse = findMatch(sInput);
			if(sInput.equalsIgnoreCase("BYE")){
				System.out.println("IT WAS NICE TALKING TO YOU USER, SEE YOU NEXT TIME!");
				break;
			}  else if(sInput.equalsIgnoreCase("knock knock")) {
				chatbotFn.knockknock();
			}  else if(sInput.toLowerCase().contains("remind me")) {
				chatbotFn.reminder();
			}  else if(sInput.toLowerCase().contains("what") && sInput.toLowerCase().contains("time")) {
				chatbotFn.whatTime();
			}  else if(sInput.toLowerCase().contains("what") && sInput.toLowerCase().contains("day")) {
				chatbotFn.whatDay();
			}  else if(sInput.equalsIgnoreCase("help")) {
				chatbotFn.help();
			}  else if(sInput.contains("+") || sInput.contains("*") || sInput.contains("-") || sInput.contains("/")) {
				System.out.println("USE 'CALCULATE'");
			}  else if(sInput.equalsIgnoreCase("calculate")) {
				chatbotFn.calculate();
			}  else if(sInput.equalsIgnoreCase("countdown")) {
				chatbotFn.countdown();
			}  else if(sInput.toLowerCase().contains("play") && sInput.toLowerCase().contains("game")) {
				chatbotFn.gameSelector();
			}  else if(sInput.equalsIgnoreCase("tell me a joke")) {
				chatbotFn.joke();
			} else if(sResponse.length() == 0) {
				System.out.println("I'M NOT SURE IF I UNDERSTAND WHAT YOU  ARE TALKING ABOUT.");
			} else {
				System.out.println(sResponse);
			}
		}
	}	
}