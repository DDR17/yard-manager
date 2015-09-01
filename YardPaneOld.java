package yardmanager;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class YardPane extends JPanel
{
	private static int mouseX, mouseY, dragX, dragY;
	int conDim[][], length, height, pointX, pointY, viewLevel, addLevel, index, oldX, oldY, oldLevel, l=3, adj;
	Database db;
	boolean update, active, boundBlock, sizeBlock, point, attach, covered, nameDrawn, adjacent = false;
	String colourList[], conName[], name, newColour;
	Color colour;
	
	public YardPane()
	{		
		point = false;
		update = true;
		active = false;
		viewLevel = 1;
		
		addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {}
			
			public void mouseEntered(MouseEvent e) {}
			
			public void mouseExited(MouseEvent e) {}
			
			public void mousePressed(MouseEvent e) 
			{
				
				if (e.getButton() == MouseEvent.BUTTON1 && active && !boundBlock && !sizeBlock )
				{
					if(covered)
					{
						JOptionPane.showMessageDialog(new JFrame(), "Selected container is covered.", "", JOptionPane.WARNING_MESSAGE);
					}
					else
					{
					db = new Database();
					db.setLocation(name, mouseX/l*l, mouseY/l*l, length, height, newColour, addLevel, oldX, oldY, oldLevel);
					update = true;
					active = false;
					}
				}
				
				if(e.getButton() == MouseEvent.BUTTON3) 
				{
					if(active) 
					{
						int temp = height;
						height = length;
						length = temp;
					}
					
					if(overlap())
					{
						if(conDim[index][5]>0)
						{
							JOptionPane.showMessageDialog(new JFrame(), "Selected container is covered.", "", JOptionPane.WARNING_MESSAGE);
						} else
						{
						name = conName[index];
						oldX = conDim[index][0];
						oldY = conDim[index][1];
						oldLevel = conDim[index][4];
						length = conDim[index][2];
						height = conDim[index][3];
						newColour = colourList[index];
						active = true;
						}
					}
				}
				checkBoundary();
				point = false;
				repaint();
			}
			
			public void mouseReleased(MouseEvent e) 
			{
				
				//if(adjacent)
				//{
				//	if(adj > 0 && Math.abs(adj) == 1){
				//		mouseX = conDim[index][0] + conDim[index][2];
				//}
				//	adjacent = false;
				//} 
			}
		});
		
		addMouseMotionListener(new MouseMotionListener()
		{
			public void mouseDragged(MouseEvent e) 
			{
				//if(attach && active) {
					
				//	adjacent = true; 
				//	dragX = e.getX();
				//	dragY = e.getY();
				
				//	if(Math.abs(dragX-mouseX) >= Math.abs(dragY-mouseY)) {
				//		adj = (dragX-mouseX)/Math.abs(dragX-mouseX);
				//	} else
				//	{
				//		adj = 2*(dragY-mouseY)/Math.abs(dragY-mouseY);
				//	}
				//}	
			}
			
			public void mouseMoved(MouseEvent e) 
			{
				mouseX = e.getX();
				mouseY = e.getY();
				checkBoundary();
				addLevel = 1;
				if(active) 
				{
					covered = false;
					if(overlap())
					{
						if(conDim[index][4] !=3 && oldX != conDim[index][0] && oldY != conDim[index][1]) 
						{
							if(conDim[index][5]>0) covered = true;
							attach = true;
							mouseX = conDim[index][0];
							mouseY = conDim[index][1];
							length = conDim[index][2];
							height = conDim[index][3];
							addLevel = conDim[index][4] + 1;
						}
						else boundBlock = true;
					}			 
				}
				repaint();
			}
		
		});
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		drawBackground(1290, 1200, g2);
		drawContainers(g2);
		if(point) drawPointer(g2);
		drawName(g2);
		if(active) drawCursor(g2);
	}
	
	public void drawBackground(int width, int height, Graphics2D g2)
	{		
		setPreferredSize(new Dimension(width, height));
		
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillRect(0, 0, 1290, 1200);
		
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, 6, 1110);
		g2.fillRect(0, 0, 1290, 6);
		g2.fillRect(0, 1110, 705, 6);
		g2.fillRect(705, 1110, 6, 90);
		g2.fillRect(705, 1194, 585, 6);
		g2.fillRect(1284, 0, 6, 570);
		g2.fillRect(1284, 675, 6, 525);
		g2.setColor(Color.GREEN);
		g2.fillRect(1284, 570, 6, 105);
		g2.setColor(Color.GRAY);
		g2.fillRect(0, 1116, 705, 84);
		
		g2.setColor(Color.BLACK);
		for (int i = 0; i < width/7; i++) {
			int y2 = 1110;
			if(i>33) y2 = 1200;
			g2.drawLine(i*21, 0, i*21, y2);
		}
		for (int i = 0; i < height/7; i++) {
			int x1 = 0;
			if(i > 52) x1 = 711;
			g2.drawLine(x1, i*21, 1283, i*21);
		}
	}
	
	public void drawCursor(Graphics2D g2)
	{
		g2.setColor(Color.CYAN);
		g2.setStroke(new BasicStroke(2));
		g2.drawRect(mouseX/l*l, mouseY/l*l, length, height);
		if(boundBlock || sizeBlock) 
		{
			g2.drawLine(mouseX/l*l, mouseY/l*l, mouseX/l*l + length, mouseY/l*l + height);
			g2.drawLine(mouseX/l*l, mouseY/l*l +height, mouseX/l*l +length, mouseY/l*l);
		}
	}
	
	public void drawContainers(Graphics2D g2)
	{
		if(update) {
			db = new Database();
			conDim = db.getContainers(viewLevel);
			db = new Database();
			colourList = db.getColour(viewLevel);
			db = new Database();
			conName = db.getNames(viewLevel);
		}
		update = false;
		
		for(int j = 1; j<4; j++)
		{
			for (int i = 0; i < conDim.length; i++)
			{
				if(conDim[i][4] == j)
				{
					String str = String.valueOf(conDim[i][4]);
					if(conDim[i][5]==2) str = String.valueOf(conDim[i][4]) + "**";
					if(conDim[i][5]==1) str = String.valueOf(conDim[i][4]) + "*";
					if(colourList[i].equals("Red")) colour = Color.RED;
					if(colourList[i].equals("Blue")) colour = Color.BLUE;
					if(colourList[i].equals("Green")) colour = Color.GREEN;
					if(colourList[i].equals("Black")) colour = Color.BLACK;
					if(colourList[i].equals("Orange")) colour = Color.ORANGE;
					g2.setColor(colour);
					g2.fillRect(conDim[i][0], conDim[i][1], conDim[i][2], conDim[i][3]);
					g2.setColor(Color.BLACK);
					g2.drawRect(conDim[i][0], conDim[i][1], conDim[i][2], conDim[i][3]);
					g2.drawString(str, conDim[i][0]+conDim[i][2]/2, conDim[i][1]+conDim[i][3]/2);
					
				}
			}
		}
		
	}
	
	public void setDim(String nm, int lgth, String clr){
		name = nm;
		newColour = clr;		
		length = lgth;
		height = 24;
		active = true;
	}
	
	public void drawName(Graphics2D g2) {
		nameDrawn = false;
		for(int z = viewLevel; z > -1; z--) {
			for(int i = 0; i < conDim.length; i++) {
				if(conDim[i][4] == z && (mouseX > conDim[i][0]) && (mouseX < conDim[i][0] + conDim[i][2]) && (mouseY > conDim[i][1]) && (mouseY < conDim[i][1] + conDim[i][3])) {
					g2.setColor(Color.WHITE);
					g2.fillRect(mouseX, mouseY - 15, 100, 17);
					g2.setColor(Color.BLACK);
					g2.drawString(conName[i], mouseX, mouseY);
					nameDrawn = true;
					break;
				}
			}
			if(nameDrawn) break;
		}
		
	}
	
	public void drawPointer(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.drawLine(pointX + 10, 0, pointX + 10, 1200);
		g2.drawLine(0, pointY +10, 1290, pointY + 10);		
	}
	
	public void checkBoundary() {
		boundBlock = false;
		if(mouseX < 711 && mouseY > 1110 - height) boundBlock = true;
	}
	
	public boolean overlap() 
	{
		sizeBlock = false;
		for(int z = 3; z >= 1; z--)
		{
			for(int k =0; k < conDim.length; k++) 
			{
				if((mouseX/l*l > conDim[k][0] || mouseX/l*l + length > conDim[k][0]) && (mouseX/l*l < conDim[k][0] + conDim[k][2] || mouseX/l*l + length < conDim[k][0] + conDim[k][2]) && (mouseY/l*l > conDim[k][1] || mouseY/l*l + height > conDim[k][1]) && (mouseY/l*l < conDim[k][1] + conDim[k][3] || mouseY/l*l + height < conDim[k][1] + conDim[k][3]) && conDim[k][4] == z)
				{
					if(length + height != conDim[k][2] + conDim[k][3] && active == true )
					{
						sizeBlock = true;
						return false;
					} else {
						index=k;
						return true;
					}
						
				}
				
			}
		
		}
		return false;
	}
}