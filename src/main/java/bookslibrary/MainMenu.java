package bookslibrary;

import java.util.List;
import java.util.Scanner;

import dao.BooksOptions;
import entity.Book;

//класс который просматривает веденные в консоль данные и выполняет дествия в зависимости от команды
public class MainMenu {

	BooksOptions booksOpt = new BooksOptions();
	
	//метод в который передается введенная пользователем строка
	public void listen(String line){
		//команда - все что до первого пробела
		String comand = line.substring(0, line.indexOf(" "));
		//книга - все что от первого пробела и до конца строки
		String book = line.substring(line.indexOf(" ")+1, line.length());
 
		switch(comand) {
			case "add": 
				add(book);
				break;
	
			case "remove": 
				remove(book);
				break;
			
			case "edit": 
				edit(book);
				break;
			
			case "help": 
				help();
				break;
			
			case "all": 
				List<Book> booksList = booksOpt.allBooks();
				for (Book b : booksList){
					System.out.println(b.toString());
				}
				break;
			
			default: 
				System.out.println("enter 'help me' to show comands");
				break;
		}
	
	}

	
	//метод для добавления книги в БД
	private void add(String bookString){
	   Book book = stringToObject(bookString);
	   String added = booksOpt.addBook(book);
	   System.out.println(added);
	}

	//подсказки по командам
	public void help(){
		System.out.println("enter 'add author \"book name\"' to add a book");
		System.out.println("enter 'all books' to show all books");
		System.out.println("enter 'remove book_name' to remove book");
		System.out.println("enter 'edit book_name' to edit book name");
	}

	
	//возвращает новый объект "книга" из строки прочитанной из консоли вида: author "book name"
	private Book stringToObject(String bString){
		//Автор - все что до кавычек, название - в кавычках
		String author = bString.substring(0, bString.indexOf("\"")-1);
	    String bookName = bString.substring(bString.indexOf("\"")+1, bString.lastIndexOf("\""));
	return new Book(bookName, author);
	}
	
	
	//удаляет книгу по ее названию
	private void remove(String bookName){

		List<Book> booksList = booksOpt.getBookByName(bookName);
		
		//если книг с таким названием несколько - даем пользователю выбрать что удалить
		if (booksList.size() > 1){
			System.out.println("we have few books with such name please choose one by typing a number of book:");
			for(int i = 0; i < booksList.size(); i++){
				System.out.println(i+1 + ". " + booksList.get(i));
			}
			Scanner scan = new Scanner(System.in);
			int num = Integer.valueOf(scan.nextLine())-1;
			String removed =  booksOpt.removeBook(booksList.get(num));
			System.out.println("book " + removed + " was removed" );
			
		}
		
		else {
			String removed =  booksOpt.removeBook(booksList.get(0));
			System.out.println("book " + removed + " was removed" );
		}
		
	}

	//редактирует название книги
	private void edit(String bookName){
		//получаем первую книгу из возвращаемого массива
		Book book = booksOpt.getBookByName(bookName).get(0);
		System.out.println("enter a new book name: ");
		Scanner sc = new Scanner(System.in);
		String newName = sc.nextLine();
		book.setName(newName);
		booksOpt.addBook(book);
	}
	
	
}
