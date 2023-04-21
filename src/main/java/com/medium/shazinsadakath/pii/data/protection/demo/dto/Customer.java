package com.medium.shazinsadakath.pii.data.protection.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.medium.shazinsadakath.pii.data.protection.demo.utils.ProtectData;
import com.medium.shazinsadakath.pii.data.protection.demo.utils.ProtectDataSerializer;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer implements Serializable {

    @ProtectData
    private String taxId;

    private String firstName;

    private String lastName;

    public Customer() {}

    public Customer(String taxId, String firstName, String lastName) {
        this.taxId = taxId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
