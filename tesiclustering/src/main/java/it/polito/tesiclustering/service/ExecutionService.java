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
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.transaction.Transactional;

import org.apache.tools.ant.util.FileUtils;
import org.python.util.PythonInterpreter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.polito.tesiclustering.dao.ExecutionDao;
import it.polito.tesiclustering.model.Execution;
import it.polito.tesiclustering.model.Graph;
import it.polito.tesiclustering.model.InfoCluster;
import it.polito.tesiclustering.model.Node;
import it.polito.tesiclustering.model.Result;
import it.polito.tesiclustering.model.User;
import it.polito.tesiclustering.model.exception.DaoException;
import it.polito.tesiclustering.model.exception.ExecutionAlreadyCreatedException;

public class ExecutionService {

	private List<Execution> executions = new LinkedList<Execution>();
	private ExecutionDao executionDao = new ExecutionDao();
	int id = 0;

	public ExecutionService(List<Execution> executions) {
		super();
		this.executions = executions;
	}

	private static String removeJSONStringEscapeChars(String toParse) {

		return toParse.replaceAll("\\\\", "");

	}

	public ExecutionService() {
	}

	public Execution getExecutionByTopologyAndId(String topology, Integer id) throws DaoException {

		try {
			return this.executionDao.getExecutionByTopologyAndId(topology, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DaoException("Cannot get Execution.", e);
		}

	}

	@Transactional
	public List<Execution> getExecutions() throws DaoException {

		try {

			return this.executionDao.getAllExecution();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DaoException("Cannot get Executions.", e);
		}
	}

	@Transactional
	public List<Execution> getExecutionsByTopology(String topology) throws DaoException {

		try {

			return this.executionDao.getAllExecutionByTopology(topology);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DaoException("Cannot get Executions.", e);
		}
	}

	public Execution createExecution(Execution execution) throws ExecutionAlreadyCreatedException, DaoException {

		if (execution == null)
			return execution;

		Execution createdExecution = null;

		try {

			if (createdExecution != null)
				throw new ExecutionAlreadyCreatedException();

			// chiamare lo script in python
			PythonInterpreter python = null;

			String rate = null;

			String topologyXml = execution.getTopology().getXml_file();
			String topologyName = execution.getTopology().getName();

			if (execution.getRate() != null) {

				rate = execution.getRate().toString();
			} else if (execution.getNodes() != null) {

				rate = execution.getNodes();
				rate = removeJSONStringEscapeChars(rate);
			}

			String topology = execution.getTopology().getName();

			String[] arguments = { "CompleteIterativeClustering_v3.py", topology, rate };

			try {
				Properties p = new Properties();
				p.setProperty("python.path", "C:\\jython2.7.0");
				p.setProperty("python.home", "C:\\jython2.7.0");
				p.setProperty("python.prefix", "C:\\jython2.7.0");
				PythonInterpreter.initialize(System.getProperties(), p, arguments);
				python = new PythonInterpreter();
			} catch (Exception e) {
				e.printStackTrace();
			}

			StringWriter out = new StringWriter();
			python.setOut(out);
			python.execfile("CompleteIterativeClustering_v3.py");
			String outputStr = out.toString();
			System.out.println(outputStr);

			ObjectMapper mapper = new ObjectMapper();
			File f = null;
			File file = null;

			try {

				// JSON from file to Object
				f = new File("totclusters.json");

				if (f.createNewFile()) {

					System.out.println("File is created!");
				} else {

					System.out.println("File not created.");
				}

				BufferedReader br = null;
				FileReader fr = null;
				try {

					fr = new FileReader(new File(f.getAbsolutePath()));

					br = new BufferedReader(fr);

				} catch (FileNotFoundException e) {

					e.printStackTrace();
				}
				String line;
				StringBuilder sb = new StringBuilder();
				try {
					while ((line = br.readLine()) != null) {
						sb.append(line.trim());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				System.out.println("Convert json file inserted to string");
				System.out.println("Stringa json " + sb);

				String str = sb.toString();

				str = str.replaceFirst("\\[", "");

				str = str.substring(0, str.length() - 1) + "";

				System.out.println("Stringa new json " + str);

				fr.close();
				br.close();

				file = new File("report.json");

				if (file.createNewFile()) {

					System.out.println("File 2 is created!");
				} else {

					System.out.println("File 2 not created.");
				}

				BufferedWriter bw;

				try {

					FileWriter fw = new FileWriter(file);
					bw = new BufferedWriter(fw);
					bw.write(str);
					bw.close();
					fw.close();
					System.out.println("done!!");

				} catch (IOException e) {
					e.printStackTrace();
				}

				Execution execution1 = new Execution();
				execution1 = mapper.readValue(new File("report.json"), Execution.class);

				System.out.println("execution1" + execution1);
				System.out.println("execution" + execution);

				execution.setInfo_clusters(execution1.getInfo_clusters());
				execution.setNum_clusters(execution1.getNum_clusters());

			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			createdExecution = this.executionDao.createExecution(execution);
			f.delete();
			file.delete();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DaoException("Cannot save a new execution.", e);
		}

		return createdExecution;

	}

	public Integer deleteExecution(String topology, Integer id) throws DaoException {

		if (id == null)
			return 0;

		try {
			this.executionDao.deleteExecutionByTopologyAndId(topology, id);
			;
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DaoException("Cannot delete execution.", e);
		}
	}

}
