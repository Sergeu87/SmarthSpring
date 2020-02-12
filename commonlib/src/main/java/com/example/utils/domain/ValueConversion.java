package com.example.utils.domain;

/**
 * Created by Serhii Razovyi on 05-Nov-19.
 */
public class ValueConversion {

    private String Ref;
    private String Type;

    /**
     * Gets ref.
     *
     * @return the ref
     */
    public String getRef () {
        return Ref;
    }

    /**
     * Sets ref.
     *
     * @param Ref the ref
     */
    public void setRef (String Ref) {
        this.Ref = Ref;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType () {
        return Type;
    }

    /**
     * Sets type.
     *
     * @param Type the type
     */
    public void setType (String Type) {
        this.Type = Type;
    }

    @Override
    public String toString() {
        return "ValueConversion [Ref = " + Ref + ", Type = " + Type + "]";
    }
}
