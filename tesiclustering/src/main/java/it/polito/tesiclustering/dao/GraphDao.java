package it.polito.tesiclustering.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import it.polito.tesiclustering.infrastructure.HibernateUtil;
import it.polito.tesiclustering.model.Execution;
import it.polito.tesiclustering.model.Graph;

public class GraphDao {

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

	public Graph createGraph(Graph graph) throws Exception {

		Session session = createSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(graph);
			tx.commit();
		} catch (Exception e) {

			rollbackTransaction(tx);
			throw e;
		} finally {

			closeSession(session);
		}

		return graph;
	}

	public Graph getGraph(Integer id) throws Exception {

		Session session = createSession();
		Graph graph;

		try {

			graph = (Graph) session.get(Graph.class, id);

		} catch (Exception e) {

			rollbackTransaction(session.getTransaction());
			throw e;

		} finally {

			closeSession(session);
		}

		return graph;

	}

	public List<Graph> getAllGraph() throws Exception {

		Session session = createSession();
		Transaction tx;
		List<Graph> graphs;

		try {

			graphs = (List<Graph>) session.createCriteria(Graph.class).list();

		} catch (Exception e) {

			rollbackTransaction(session.getTransaction());
			throw e;

		} finally {

			closeSession(session);
		}

		return graphs;
	}

	public Graph getGraphByname(String name) throws Exception {
		Session session = createSession();
		Transaction tx = null;
		Graph graph;

		if (name == null)
			throw new Exception("Name must not be null.");

		try {
			tx = session.beginTransaction();
			graph = (Graph) session.createCriteria(Graph.class).add(Restrictions.eq("name", name)).uniqueResult();
			tx.commit();

		} catch (Exception e) {

			rollbackTransaction(session.getTransaction());
			throw e;

		} finally {

			closeSession(session);
		}

		return graph;
	}

	public void deleteGraphByName(String name) throws Exception {
		// TODO Auto-generated method stub

		Session session = createSession();
		Transaction tx = null;
		Graph graph;
		List<Execution> executions;

		if (name == null)
			throw new Exception("Name must not be null.");

		try {
			tx = session.beginTransaction();
			executions = (List<Execution>) session.createCriteria(Execution.class)
					.add(Restrictions.eq("topology.name", name)).list();
			graph = (Graph) session.load(Graph.class, name);
			session.delete(graph);
			for (Execution execution : executions) {

				session.delete(execution);
			}
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
