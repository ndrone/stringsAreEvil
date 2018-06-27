package org.example;

class ValueHolder {

    private Integer elementId;
    private Integer vehicleId;
    private Integer term;
    private Integer mileage;
    private Double value;

    ValueHolder(String line) {
        this(line.split(","));
    }

    ValueHolder(String[] parts) {
        elementId = Integer.valueOf(parts[1]);
        vehicleId = Integer.valueOf(parts[2]);
        term = Integer.valueOf(parts[3]);
        mileage = Integer.valueOf(parts[4]);
        value = Double.valueOf(parts[5]);
    }

    public ValueHolder(Integer elementId, Integer vehicleId, Integer term, Integer mileage, Double value) {
        this.elementId = elementId;
        this.vehicleId = vehicleId;
        this.term = term;
        this.mileage = mileage;
        this.value = value;
    }
}
