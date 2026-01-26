package com.orion.DigiWallet.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


// TODO 2.1
//UNCOMMENT THE ANNOTATIONS BELOW TO MAKE THIS CLASS A JPA ENTITY
@Entity
@Table(name = "card")
public class Card {


    // Mark this field as the primary key of the entity
    // Use auto-generation strategy suitable for MySQL
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Define a many-to-one relationship with Wallet
    // Each card must belong to exactly one wallet
    // Use @JoinColumn with name "wallet_id" and make it NOT NULL
    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    // Store the physical card number
    // - Must be unique
    // - Must be exactly 16 characters
    // - Must NOT be null
    @Column(name = "card_number", nullable = false, unique = true, length = 16)
    private String cardNumber;

    // Store the card type
    // Examples: DEBIT / PREPAID / VIRTUAL
    // - Must NOT be null
    // - Length should be limited (around 10 characters)
    @Column(name = "card_type", nullable = false, length = 10)
    private String cardType;

    // Store the card expiry date
    // Use LocalDate (not LocalDateTime)
    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    // Store the current card status
    // Examples: ACTIVE / BLOCKED / EXPIRED
    // - Must NOT be null
    // - Length should be limited (around 20 characters)
    @Column(name = "status", nullable = false, length = 20)
    private String status;

    // Store the card issued timestamp
    // This value should be automatically set when the card is created
    @Column(name = "issued_at", nullable = false)
    private LocalDateTime issuedAt;


    //[OPTIONAL]
    // Use a JPA lifecycle callback
    // Automatically set issuedAt before persisting
    // Also set default values for:
    // - status (ACTIVE)
    // - cardType (DEBIT)
    @PrePersist
    public void prePersist() {
        this.issuedAt = LocalDateTime.now();

        if (this.status == null) {
            this.status = "ACTIVE";
        }

        if (this.cardType == null) {
            this.cardType = "DEBIT";
        }
    }

    //ONCE YOU ARE DONE, PROCEED TO RESTART THE APPLICATION
    //GO TO MYSQL WORKBENCH AND VERIFY THAT THE "card" TABLE IS CREATED AS EXPECTED
    // -----------------------------------------
    // Getters & Setters
    // -----------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }


}
