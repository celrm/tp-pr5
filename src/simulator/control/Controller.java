package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.PhysicsSimulator;

public class Controller {
	private PhysicsSimulator simulador;
	private Factory<Body> factoria;
	public Controller(PhysicsSimulator sim, Factory<Body> fact){
		simulador = sim;
		factoria = fact;
	}
	public void loadBodies(InputStream in){
		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		JSONArray array = jsonInput.getJSONArray("bodies");
		try {
			for (int i = 0; i < array.length(); i++)
				simulador.addBody(factoria.createInstance(array.getJSONObject(i)));
		}
		catch(IllegalArgumentException e){
			System.err.println("Illegal argument: " + e.getMessage());
		}
	}
	public void run(int n, OutputStream out){
//		JSONObject estados = new JSONObject();
//		JSONArray array = new JSONArray();
//		for (int i= 0; i < n;++i){
//			array.put(simulador.toObject());
//			simulador.advance();
//		}
//		estados.put("states", array);
//		PrintStream p = new PrintStream(out);
//		p.println(estados);
//		
		StringBuilder s = new StringBuilder();
		s.append("{ \"states\": [");
		
		if(n>0)
			s.append(simulador.toString());
		for (int i= 1; i < n;++i){
	    	s.append(", ");
			s.append(simulador.toString());
			simulador.advance();
		}	    
		s.append("] }");
		PrintStream p = new PrintStream(out);
		p.println(s);
	}
	
}
