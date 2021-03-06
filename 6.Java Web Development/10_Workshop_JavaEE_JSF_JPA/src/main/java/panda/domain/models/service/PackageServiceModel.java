package panda.domain.models.service;

import panda.domain.entities.Status;
import panda.domain.entities.User;

import java.time.LocalDateTime;

public class PackageServiceModel {

    private String id;
    private String description;
    private Double weight;
    private String shippingAddress;
    private Status status;
    private LocalDateTime estimatedDeliveryDate;
    private UserServiceModel recipient;

    public PackageServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(LocalDateTime estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public UserServiceModel getRecipient() {
        return recipient;
    }

    public void setRecipient(UserServiceModel recipient) {
        this.recipient = recipient;
    }
}
