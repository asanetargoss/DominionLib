package mchorse.mclib.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mchorse.mclib.config.values.IConfigValue;

import java.util.HashMap;
import java.util.Map;

public class ConfigCategory
{
	public final String id;
	public final Map<String, IConfigValue> values = new HashMap<String, IConfigValue>();

	public ConfigCategory(String id)
	{
		this.id = id;
	}

	public JsonObject toJSON()
	{
		JsonObject object = new JsonObject();

		for (IConfigValue value : this.values.values())
		{
			object.add(value.getId(), value.toJSON());
		}

		return object;
	}

	public void fromJSON(JsonObject object)
	{
		for (Map.Entry<String, JsonElement> entry : object.entrySet())
		{
			IConfigValue value = this.values.get(entry.getKey());

			if (value != null)
			{
				value.reset();
				value.fromJSON(entry.getValue());
			}
		}
	}
}