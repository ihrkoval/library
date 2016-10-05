package bookslibrary;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

//класс с методом возвращающим EntityManager 
public class SystemProps{
	
	    public EntityManager entityManager() {
		 
		 	Map<String,String> props = new HashMap<String,String>();
			props.put("javax.persistence.jdbc.url","jdbc:mysql://192.168.20.38:3306/users_auth");
			props.put("javax.persistence.jdbc.user","root");
			props.put("javax.persistence.jdbc.password","T5r+VuKxg4");
			props.put("javax.persistence.jdbc.driver","com.mysql.jdbc.Driver");
			props.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
			props.put("hibernate.hbm2ddl.auto","update");
			props.put("hibernate.show_sql","true");

		    LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		    emf.setPersistenceProviderClass(org.hibernate.jpa.HibernatePersistenceProvider.class); 
		    emf.setPackagesToScan("entity"); 
		    emf.setPersistenceUnitName("library");
		    emf.setJpaPropertyMap(props);
		    emf.afterPropertiesSet();
		 
		 
	     
		   
		    if (emf.getObject() == null){
		    	System.out.println("EMF IS NULL!!!");
		    }
	        return emf.getObject().createEntityManager();
	}
}
