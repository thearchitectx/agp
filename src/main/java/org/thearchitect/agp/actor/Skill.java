package org.thearchitect.agp.actor;

import java.io.Serializable;

/**
 *
 * @author x4rb
 */
public class Skill implements Serializable {
    private final String id;
    private final String name;
    private final String description;
    private final DynValue value;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public DynValue getValue() {
        return value;
    }

    Skill(final String id, final String name, final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.value = new DynValue(0);
    }
    
    public static SkillBuilder builder() {
        return new SkillBuilder();
    }

}
