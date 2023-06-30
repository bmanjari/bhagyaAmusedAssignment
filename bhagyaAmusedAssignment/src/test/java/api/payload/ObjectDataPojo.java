package api.payload;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ObjectDataPojo {
    private int year;
    private Double price;
    private String CPU_model;
    private String hard_disk_size;
    private String color;
    private String capacity;

    @Override
    public String toString() {
        return "ObjectDataPojo{" +
                "year=" + year +
                ", price=" + price +
                ", CPU_model='" + CPU_model + '\'' +
                ", hard_disk_size='" + hard_disk_size + '\'' +
                ", color='" + color + '\'' +
                ", capacity='" + capacity + '\'' +
                '}';
    }


    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }



    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public String getHard_disk_size() {
        return hard_disk_size;
    }

    public void setHard_disk_size(String Hard_disk_size) {
        this.hard_disk_size = Hard_disk_size;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCPU_model() {
        return CPU_model;
    }

    public void setCPU_model(String CPU_model) {
        this.CPU_model = CPU_model;
    }



    }







