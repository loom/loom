package io.loom.core.messaging;

class MutableMessageWithoutJsonCreator implements Message {
    private int intField;
    private String stringField;

    public int getIntProperty() {
        return intField;
    }

    public void setIntProperty(int intValue) {
        this.intField = intValue;
    }

    public String getStringProperty() {
        return stringField;
    }

    public void setStringProperty(String stringValue) {
        this.stringField = stringValue;
    }
}
