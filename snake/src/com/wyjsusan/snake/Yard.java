package com.wyjsusan.snake;

import java.awt.*;
import java.awt.event.*;

public class Yard extends Frame {
	




	private static final int ROWS = 50;
	private static final int COLS = 50;
	
	private static final int BLOCK_SIZE = 10;
	
	Image offScreenImage = null;
	
	public void launch() {
		this.setLocation(200, 200);
		this.setSize(COLS * BLOCK_SIZE, COLS * BLOCK_SIZE);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setVisible(true);
	}
	
	

	public static void main(String[] args) {
		new Yard().launch();
		

	}
	
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, COLS * BLOCK_SIZE, COLS * BLOCK_SIZE);
		g.setColor(Color.DARK_GRAY);
		for (int i = 1; i < ROWS; i++) {
			g.drawLine(0, BLOCK_SIZE * i, COLS * BLOCK_SIZE, BLOCK_SIZE * i);
			g.drawLine(BLOCK_SIZE * i, 0, BLOCK_SIZE * i, BLOCK_SIZE * ROWS);
		}
		g.setColor(c);
	}

}
