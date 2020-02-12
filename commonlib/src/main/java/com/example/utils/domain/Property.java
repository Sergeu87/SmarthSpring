package com.example.utils.domain;

/**
 * Created by Serhii Razovyi on 06-Nov-19.
 */
public class Property {

    private String id;
    private String name;
    private String accessMode;
    private String valueType;
    private String refValueType;

    /**
     * Instantiates a new Property.
     *
     * @param id           the id
     * @param name         the name
     * @param accessMode   the access mode
     * @param valueType    the value type
     * @param refValueType the ref value type
     */
    public Property(String id,
                    String name,
                    String accessMode,
                    String valueType,
                    String refValueType) {
        this.id = id;
        this.name = name;
        this.accessMode = accessMode;
        this.valueType = valueType;
        this.refValueType = refValueType;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId () {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId (String id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName () {
        return name;
    }

    /**
     * Sets name.
     *
     * @param Name the name
     */
    public void setName (String Name) {
        this.name = Name;
    }

    /**
     * Gets access mode.
     *
     * @return the access mode
     */
    public String getAccessMode () {
        return accessMode;
    }

    /**
     * Sets access mode.
     *
     * @param AccessMode the access mode
     */
    public void setAccessMode (String AccessMode) {
        this.accessMode = AccessMode;
    }

    /**
     * Gets value type.
     *
     * @return the value type
     */
    public String getValueType () {
        return valueType;
    }

    /**
     * Sets value type.
     *
     * @param ValueType the value type
     */
    public void setValueType (String ValueType) {
        this.valueType = ValueType;
    }

    /**
     * Gets ref value type.
     *
     * @return the ref value type
     */
    public String getRefValueType () {
        return refValueType;
    }

    /**
     * Sets ref value type.
     *
     * @param RefValueType the ref value type
     */
    public void setRefValueType (String RefValueType) {
        this.refValueType = RefValueType;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = "+id+", name = "+ name +", accessMode = "+ accessMode +", valueType = "+ valueType +", refValueType = "+ refValueType +"]";
    }
}
