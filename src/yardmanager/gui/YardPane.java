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

import javax.swing.JPanel;

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
	
	public YardPane(Connection conn, Yard yard, ContainerDAO containerDAO) {
		this.conn = conn;
		this.yard = yard;
		this.containerDAO = containerDAO;

		setBackground(Color.BLACK);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		drawYard(g2);
		drawGrid(g2);
	}
	
	public void drawGrid(Graphics2D g2) {
		// TODO
	}
	
	public void drawYard(Graphics2D g2) {
		g2.setColor(Color.WHITE);		
		g2.fillPolygon(yard.getBoundaries());
	}
	
	public void drawContainers(Graphics g2) {
		// TODO
	}
}
