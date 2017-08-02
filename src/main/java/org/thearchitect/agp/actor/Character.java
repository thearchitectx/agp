package org.thearchitect.agp.actor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author x4rb
 */
public class Character implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final Map<String, Skill> skills = new HashMap();
    
    public void putSkill(String id, Skill skill) {
        skills.put(id, skill);
    }
    
    public Skill getSkill(String id) {
        return skills.get(id);
    }
}
