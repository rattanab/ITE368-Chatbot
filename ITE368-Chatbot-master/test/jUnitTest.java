import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class jUnitTest {

	Chatterbot2 test;
	chatbotFn test2;
	String in;
	String out;
	
	public jUnitTest(String in, String out) {
		this.in = in;
		this.out = out;
	}
	
	@Parameters
	public static Collection<Object[]> testinput(){
		Object kb[][] = {
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
		return Arrays.asList(kb);
	}
	@Test
	public void test() {
		// testing for input and output of the KnowledgeBase in chatterbot2.
		assertEquals(test.findMatch(in),out);
	}

}
