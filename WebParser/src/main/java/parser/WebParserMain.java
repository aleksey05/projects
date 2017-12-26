package parser;

public class WebParserMain {

	public static void main(String[] args) {
		if (args[0] != null) {
			System.out.println(args[0] + " ----------------------------------------------------");
			new WebParserRunner(args[0]).parse();
		} else {
			System.out.println("please provide keyword");
			return;
		}
	}

}
