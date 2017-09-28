package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 06.04.2016.
 */
public class QueryConvert {
    private Map<String, String> entities = new HashMap<>();

   public Map getEntities() {
        return entities;
    }

    public Object getValueOfQueryConvert(final String name) {
        if (name == null)
            return null;
        for (Map.Entry<String, String> stringStringEntry : this.entities.entrySet()) {
            if (stringStringEntry == null)
                continue;
            if (name.equalsIgnoreCase(stringStringEntry.getKey())) {
                return stringStringEntry.getValue();
            }
        }
        return null;
    }
}
