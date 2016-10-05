package dao;

import java.util.List;

import javax.persistence.EntityManager;

import bookslibrary.SystemProps;
import entity.Book;
//Класс для работы с БД
public class BooksOptions {
	EntityManager em = new SystemProps().entityManager();
	
	//добавляет книгу
	public String addBook(Book book) {
		try{
		em.getTransaction().begin();
		em.persist(book);
		em.getTransaction().commit();
		} catch (Exception e){
			System.out.println(e);
			em.getTransaction().rollback();
			return e.getMessage();
		}
		
		return book.toString()+" was added";
	}
	
	//возвращает массив всех книг 
	public List<Book> allBooks(){
		List<Book> books = (List<Book>) em.createNativeQuery("select * from Books", Book.class).getResultList();
		return books;
	}
	
	//возвращает массив книг по имени книги
	public List<Book> getBookByName(String name){
		List<Book> books = (List<Book>) em.createNativeQuery("select * from Books where name like \""+name+"\"", Book.class).getResultList();
		return books;	
	}
	
	//удаляет книгу
	public String removeBook(Book book){
		try{
		em.getTransaction().begin();
		em.remove(book);
		em.getTransaction().commit();
		return book.toString();
		} catch (Exception e){
			System.out.println(e);
			em.getTransaction().rollback();
			return e.getMessage();
			
		}
		
	}
	

}
