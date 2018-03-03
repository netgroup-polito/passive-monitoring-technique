package it.polito.tesiclustering.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import it.polito.tesiclustering.infrastructure.HibernateUtil;
import it.polito.tesiclustering.model.Execution;
import it.polito.tesiclustering.model.Graph;

public class ExecutionDao {

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

	public Execution createExecution(Execution execution) throws Exception {

		Session session = createSession();
		Transaction tx = null;

		Execution savedExecution = new Execution();
		Integer id;

		try {

			tx = session.beginTransaction();

			id = (Integer) session.save(execution);
			System.out.println("ID :" + id);
			// get saved execution
			savedExecution = (Execution) session.get(Execution.class, id);
			System.out.println("savedExecution :" + savedExecution);

			tx.commit();
		} catch (Exception e) {

			rollbackTransaction(tx);
			throw e;
		} finally {

			closeSession(session);
		}

		return savedExecution;
	}

	public Execution getExecution(Integer id) throws Exception {

		Session session = createSession();
		Transaction tx = null;
		Execution execution;

		try {

			tx = session.beginTransaction();
			execution = (Execution) session.get(Execution.class, id);
			tx.commit();

		} catch (Exception e) {

			rollbackTransaction(tx);
			throw e;

		} finally {

			closeSession(session);
		}

		return execution;

	}

	@Transactional
	public List<Execution> getAllExecution() throws Exception {

		Session session = createSession();
		Transaction tx = null;
		List<Execution> executions;

		try {

			tx = session.beginTransaction();
			executions = (List<Execution>) session.createCriteria(Execution.class).list();
			tx.commit();

		} catch (Exception e) {

			rollbackTransaction(tx);
			throw e;

		} finally {

			closeSession(session);
		}

		return executions;
	}

	@Transactional
	public List<Execution> getAllExecutionByTopology(String topology) throws Exception {

		Session session = createSession();
		Transaction tx = null;
		List<Execution> executions;

		if (topology == null)
			throw new Exception("Topology must not be null.");

		try {

			tx = session.beginTransaction();
			executions = (List<Execution>) session.createCriteria(Execution.class)
					.add(Restrictions.eq("topology.name", topology)).list();
			tx.commit();

		} catch (Exception e) {

			rollbackTransaction(tx);
			throw e;

		} finally {

			closeSession(session);
		}

		return executions;
	}

	public Execution getExecutionById(Integer id) throws Exception {
		Session session = createSession();
		Execution execution;

		if (id == null)
			throw new Exception("Id must not be null.");

		try {

			execution = (Execution) session.createCriteria(Execution.class).add(Restrictions.eq("id", id))
					.uniqueResult();

		} catch (Exception e) {

			rollbackTransaction(session.getTransaction());
			throw e;

		} finally {

			closeSession(session);
		}

		return execution;
	}

	public void deleteExecutionByTopologyAndId(String topology, Integer id) throws Exception {
		// TODO Auto-generated method stub
		Session session = createSession();
		Transaction tx = null;
		Execution execution;

		if (topology == null)
			throw new Exception("Topology must not be null.");

		try {
			tx = session.beginTransaction();
			execution = (Execution) session.createCriteria(Execution.class).add(Restrictions.eq("id", id))
					.add(Restrictions.eq("topology.name", topology)).uniqueResult();
			session.delete(execution);
			tx.commit();
		} catch (Exception e) {

			rollbackTransaction(tx);
			throw e;

		} finally {

			closeSession(session);
		}

		return;
	}

	public Execution getExecutionByTopologyAndId(String topology, Integer id) throws Exception {
		// TODO Auto-generated method stub

		Session session = createSession();
		Execution execution;

		if (topology == null)
			throw new Exception("Topology must not be null.");

		try {

			execution = (Execution) session.createCriteria(Execution.class).add(Restrictions.eq("id", id))
					.add(Restrictions.eq("topology.name", topology)).uniqueResult();

		} catch (Exception e) {

			rollbackTransaction(session.getTransaction());
			throw e;

		} finally {

			closeSession(session);
		}

		return execution;
	}

}
