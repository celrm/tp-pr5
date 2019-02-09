package simulator.factories;

import org.json.JSONObject;

import simulator.model.FallingToCenterGravity;
import simulator.model.GravityLaws;
public class FallingToCenterGravityBuilder extends Builder<GravityLaws>{

	public FallingToCenterGravityBuilder(){
		typeTag = "ftcg";
		desc = "Falling to Center Gravity";
	}

	@Override
	public JSONObject getBuilderInfo() {
		JSONObject sol = new JSONObject();
		sol.put("type", typeTag);
		JSONObject otro = new JSONObject();
		sol.put("data", otro);
		sol.put("desc", desc);
		return sol;
	}

	@Override
	protected GravityLaws createTheInstance(JSONObject jsonObject) {
		return new FallingToCenterGravity();
	}

}
