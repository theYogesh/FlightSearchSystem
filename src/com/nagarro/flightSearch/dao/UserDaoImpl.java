package com.nagarro.flightSearch.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.nagarro.flightSearch.model.User;

public class UserDaoImpl implements UserDao
{
	private static HibernateTemplate template;
	
	public UserDaoImpl()
	{
		super();
	}

	public HibernateTemplate getTemplate()
	{
		return template;
	}

	public void setTemplate(HibernateTemplate template)
	{
		this.template = template;
	}
	
	//@Transactional(readOnly = false)
	public boolean saveUser(User user)
	{	
//		template.setCheckWriteOperations(false);
//		template.getSessionFactory().getCurrentSession().setFlushMode(Flus‌​hMode.AUTO);
		User tempUser = getByUsername(user.getUsername());
		Boolean res;
		if(tempUser!=null)
		{
			res=false;
		}
		else
		{
			Session session = template.getSessionFactory().openSession();
			Transaction tx = null;
			try
			{
				tx = session.beginTransaction();
				session.save(user);
				tx.commit();
			}
			catch (HibernateException e)
			{
				if ( tx != null )
					tx.rollback();
				System.out.println("Error: " + e.getMessage());
			}
			catch (Exception e)
			{
				System.out.println("Error: " + e.getMessage());
			}
			finally
			{
				session.close();
			}
			
			res=true;
		}
		return res;
	}

	public void updateUser(User user)
	{
		template.update(user);
	}
 
	public boolean deleteUser(User user)
	{	
		User tempUser = getByUsername(user.getUsername());
		Boolean res;
		if(tempUser!=null)
		{
			template.delete(tempUser);
			res=true;
		}
		else
		{
			res=false;
		}
		return res;
	}
 
	public User getById(int id)
	{
		return ((User) template.get(User.class, id));
	}

	public User getByUsername(String username)
	{
		System.out.println("%% username="+username);
		List resList = template.find("FROM User where username = ? ",username);
		if(!resList.isEmpty())
		{
			return ((User)resList.get(0));
		}
		return null;
	}
	public List<User> getUsers()
	{
		List<User> list = new ArrayList<User>();
		list = template.loadAll(User.class);
		return list;
	}
	
	public boolean isExists(User user)
	{
		String query = "FROM User where username = ? AND password = ? ";
		Object[] params  = {user.getUsername() , user.getPassword()};
		
		if(!template.find(query, params).isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}