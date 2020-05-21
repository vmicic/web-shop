package com.ftn.webshop.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CustomerCategory extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "customerCategory")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ConsumptionThreshold> consumptionThresholds = new ArrayList<>();

    public CustomerCategory() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ConsumptionThreshold> getConsumptionThresholds() {
        return consumptionThresholds;
    }

    public void setConsumptionThresholds(List<ConsumptionThreshold> consumptionThresholds) {
        this.consumptionThresholds = consumptionThresholds;
    }

    @Override
    public String toString() {
        return "CustomerCategory{" +
                "name='" + name + '\'' +
                ", consumptionThresholds=" + consumptionThresholds +
                '}';
    }
}
