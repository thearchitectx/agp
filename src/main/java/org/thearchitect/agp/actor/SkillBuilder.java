/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thearchitect.agp.actor;

/**
 *
 * @author x4rb
 */
public class SkillBuilder {
    
    private String id;
    private String name;
    private String description;

    SkillBuilder() {
    }

    public SkillBuilder id(final String value) {
        this.id = value;
        return this;
    }

    public SkillBuilder name(final String value) {
        this.name = value;
        return this;
    }

    public SkillBuilder description(final String value) {
        this.description = value;
        return this;
    }

    public Skill build() {
        return new org.thearchitect.agp.actor.Skill(id, name, description);
    }
    
}
