package bookslibrary;


import java.util.Scanner;


public class Main {
	
	 public static void main(String []args){
		
		Scanner scan = new Scanner(System.in);
		String line = "";
		
		while(!line.equals("exit")){
			line = scan.nextLine();
			try{ //на случай если пользователь введет одно слово и не получится спарсить команду/книгу
			new MainMenu().listen(line);
			} catch (StringIndexOutOfBoundsException e){ 
				System.out.println("enter 'help me' to show comands");
			}
		}
		scan.close();
		
	 }
}
