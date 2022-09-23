package com.med_ali.invoice_generator.models;

import java.util.Date;

public class CommandLine {
    private float subtotal;
    private float tva;
    private float total;
    private float benefitPub;
    private String productName;
    private Date datePublicationStart;
    private Date datePublicationEnd;
    public CommandLine(String productName, Date datePublicationEnd, Date datePublicationStart, float subtotal,
                       float tva, float total, float benefitPub) {
        this.productName = productName;
        this.datePublicationEnd = datePublicationEnd;
        this.datePublicationStart = datePublicationStart;
        this.subtotal = subtotal;
        this.tva = tva;
        this.total = total;
        this.benefitPub = benefitPub;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getTva() {
        return tva;
    }

    public void setTva(float tva) {
        this.tva = tva;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getBenefitPub() {
        return benefitPub;
    }

    public void setBenefitPub(float benefitPub) {
        this.benefitPub = benefitPub;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getDatePublicationStart() {
        return datePublicationStart;
    }

    public void setDatePublicationStart(Date datePublicationStart) {
        this.datePublicationStart = datePublicationStart;
    }

    public Date getDatePublicationEnd() {
        return datePublicationEnd;
    }

    public void setDatePublicationEnd(Date datePublicationEnd) {
        this.datePublicationEnd = datePublicationEnd;
    }
}
