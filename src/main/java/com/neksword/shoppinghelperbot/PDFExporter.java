package com.neksword.shoppinghelperbot;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

@Component
public class PDFExporter implements Exporter {

    @Override
    public File export(ShoppingList shoppingList, CallbackQueryData callbackQueryData) {
        Document document = new Document();
        final var formattedDate = LocalDate.now().format(ISO_LOCAL_DATE);
        final var fileName = formattedDate + ".pdf";
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
        } catch (DocumentException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        document.open();
        PdfPTable table = new PdfPTable(4);
        table.setHeaderRows(1);
        table.addCell(formattedDate);
        table.completeRow();
        addGoodRows(shoppingList, table);

        try {
            document.add(table);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        document.close();
        return new File(fileName);
    }

    private void addGoodRows(ShoppingList shoppingList, PdfPTable table) {
        shoppingList.getGoodsList()
                    .forEach(good -> {
                        table.addCell(good.getName());
                        table.addCell(String.valueOf(good.getQuantity()));
                        table.addCell(String.valueOf(good.getPrice()));
                        table.addCell(String.valueOf(good.getTotal()));
                        table.completeRow();
                    });
    }
}
