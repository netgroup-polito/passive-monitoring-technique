package it.polito.tesiclustering.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import it.polito.tesiclustering.infrastructure.HibernateUtil;
import it.polito.tesiclustering.model.User;

public class UserDao {

	private Session createSession() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return session;
	}

	private void closeSession(Session session) {

		if (session != null)
			session.close();

	}

	private void rollbackTransaction(Transaction transaction) {

		if (transaction != null) {

			transaction.rollback();

		}

	}

	public User createUser(User user) throws Exception {

		Session session = createSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (Exception e) {

			rollbackTransaction(tx);
			throw e;
		} finally {

			closeSession(session);

		}

		return user;
	}

	public User getUser(Integer id) throws Exception {

		Session session = createSession();
		User user;

		try {

			user = (User) session.get(User.class, id);

		} catch (Exception e) {

			rollbackTransaction(session.getTransaction());
			throw e;

		} finally {

			closeSession(session);

		}

		return user;

	}

	public List<User> getAllUser() throws Exception {

		Session session = createSession();
		Transaction tx;
		List<User> users;

		try {

			users = (List<User>) session.createCriteria(User.class).list();

		} catch (Exception e) {

			rollbackTransaction(session.getTransaction());
			throw e;

		} finally {

			closeSession(session);

		}

		return users;
	}

	public User getUserByUsername(String username) throws Exception {
		Session session = createSession();
		User user;

		if (username == null)
			throw new Exception("Username must not be null.");

		try {

			user = (User) session.createCriteria(User.class).add(Restrictions.eq("username", username)).uniqueResult();

		} catch (Exception e) {

			rollbackTransaction(session.getTransaction());
			throw e;

		} finally {

			closeSession(session);

		}

		return user;
	}

	public void deleteUser(Integer id) throws Exception {
		// TODO Auto-generated method stub

		Session session = createSession();
		Transaction tx = null;
		User user;
		try {
			tx = session.beginTransaction();
			user = (User) session.load(User.class, id);
			session.delete(user);
			tx.commit();
		} catch (Exception e) {

			rollbackTransaction(tx);
			throw e;

		} finally {

			closeSession(session);

		}

		return;
	}

}
