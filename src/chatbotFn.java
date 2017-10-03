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

public class chatbotFn {

	Chatterbot2 bot;
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
		System.out.println("WHOSE THERE?");
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
		System.out.println("LIST FO FUNCTIONS: 'KNOCK KNOCK', 'WHAT TIME IS IT', 'REMIND ME', 'CALCULATE', 'COUNTDOWN', 'PLAY GAME'");
		System.out.print(">");
	}
	
	public static void gameSelector() throws Exception {
		System.out.println("1: ANAGRAM PUZZLE");
		System.out.println("2: GUESS MY NUMBER");
		System.out.println("0: EXIT");
		System.out.print(">");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int nSelect = Integer.parseInt(in.readLine());
		if (nSelect == 1) {
			gameAnagram();
		} else if (nSelect==2) {
			gameNumGuess();
		}
		else {
			System.out.println("EXITED");
		}
	}
	
	public static void gameAnagram() throws Exception {
		String[] puzzlelist = {
			"dirty room",
			"meal for one",
			"television ads",
			"deductions",
			"parent"
		};
		String[] anslist = {
			"dormitory",
			"for me alone",
			"enslave idiots",
			"discounted",
			"entrap"
		};
		int ranN = ThreadLocalRandom.current().nextInt(/*min*/0, /*max*/4 + 1);
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
	
	// calculate() = calculation()+multiplication()division()+subtraction+addition()
	public static void calculate() {

        Scanner input = new Scanner(System.in);
        String[] a = new String[0];
        String eq = "";
        System.out.println("Example: a+b-c*d/e");
        System.out.print("Input equation: ");
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
