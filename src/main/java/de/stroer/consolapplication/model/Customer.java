package de.stroer.consolapplication.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CUSTOMERS")
public class Customer{

    private Long customerId;
    private String ibans;
    private String fName;
    private String lName;

    public Customer() { }

    public Customer(String ibans, String fName, String lName) {
        this.ibans = ibans;
        this.fName = fName;
        this.lName = lName;
    }
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() { return customerId; }
    public void setId(Long id) {
        this.customerId = id;
    }
    @NotNull @Size(min = 22, max = 34)
    @Column(name = "iban", unique = true,length = 34, nullable = false)
    public String getIban() {
        return ibans;
    }
    public void setIban(String ibans) {
        this.ibans = ibans;
    }
    @Column(name="fname",length = 15) @Size(min = 3, max = 15)
    public String getfName() {
        return fName;
    }
    public void setfName(String fName) {
        this.fName = fName;
    }
    @Column(name="lmame",length = 15) @Size(min = 3, max = 15)
    public String getlName() {
        return lName;
    }
    public void setlName(String lName) {
        this.lName = lName;
    }

    @Override
    public String toString() {
        return "CUSTOMERS{" +
                "customerId=" + customerId +
                ", iban='" + ibans + '\'' +
                ", fname='" + fName + '\'' +
                ", lname=" + lName +
                '}';
    }
}
