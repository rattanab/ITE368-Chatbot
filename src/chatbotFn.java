import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

import org.omg.Messaging.SyncScopeHelper;

public class chatbotFn {

	Chatterbot2 bot;
	static final DateFormat day = new SimpleDateFormat("MM/dd/yyyy");
	static final DateFormat time = new SimpleDateFormat("HH:mm:ss"); //yyyy/MM/dd HH:mm:ss = 2016/11/16 12:08:43
	static final DateFormat greeting = new SimpleDateFormat("HH");
	static int interval;
	static Timer timer = new Timer();
	
	public static void greeting() {
		Date date = new Date();
		if (Integer.parseInt(greeting.format(date)) < 12) {
			System.out.println("GOOD MORNING");
		} else if (Integer.parseInt(greeting.format(date)) < 18) {
			System.out.println("GOOD AFTERNOON");
		} else {
			System.out.println("GOOD EVENING");
		}
	}
	
	public static void whatDay() {
		Date date = new Date();
		System.out.println("IT IS "+day.format(date));
	}
	
	// reminder()+showReminder()
	public static void reminder() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MESSAGE TO DISPLAY");
		System.out.print(">");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String rInput = in.readLine();
		System.out.println("TIME TO DISPLAY REMINDER(SEC)");
		System.out.print(">");
		String numInput = in.readLine();
		showReminder(Integer.parseInt(numInput),rInput);
	}
	
	public static void showReminder(int sec, final String mes) {
		timer.schedule(new TimerTask() {
		  @Override
		  public void run() {
		    System.out.println("REMINDER:"+mes);
		    System.out.print(">");
		  }
		}, sec*1000);
	}
	
	public static void countdown() throws Exception {
		System.out.println("WHERE SHOULD THE COUNTDOWN START?");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String numInput = in.readLine();
		interval = Integer.parseInt(numInput);
		timer.scheduleAtFixedRate(new TimerTask() {
	        public void run() {
	            System.out.println(setInterval());
	        }
	    }, 1000,1000);
	}
	
	private static final int setInterval() {
	    if (interval == 1)
	        timer.cancel();
	    return --interval;
	}
	
	public static void knockknock() throws Exception {
		System.out.println("WHO'S THERE?");
		System.out.print(">");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String sInput = in.readLine();
		System.out.println(sInput+" WHO?");
		System.out.print(">");
		String sInput1 = in.readLine();
		System.out.println("FUNNY...");
	}

	public static void whatTime() {
		Date date = new Date();
		System.out.println("IT IS "+time.format(date));
	}
	
	public static void help() {
		System.out.println("LIST OF FUNCTIONS: 'KNOCK KNOCK', 'WHAT TIME', 'WHAT DAY', 'REMIND ME', 'CALCULATE', 'COUNTDOWN', 'PLAY GAME', 'TELL ME A JOKE'");
		System.out.print(">");
	}
	
	public static void joke() {
		String[] jokelist = {
				"ARRAY STARTS AT 1.",
				"HOW MANY PROGRAMMER DOES IT TAKE TO CHANGE A LIGHT BLUB? NONE, ITS A HARDWARE ISSUE.",
				"YOUR MOTHER IS SO FAT, THE RECUSIVE FUNCTION CALCULATING HER MASS CAUSE A STACK OVERFLOW.",
				" ; IS THE HIDE AND SEEK CHAMPION.",
				"WHEN YOU DON'T HAVE TIME TO DEBUG, SELL IT AS A FEATURE.",
				"HAS YOUR PROGRAM SHIPPED WITHOUT A BUG? NO WORRIES, THERE IS ALWAYS DLC ;)"
						};
		int ranN = ThreadLocalRandom.current().nextInt(/*min*/0, /*max*/4 + 1);
		System.out.println(jokelist[ranN]);
	}
	
	public static void gameSelector() throws Exception {
		System.out.println("1: ANAGRAM PUZZLE");
		System.out.println("2: GUESS MY NUMBER");
		System.out.println("3: 'WHAT DO YOU' GAME");
		System.out.println("0: EXIT");
		System.out.print(">");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int nSelect = Integer.parseInt(in.readLine());
		if (nSelect == 1) {
			gameAnagram();
		} else if (nSelect==2) {
			gameNumGuess();
		} else if (nSelect==3){
			gameWhatDoYouGet();
		}
		else {
			System.out.println("EXITED");
		}
	}
	
	public static void gameAnagram() throws Exception {
		String[] puzzlelist = {
			"DIRTY ROOM",
			"MEAL FOR ONE",
			"TELEVISION ADS",
			"DEDUCTIONS",
			"PARENT",
			"ELVIS",
			"ELEVEN PLUS TWO"
		};
		String[] anslist = {
			"DORMITORY",
			"FOR ME ALONE",
			"ENSLAVE IDIOTS",
			"DISCOUNTED",
			"ENTRAP",
			"LIVES",
			"TWELVE PLUS ONE"
		};
		int ranN = ThreadLocalRandom.current().nextInt(/*min*/0, /*max*/6 + 1);
		boolean quit=false;
		System.out.println("");
		System.out.println("THE WORD IS: "+puzzlelist[ranN]);
		while(quit!=true) {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String ansInput = in.readLine();
			if (ansInput.equalsIgnoreCase(anslist[ranN])) {
				System.out.println("CORRECT");
				quit=true;
			}
			else {
				System.out.println("TRY AGAIN");
			}
		}
	}
	
	public static void gameNumGuess() throws Exception {
		System.out.println("WHAT IS MAX NUMBER?");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int nMax = Integer.parseInt(in.readLine());
		int ranN = ThreadLocalRandom.current().nextInt(/*min*/1, /*max*/nMax + 1);
		boolean quit = false;
		System.out.println("0 TO QUIT");
		System.out.println("I GOT A NUMBER, GUESS");
		while (quit!=true) {
			int nGuess = Integer.parseInt(in.readLine());
			if (nGuess == ranN) {
				System.out.println("CONGRADURATION");
				quit=true;
			} else if  (nGuess == 0) {
				System.out.println("EXITED");
			} else if (nGuess < ranN) {
				System.out.println("HIGHER");
			} else if (nGuess > ranN) {
				System.out.println("LOWER");
			}
		}
	}
	public static void gameWhatDoYouGet() throws Exception {
		String [] TheWhat = {
			" GIVE A SQUID TO KEEP HIM FROM BEING QUIET?",
			" GET IF YOU CALL YOUR UFC TRAINED DENTIST OUT OF HOURS?",
			" CALL A COW WHEN IT IS DRESSED UP FOR DINNER?",
			" CALL A 2 x 4 PREGNANT PLANK OF WOOD?",
			" GIVE A ROMAN SOLDIER WHO HOLDS UP TWO FINGERS AND ASKS FOR BEER?"
		};
		String [] TheLaugh = {
			"TEN-TICKLES	^,^",
			"TOOTH HURTY	^,^",
			"UDDERLY RIDICULOUS		^,^",
			"MOTHER BOARD	^,^",
			"A PUNCH IN THE FACE! MY BEER!  *.*"		
		};
		int ranN = ThreadLocalRandom.current().nextInt(/*min*/0, /*max*/4 + 1);
		boolean quit=false;
		System.out.println("");
		System.out.println("What do you"+ TheWhat[ranN]);
		while(quit!=true) {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String ansInput = in.readLine();
			if (ansInput.equalsIgnoreCase("What")) {
				System.out.println(TheLaugh[ranN]);
				quit=true;
			}
			else {
				System.out.println("SAY 'WHAT'! DO YOU NOT GET JOKES?");
				
			}
		}
	}
	
	// calculate() = calculation()+multiplication()division()+subtraction+addition()
	public static void calculate() {

        Scanner input = new Scanner(System.in);
        String[] a = new String[0];
        String eq = "";
        System.out.println("EXAMPLE: a+b-c*d/e");
        System.out.print("INPUT EQUATION: ");
        eq = input.next();
        a = eq.split("(?<=\\+)|(?=\\+)|(?<=-)|(?=-)|(?<=\\*)|(?=\\*)|(?<=/)|(?=/)");
        String[] b = new String[a.length];
        System.arraycopy(a, 0, b, 0, (b.length));
        double inputresult = Double.parseDouble(a[a.length - 1]);
        calculation(b);
    }
	
    public static void calculation(String[] b){
    	b=division(b,0);	
    	b=multiplication(b,0);
    	b=subtraction(b,0);
    	b=addition(b,0);
    	System.out.println("RESULT: "+b[0]);
    }
    
    public static String[] multiplication(String[] eq, int i){
        double result=0;
    	if (eq[i].equals("*")) {
    		String[] tempeq = new String[eq.length - 2];
            result = Double.parseDouble(eq[i - 1]) * Double.parseDouble(eq[i + 1]);
            eq[i - 1] = Double.toString(result);
            System.arraycopy(eq, 0, tempeq, 0, i);
            System.arraycopy(eq, i + 2, tempeq, i, (eq.length) - (i + 2));
            eq = tempeq;
            result = 0; //reset
            i--;	
            multiplication(eq,i);
        } else if (i<eq.length-1){
        	i++;
        	multiplication(eq,i);
        }
        return eq;
    }
    public static String[] division(String[] eq, int i){
    	double result=0;
    	if (eq[i].equals("/")) {
    		String[] tempeq = new String[eq.length - 2];
            result = Double.parseDouble(eq[i - 1]) / Double.parseDouble(eq[i + 1]);
            eq[i - 1] = Double.toString(result);
            System.arraycopy(eq, 0, tempeq, 0, i);
            System.arraycopy(eq, i + 2, tempeq, i, (eq.length) - (i + 2));
            eq = tempeq;
            result = 0; //reset
            i--;
            division(eq,i);
        } else if (i<eq.length-1){
        	i++;
        	division(eq,i);
        }
        return eq;
    }
    public static String[] subtraction(String[] eq, int i){
    	double result=0;
    	if (eq[i].equals("-")) {
    		String[] tempeq = new String[eq.length - 2];
            result = Double.parseDouble(eq[i - 1]) - Double.parseDouble(eq[i + 1]);
            eq[i - 1] = Double.toString(result);
            System.arraycopy(eq, 0, tempeq, 0, i);
            System.arraycopy(eq, i + 2, tempeq, i, (eq.length) - (i + 2));
            eq = tempeq;
            result = 0; //reset
            i--;
            subtraction(eq,i);
        } else if (i<eq.length-1){
        	i++;
        	subtraction(eq,i);
        }
        return eq;
    }
    public static String[] addition(String[] eq, int i){
    	double result=0;
    	if (eq[i].equals("+")) {
    		String[] tempeq = new String[eq.length - 2];
            result = Double.parseDouble(eq[i - 1]) + Double.parseDouble(eq[i + 1]);
            eq[i - 1] = Double.toString(result);
            System.arraycopy(eq, 0, tempeq, 0, i);
            System.arraycopy(eq, i + 2, tempeq, i, (eq.length) - (i + 2));
            eq = tempeq;
            result = 0; //reset
            i--;
            addition(eq,i);
        } else if (i<eq.length-1){
        	i++;
        	addition(eq,i);
        }
        return eq;
    }
}