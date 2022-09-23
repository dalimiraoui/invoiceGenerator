package com.med_ali.invoice_generator;


import com.lowagie.text.DocumentException;
import com.med_ali.invoice_generator.models.Person;
import com.med_ali.invoice_generator.models.Command;
import com.med_ali.invoice_generator.models.CommandLine;
import com.med_ali.invoice_generator.service.InvoiceGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class GenerateInvoiceTest {
    private final InvoiceGenerator invoiceGenerator;
    @Autowired
    public GenerateInvoiceTest(InvoiceGenerator invoiceGenerator) {
        this.invoiceGenerator = invoiceGenerator;
    }

    @Test
    void contextLoads() {
    }

    @Test
    void generateInvoice() throws DocumentException {

        {
            try {
                Person sender = new Person("omar", "triki", "021544964", "sfax afran 3093");
                Person receiver = new Person("Strics", "Ads", "1733057/B", "Cité Snit Sahloul,4054");
                CommandLine commandLine0 = new CommandLine("this is the publication 1",new Date(), new Date(),(float) 125.14, 23.00F, 147F, 120.01F);
                CommandLine commandLine1 = new CommandLine("this is the publication 2",new Date(), new Date(), (float) 125.14, 23.00F, 147F, 120.01F);
                CommandLine commandLine2 = new CommandLine("this is the publication 3",new Date(), new Date(), (float) 125.14, 23.00F, 147F, 120.01F);
                CommandLine commandLine3 = new CommandLine("this is the publication 4", new Date(), new Date(), (float) 125.14, 23.00F, 147F, 120.01F);
                List<CommandLine> commandLines = new ArrayList<>();
                commandLines.add(commandLine0);
                commandLines.add(commandLine1);
                commandLines.add(commandLine2);
                commandLines.add(commandLine3);
                Command command = new Command(5463543546534L, sender, receiver, new Date(), 155646, 12, 565464, commandLines);
                OutputStream outputStream = invoiceGenerator.generatePdf(command);
                System.out.println(outputStream);
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
