/**
 * 
 */
package yardmanager.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.sql.Connection;
import java.util.List;

import javax.swing.JPanel;

import yardmanager.Container;
import yardmanager.Yard;
import yardmanager.dao.ContainerDAO;

/**
 * @author Home
 *
 */
public class YardPane extends JPanel {
	private static int mouseX, mouseY, dragX, dragY;
	private Connection conn;
	private Yard yard;
	private ContainerDAO containerDAO;
	private static int scale;
	
	public YardPane(Connection conn, Yard yard, ContainerDAO containerDAO) {
		this.conn = conn;
		this.yard = yard;
		this.containerDAO = containerDAO;
		scale = 4;

		setBackground(Color.BLACK);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		drawYard(g2);
		drawGrid(g2);
		drawContainers(g2);
	}
	
	public void drawGrid(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		
		for (int i = 0; i < this.getWidth(); i+= 20) {
			g2.drawLine(i, 0, i, this.getHeight());
		}
		
		for (int i = 0; i < this.getHeight(); i += 20) {
			g2.drawLine(0, i, this.getWidth(), i);
		}
	}
	
	public void drawYard(Graphics2D g2) {
		g2.setColor(Color.GRAY);
		g2.fillPolygon(yard.getBoundaries());
	}
	
	public void drawContainers(Graphics g2) {
		List<Container> containers = containerDAO.listByYard(yard.getId(), true);
		
		for (int i = 0; i < containers.size(); i++) {
			switch (containers.get(i).getColour()) {
				case "Green":
					g2.setColor(Color.GREEN);
					break;
				case "Red":
					g2.setColor(Color.RED);
					break;
				case "Blue": 
					g2.setColor(Color.BLUE);
					break;
				case "Black": 
					g2.setColor(Color.BLACK);
					break;
				case "Orange": 
					g2.setColor(Color.ORANGE);
					break;
				case "Yellow": 
					g2.setColor(Color.YELLOW);
					break;
				case "Brown": 
					g2.setColor(Color.MAGENTA);
					break;
				case "White": 
					g2.setColor(Color.WHITE);
					break;
				default: break;
			}
			
			g2.fillRect(containers.get(i).getxPos(), containers.get(i).getyPos(), 8*scale, containers.get(i).getSize()*scale);
		}
	}
}
