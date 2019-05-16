package br.com.drulis.gct.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfGenerator {

	private String arquivoPath;
	
	public void gerar() {
		// criação do documento
		Document document = new Document();
		Random rnd = new Random();
		
		arquivoPath = "/tmp/PDF_DevMedia" + rnd.nextInt() + ".pdf";
		
		try {

			PdfWriter.getInstance(document, new FileOutputStream(arquivoPath));
			document.open();
			// adicionando um parágrafo no documento
			document.add(new Paragraph("Gerando PDF - Java -- " + rnd.nextInt()));
			document.newPage();
			document.add(new Paragraph("Novo parágrafo na nova página " + rnd.nextInt()));
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		document.close();
	}

	public void copiarUltimoPdf() {
		Random rnd = new Random();
		String novoArquivoPath = "/tmp/novoPdf_" + rnd.nextInt() + ".pdf";
		try {
			// cria o reader para o pdf já criado
			PdfReader reader = new PdfReader(arquivoPath);
			// n recebe o numero total de páginas
			int n = reader.getNumberOfPages();
			// tamanho da primeira página
			Rectangle psize = reader.getPageSize(1);
			float width = psize.getHeight();
			float height = psize.getWidth();

			// crie o segundo pdf
			Document document = new Document(new Rectangle(width, height));
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(novoArquivoPath));
			document.open();
			// adiciona conteúdo ao segundo pdf
			PdfContentByte cb = writer.getDirectContent();
			int i = 0;
			int p = 0;
			while (i < n) {
				document.newPage();
				p++;
				i++;
				PdfImportedPage page1 = writer.getImportedPage(reader, i);
				cb.addTemplate(page1, .5f, 0, 0, .5f, 60, 120);
				if (i < n) {
					i++;
					PdfImportedPage page2 = writer.getImportedPage(reader, i);
					cb.addTemplate(page2, .5f, 0, 0, .5f, width / 2 + 60, 120);
				}
				BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
				cb.beginText();
				cb.setFontAndSize(bf, 14);
				cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "page " + p + " of " + ((n / 2) + (n % 2 > 0 ? 1 : 0)),
						width / 2, 40, 0);
				cb.endText();
			}
			document.close();
		} catch (Exception de) {
			de.printStackTrace();
		}

	}
}