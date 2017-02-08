import java.awt.Color;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class Main {
	
	public static PDPage page = null;
	
	public static void main(String[] args) throws IOException {
    	
		PDDocument doc = new PDDocument();
		page = new PDPage();
		doc.addPage(page);

		PDPageContentStream contentStream = new PDPageContentStream(doc, page);
		
		for (int i=0; i<10; i++) {
	         //Creating a blank page 
	         PDPage blankPage = new PDPage();

	         //Adding the blank page to the document
	         doc.addPage( blankPage );
	         
		}
		
		DrawTable dT = new DrawTable(); 
		
		dT.addVal("asd", "asdd" , "asdf");
		dT.addVal("adas", "asdasd", "asdasddddd");
		
		String[][] content = dT.getTable();
		
		drawTable(page, contentStream, 700, 50, content);
		
		contentStream.close();
		try {
			doc.save("test.pdf" );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	@SuppressWarnings("deprecation")
	public static void drawTable(PDPage page, PDPageContentStream contentStream, float y, float margin, String[][] content) throws IOException {
		
		final int rows = content.length;
		final int cols = content[0].length;
		final float rowHeight = 20f;
		final float tableWidth = page.getMediaBox().getWidth() - margin - margin;
		final float tableHeight = rowHeight * rows;
		final float colWidth = tableWidth/(float)cols;
		final float cellMargin=5f;
		
		//draw the rows
		float nexty = y ;
		for (int i = 0; i <= rows; i++) {
			contentStream.drawLine(margin, nexty, margin+tableWidth, nexty);
			nexty-= rowHeight;
		}
		
		//draw the columns
		float nextx = margin;
		for (int i = 0; i <= cols; i++) {
			contentStream.drawLine(nextx, y, nextx, y-tableHeight);
			nextx += colWidth;
		}

		
		
		
		float textx = margin+cellMargin;
		float texty = y-15;
		for(int i = 0; i < 1; i++){
			for(int j = 0 ; j < 3; j++){
				String text = content[i][j];
				contentStream.setFont(PDType1Font.HELVETICA_BOLD , 15);
				contentStream.setNonStrokingColor(Color.RED);
				contentStream.beginText();
				contentStream.moveTextPositionByAmount(textx,texty);
				contentStream.drawString(text);
				contentStream.endText();
				textx += colWidth;
			}
			
			texty-=rowHeight;
			textx = margin+cellMargin;
		}
		
		for(int i = 1; i < content.length; i++){
			for(int j = 0 ; j < content[i].length; j++){
				String text = content[i][j];
				contentStream.setFont(PDType1Font.TIMES_ROMAN , 10);
				contentStream.setNonStrokingColor(Color.BLACK);
				contentStream.beginText();
				contentStream.moveTextPositionByAmount(textx,texty);
				contentStream.drawString(text);
				
				contentStream.endText();
				textx += colWidth;
			}
			
			texty-=rowHeight;
			textx = margin+cellMargin;
		}
	}
}