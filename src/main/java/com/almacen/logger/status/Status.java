package com.almacen.logger.status;

public enum Status {
    INFO(1),WARNING(2), ERROR(3);

    private Integer value;

    private Status(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}