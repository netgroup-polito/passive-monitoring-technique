package it.polito.tesiclustering.service;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.commons.codec.binary.Base64;
//import org.json.XML;

import it.polito.tesiclustering.dao.GraphDao;
import it.polito.tesiclustering.model.Graph;
import it.polito.tesiclustering.model.exception.DaoException;
import it.polito.tesiclustering.model.exception.GraphAlreadyCreatedException;
import it.polito.tesiclustering.model.exception.UserAlreadyCreatedException;

public class GraphService {

	private List<Graph> graphs = new LinkedList<Graph>();
	private GraphDao graphDao = new GraphDao();
	int id = 0;

	public GraphService(List<Graph> graphs) {
		super();
		this.graphs = graphs;
	}

	private static void writeFileOnServer(String name, String content) throws IOException {

		File file = new File(name);

		BufferedWriter bw;

		FileWriter fw = new FileWriter(file);
		bw = new BufferedWriter(fw);
		bw.write(content);
		bw.close();
		System.out.println("done!!");

	}

	public GraphService() {
	}

	public Graph getGraph(Integer id) throws DaoException {

		try {
			return this.graphDao.getGraph(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DaoException("Cannot get Graph.", e);
		}

	}

	public Graph getGraphByName(String name) throws DaoException {

		try {

			Graph graph = this.graphDao.getGraphByname(name);

			if (graph == null)
				return graph;

			String content = graph.getXml_file();

			String decodedString = new String(Base64.decodeBase64(content.getBytes()));

			graph.setXml_file(decodedString);

			return graph;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DaoException("Cannot get Graph.", e);
		}

	}

	public List<Graph> getGraphs() throws DaoException {

		try {

			List<Graph> listgraphs = this.graphDao.getAllGraph();

			for (Graph graph : listgraphs) {

				String content = graph.getXml_file();

				if (content != null) {

					String decodedString = new String(Base64.decodeBase64(content.getBytes()));

					graph.setXml_file(decodedString);
				} else {

					graph.setXml_file("");

				}

			}

			return listgraphs;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DaoException("Cannot get Graphs.", e);
		}
	}

	public Graph createGraph(Graph graph) throws GraphAlreadyCreatedException, DaoException {

		if (graph == null)
			return graph;

		Graph createdGraph = null;

		try {

			// check if graph is already in database
			createdGraph = this.graphDao.getGraphByname(graph.getName());

			if (createdGraph != null)
				throw new GraphAlreadyCreatedException();

			// creo il file a partire dall'xml della topologia
			String originalInput = graph.getXml_file();
			String decodedString = new String(Base64.decodeBase64(originalInput));
			writeFileOnServer(graph.getName(), decodedString);

			createdGraph = this.graphDao.createGraph(graph);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DaoException("Cannot save a new graph.", e);
		}

		return createdGraph;

	}

	public Integer deleteGraph(String name) throws DaoException {

		if (name == null)
			return 0;

		try {
			this.graphDao.deleteGraphByName(name);
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DaoException("Cannot delete graph.", e);
		}

	}

}
