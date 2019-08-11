package com.wyjsusan.snake;

import java.awt.*;
import java.awt.event.*;

public class Yard extends Frame {
	PaintThread paintThread = new PaintThread();

	private boolean gameOver = false;

	public static final int ROWS = 30;
	public static final int COLS = 30;
	
	public static final int BLOCK_SIZE = 15;
	
	private int score = 0;
	
	public int getScore() {
		return score;
	}



	private Runnable PaintThread() {
		// TODO Auto-generated method stub
		return null;
	}



	public void setScore(int score) {
		this.score = score;
	}

	Snake s = new Snake(this);
	Egg e = new Egg();
	
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
		this.addKeyListener(new KeyMonitor());
		new Thread(paintThread).start();
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
		g.setColor(Color.yellow);
		g.drawString("store:" + score, 10, 60);
		
		if (gameOver) {
			g.setFont(new Font("Calibra", Font.BOLD, 50));
			g.drawString("GAME OVER!", 100, 180);
			paintThread.gameOver();
		}
		g.setColor(c);
		s.eat(e);
		e.draw(g);
		s.draw(g);
		
		
	}
	
	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	public void stop() {
		gameOver = true;
	}
	
	//make the sanke move.
	private class PaintThread implements Runnable {
		private boolean running = true;

		@Override
		public void run() {
			while (running) {
				repaint();
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		public void gameOver() {
			running = false;
		}
		
	}
	
	private class KeyMonitor extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			s.keyPressed(e);
		}
		
	}

}
