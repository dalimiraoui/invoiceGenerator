package com.med_ali.invoice_generator.models;


import java.util.Date;
import java.util.List;

public class Command {
    private Long id;
    private Person sender;
    private Person receiver;
    private Date date;
    private float subtotal;
    private float tva;
    private float total;
    private List<CommandLine> commandLines;

    public  Command(){}
    public Command(Long id, Person sender, Person receiver, Date date, float subtotal, float tva, float total, List<CommandLine> commandLineList) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
        this.subtotal = subtotal;
        this.tva = tva;
        this.total = total;
        commandLines = commandLineList;
    }

    public Command(Long id, Person sender, Person receiver, float tva, List<CommandLine> commandLines)
    {
        this.total = 0;
        this.subtotal = 0;
        for (CommandLine commandLine :
                commandLines) {
            this.total += commandLine.getTotal();
            this.subtotal += commandLine.getSubtotal();
        }
        this.receiver = receiver;
        this.sender = sender;
        this.tva = tva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public List<CommandLine> getCommandLines() {
        return commandLines;
    }

    public void setCommandLines(List<CommandLine> commandLines) {
        this.commandLines = commandLines;
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public Person getReceiver() {
        return receiver;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }
}
