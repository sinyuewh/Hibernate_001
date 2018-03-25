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
		//�������ö���
		Configuration config=new Configuration().configure();
		
		//��������ע�����
		ServiceRegistry seviceRegistry=new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		
		//�����Ự��������
		this.sessionFactory=config.buildSessionFactory(seviceRegistry);

		//�����Ự����
		this.session=this.sessionFactory.openSession();

		//��������
		this.trans=this.session.beginTransaction();
	}
	
	
	@After
	public void destroy()
	{
		this.trans.commit();				//�ύ����
		this.session.close();				//�رջỰ
		this.sessionFactory.close();		//�رջỰ����
	}
	
	
	@Test
	public void testSaveStudents()
	{
		Students s1=new Students(1, "jsj", "��", new Date(),"�人1");
		this.session.save(s1);
	}
	
	
}
