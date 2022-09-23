package com.med_ali.invoice_generator.service;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import com.med_ali.invoice_generator.models.Command;
import com.med_ali.invoice_generator.models.CommandLine;
import org.springframework.stereotype.Service;
import com.lowagie.text.Table;

import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.awt.Color.BLACK;

@Service
public class InvoiceGenerator {
    private final static Image logo;
    private final  static  Document document = new Document();
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    private static float widthFile;
    static {
        try {
            logo = Image.getInstance("image.png");
            logo.setWidthPercentage(5);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
    public static final Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 8);
    static {
        titleFont.setColor(BLACK);
    }

    private void addRowToTable(Table table, String[] data, boolean isHeader) {
        if(isHeader)
        {
            cellFont.setStyle(Font.BOLD);
        }
        for (String columnName : data) {
            Phrase phrase = new Phrase(columnName);
            phrase.setFont(cellFont);
            Cell cell = new Cell(phrase);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
    }

    private static void createHeader(Command command)
    {
        float twoCol=300f;
        float twoCol150=twoCol +150f;
        float twoColumnWidth[]={twoCol150,twoCol};

        Table table = new Table(2);
        //table.setWidth(widthFile * 10 /100);
        table.setWidths(twoColumnWidth);
        table.setBorder(Table.NO_BORDER);
        logo.setAlignment(Element.ALIGN_LEFT);
        Cell imageCell = new Cell(logo);

       // document.add(logo);
        Phrase commandIdPhrase = new Phrase("Numéro de facture "+command.getId());
        commandIdPhrase.setFont(FontFactory.getFont("Roboto", 12));
        Phrase datePhrase = new Phrase("\n Date: "+new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        Paragraph paragraph = new Paragraph("");
        paragraph.add(commandIdPhrase);
        paragraph.add(datePhrase);
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        //document.add(paragraph);
        Cell textCell = new Cell(paragraph);
        //textCell.setBorder(Rectangle.NO_BORDER);
        textCell.setBorderColor(Color.WHITE);
        imageCell.setBorder(Rectangle.NO_BORDER);
        imageCell.setVerticalAlignment(Element.ALIGN_CENTER);
        textCell.setVerticalAlignment(Element.ALIGN_RIGHT);
        table.addCell(imageCell);
        table.addCell(paragraph);
        Paragraph c = new Paragraph("Destination:\n");
        Phrase nameReceiver = new Phrase(command.getReceiver().getFirstname()+" " + command.getReceiver().getLastname()+"\n", new Font(Font.BOLD));
        Phrase nameSender = new Phrase(command.getSender().getFirstname() +"  "+ command.getSender().getLastname(), new Font(Font.BOLD));
        Phrase addressAndTvaCodeReceiver = new Phrase(command.getReceiver().getAddress() + "\nNuméro TVA"+command.getReceiver().getCodeTva());
        Phrase addressAndTvaCodeSender = new Phrase("\n Numéro de TVA: "+command.getSender().getCodeTva());
        Paragraph c1 = new Paragraph();
        c1.add(nameSender);
        c1.add(addressAndTvaCodeSender);
        Paragraph p1 = new Paragraph();
        c.add(nameReceiver);
        c.add(addressAndTvaCodeReceiver);
        c.setAlignment(Element.ALIGN_LEFT);
        c1.setAlignment(Element.ALIGN_RIGHT);
        document.add(table);
        document.add(c);
        document.add(c1);
        document.setMargins(0, 0, 0, 0);

//        document.add(p1);
    }

    public OutputStream generatePdf(Command command) throws IOException, DocumentException {
        OutputStream fileOutputStream = Files.newOutputStream(Paths.get("model.pdf"));
        PdfWriter writer = PdfWriter.getInstance(document, fileOutputStream);
        document.newPage();
        document.open();
        document.add(new Chunk(""));
        writer.open();
        widthFile = document.getPageSize().getWidth();
        Table table = new Table(5);
        table.setBorder(Rectangle.NO_BORDER);
        String [] headers = {"nom de produit", "description", "sous-total(TND)", "TVA (TND)\n19%", "Total(TND)"};
        addRowToTable(table, headers, true);
        float subTotal=0;
        float tva=0;
        float total=0;
        for (CommandLine commandLine: command.getCommandLines())
        {
            subTotal=commandLine.getSubtotal();
            tva= subTotal * 19/100;
            total=subTotal + tva;
            String description = "date start publication :\t" + dateFormat.format(commandLine.getDatePublicationStart()) + '\n'
                    +"date end publication :\t "+dateFormat.format(commandLine.getDatePublicationEnd());
            addRowToTable(table, new String[]{commandLine.getProductName(), description, String.valueOf(commandLine.getSubtotal()), String.valueOf(tva), String.valueOf(total)}, false);
        }
        createHeader(command);
        table.setWidth(100);
        Paragraph paragraph = new Paragraph("Sous-total (TND):\t \t" + subTotal +
                "\nTVA (TND):    \t \t"+tva +"\nTotal (TND): \t \t"+ total);
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        System.out.println(document.getPageSize().getWidth());
        document.add(table);
        document.add(paragraph);
        document.close();
        writer.close();
        return fileOutputStream;
    }
}
