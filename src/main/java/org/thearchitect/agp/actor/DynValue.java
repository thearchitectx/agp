package org.thearchitect.agp.actor;

/**
 *
 * @author x4rb
 */
public class DynValue {
    private float baseValue;
    private float buff;

    public DynValue(float baseValue) {
        this.baseValue = baseValue;
    }

    public float get() {
        return baseValue + buff;
    }

    public void setBaseValue(float baseValue) {
        this.baseValue = baseValue;
    }

    public float getBaseValue() {
        return baseValue;
    }

    public float getBuff() {
        return buff;
    }
    
}
