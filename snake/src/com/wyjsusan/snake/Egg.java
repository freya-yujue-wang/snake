package com.wyjsusan.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Egg {
	int row, col;
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	int w = Yard.BLOCK_SIZE;
	int h = Yard.BLOCK_SIZE;
	
	private static Random r = new Random();
	
	public Egg(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public Egg() {
		this(r.nextInt(Yard.ROWS - 3) + 3, r.nextInt(Yard.COLS - 2) + 2);
	}
	
	public void reAppear() {
		this.row = r.nextInt(Yard.ROWS - 3) + 3;
		this.col = r.nextInt(Yard.COLS - 2) + 2;
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GREEN);
		g.fillOval(Yard.BLOCK_SIZE * col, Yard.BLOCK_SIZE * row, w, h);
		g.setColor(c);
	}
	
	//detect the collision
	public Rectangle getRect() {
		return new Rectangle(Yard.BLOCK_SIZE * col, Yard.BLOCK_SIZE * row, w, h);
		
	}

	
	
	

}
