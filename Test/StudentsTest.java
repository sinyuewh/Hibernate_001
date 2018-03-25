import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.immo.entity.Students;

//Test Class
public class StudentsTest {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction trans;
	
	
	@Before
	public void init()
	{
		//创建配置对象
		Configuration config=new Configuration().configure();
		
		//创建服务注册对象
		ServiceRegistry seviceRegistry=new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		
		//创建会话工厂对象
		this.sessionFactory=config.buildSessionFactory(seviceRegistry);

		//创建会话对象
		this.session=this.sessionFactory.openSession();

		//开启事务
		this.trans=this.session.beginTransaction();
	}
	
	
	@After
	public void destroy()
	{
		this.trans.commit();				//提交事务
		this.session.close();				//关闭会话
		this.sessionFactory.close();		//关闭会话工厂
	}
	
	
	@Test
	public void testSaveStudents()
	{
		Students s1=new Students(1, "jsj", "男", new Date(),"武汉1");
		this.session.save(s1);
	}
	
	
}
