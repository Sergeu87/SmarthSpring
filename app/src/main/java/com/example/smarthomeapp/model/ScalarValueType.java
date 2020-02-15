package com.example.smarthomeapp.model;

/**
 * Created by Serhii Razovyi on 05-Nov-19.
 */
public class ScalarValueType {

    private String name;
    private String step;
    private String minValue;
    private String numBits;
    private String units;
    private String scalarId;
    private String maxValue;

    /**
     * Empty constructor for XML de/serialization
     */
    public ScalarValueType() {
    }

    /**
     * Instantiates a new Scalar value type.
     *
     * @param name            the name
     * @param step            the step
     * @param minValue        the min value
     * @param numBits         the num bits
     * @param units           the units
     * @param scalarId        the scalar id
     * @param maxValue        the max value
     */
    public ScalarValueType(String name, String step, String minValue, String numBits, String units, String scalarId, String maxValue) {
        this.name = name;
        this.step = step;
        this.minValue = minValue;
        this.numBits = numBits;
        this.units = units;
        this.scalarId = scalarId;
        this.maxValue = maxValue;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param Name the name
     */
    public void setName(String Name) {
        this.name = Name;
    }

    /**
     * Gets step.
     *
     * @return the step
     */
    public String getStep() {
        return step;
    }

    /**
     * Sets step.
     *
     * @param Step the step
     */
    public void setStep(String Step) {
        this.step = Step;
    }

    /**
     * Gets min value.
     *
     * @return the min value
     */
    public String getMinValue() {
        return minValue;
    }

    /**
     * Sets min value.
     *
     * @param MinValue the min value
     */
    public void setMinValue(String MinValue) {
        this.minValue = MinValue;
    }

    /**
     * Gets num bits.
     *
     * @return the num bits
     */
    public String getNumBits() {
        return numBits;
    }

    /**
     * Sets num bits.
     *
     * @param NumBits the num bits
     */
    public void setNumBits(String NumBits) {
        this.numBits = NumBits;
    }

    /**
     * Gets units.
     *
     * @return the units
     */
    public String getUnits() {
        return units;
    }

    /**
     * Sets units.
     *
     * @param Units the units
     */
    public void setUnits(String Units) {
        this.units = Units;
    }

    /**
     * Gets scalar id.
     *
     * @return the scalar id
     */
    public String getScalarId() {
        return scalarId;
    }

    /**
     * Sets scalar id.
     *
     * @param id the id
     */
    public void setScalarId(String id) {
        this.scalarId = id;
    }

    /**
     * Gets max value.
     *
     * @return the max value
     */
    public String getMaxValue() {
        return maxValue;
    }

    /**
     * Sets max value.
     *
     * @param MaxValue the max value
     */
    public void setMaxValue(String MaxValue) {
        this.maxValue = MaxValue;
    }

    @Override
    public String toString()
    {
        return "ScalarValueType [name = " + name + ", step = " + step
                + ", minValue = " + minValue + ", numBits = " + numBits
                + ", units = " + units + ", id = " + scalarId
                + ", maxValue = " + maxValue + "]";
    }
}
