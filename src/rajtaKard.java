import java.awt.Color;
import java.awt.Graphics;

public class rajtaKard {
	
	
	
	
	public static void rajtaKardar(Graphics g) {
		
		//SKapar variablar som används för att skapa formerna & korten.
		int y = 610;
		int x = 170;
		int yM = 640; //yM med mellanrum från vänster sida av kortet (så att symbolerna inte sitter fast i kanten av kortet)
		
		int cirkelStorlek = 7;
		
		//Kort
		int kortDistans = 200;
		int combineX;
		int antLoop = 6;
		
		int kortBredd = 100;
		int kortHojd = 150;
		
		int valorPositionX;
		String valor = "A"; //Choose valor
		
		for (int i = 0; i < antLoop; i++) {
			
			combineX = x + (kortDistans * i);
			valorPositionX = 180 + (kortDistans * i);
			
			g.setColor(Color.black);
			g.drawRect(combineX, y, kortBredd, kortHojd); //Ritar korten
			
			//Just delete the forwardslashes if you want to test it
			
			//rajtaHjärter(g, combineX, yM, cirkelStorlek);
			//rajtaRuter(g, combineX, yM, cirkelStorlek);
			//rajtaSpader(g, combineX, yM, cirkelStorlek);
			rajtaKlover(g, combineX, yM, cirkelStorlek);
			
			rajtaValor(g, valorPositionX, yM, valor);
			
		}
		
	}
	
	public static void rajtaHjärter(Graphics g, int combineX, int yM, int cirkelStorlek) {
		
		int xM = combineX + 10; 		//xM med mellanrum från vänster sida av kortet (så att symbolerna inte sitter fast i kanten av kortet)
		
		int combineX1 = xM + 10;
		int combineX2 = xM + 5;
		int combineY = yM + 7;
		int combineY2 = yM - 8;
		int combineY3 = yM - 3;
		
		g.setColor(Color.RED);
		g.drawLine(xM, combineY3, combineX1, combineY3); //Uppe vänster till uppe höger
		
		for (int i = 0; i < 6; i++) {
			
			g.drawLine(combineX2, combineY - i, combineX1 - i, combineY3); 	//Nere till uppe höger
			g.drawLine(combineX2, combineY - i, xM + i, combineY3); 			//Nere till uppe vänster
			
		}
		
		//Cirklarna
		g.fillOval(xM - 1, combineY2, cirkelStorlek, cirkelStorlek);
		g.fillOval(combineX2 - 1, combineY2, cirkelStorlek, cirkelStorlek);
		
	}
	
	public static void rajtaRuter(Graphics g, int combineX, int yM, int cirkelStorlek) {
		
		int xM = combineX + 10; 		//xM med mellanrum från vänster sida av kortet (så att symbolerna inte sitter fast i kanten av kortet)
		
		int combineX1 = xM + 10;
		int combineX2 = xM + 5;
		int combineY1 = yM + 5;
		int combineY2 = yM + 15;
		int combineY4 = yM - 5;
		
		g.setColor(Color.RED);
		
		for (int i = 0; i < 6; i++) {
			
			g.drawLine(combineX2, combineY4 + i, combineX1 - i, combineY1); 		//Uppe till mitten höger
			g.drawLine(combineX2, combineY4 + i, xM + i, combineY1); 				//Uppe till mitten vänster
			g.drawLine(combineX2, combineY2 - i, combineX1 - i, combineY1); //Nere till mitten höger
			g.drawLine(combineX2, combineY2 - i, xM + i, combineY1); 		//Nere till mitten vänster
			
		}
		
	}
		
	public static void rajtaSpader(Graphics g, int combineX, int yM, int cirkelStorlek) {
		
		int xM = combineX + 10; 		//xM med mellanrum från vänster sida av kortet (så att symbolerna inte sitter fast i kanten av kortet)
		
		int combineX1 = xM + 10;
		int combineX2 = xM + 5;
		int combineY1 = yM + 4;
		int combineY2 = yM + 13;
		int combineY3 = yM + 1;
		int combineY4 = yM - 5;
		
		g.setColor(Color.BLACK);
		
		for (int i = 0; i < 8; i++) {
			
			g.drawLine(combineX2, combineY4, xM + 12 - i, combineY1); 		//Uppe till mitten höger
			g.drawLine(combineX2, combineY4, xM - 2 + i, combineY1); 				//Uppe till mitten vänster
			g.drawLine(combineX2, combineY1, combineX1 - i, combineY2); //Mitten till nere höger
			g.drawLine(combineX2, combineY1, xM + 1 + i, combineY2); 	//Mitten till nere vänster
			
		}
		
		//Cirklarna
		g.fillOval(xM - 2, combineY3, cirkelStorlek, cirkelStorlek);
		g.fillOval(combineX2, combineY3, cirkelStorlek, cirkelStorlek);
		
	}
	
	public static void rajtaKlover(Graphics g, int combineX, int yM, int cirkelStorlek) {
		
		int xM = combineX + 10; 		//xM med mellanrum från vänster sida av kortet (så att symbolerna inte sitter fast i kanten av kortet)
		
		int combineX1 = xM + 5;
		int combineX2 = xM + 1;
		int combineY1 = yM + 5;
		int combineY2 = yM + 9;
		int combineY3 = yM -1;
		int combineY4 = yM - 5;
		
		g.setColor(Color.BLACK);
		
		for (int i = 0; i < 5; i++) {
			
			g.drawLine(combineX1, combineY1 + i, xM + 9 - i, combineY2); 	//Mitten till nere höger
			g.drawLine(combineX1, combineY1 + i, combineX2 + i, combineY2); //Mitten till nere vänster
			
		}
		
		//Cirklarna
		g.fillOval(xM - 1, combineY3, cirkelStorlek, cirkelStorlek);
		g.fillOval(combineX1 - 1, combineY3, cirkelStorlek, cirkelStorlek);
		g.fillOval(combineX2, combineY4, cirkelStorlek, cirkelStorlek);
		
	}
	
	public static void rajtaValor(Graphics g, int valorPositionX, int yM, String valor) {
		
		g.drawString(valor, valorPositionX, yM - 10);
		
	}
	
}
