package com.wyjsusan.snake;

import java.awt.*;
import java.awt.event.KeyEvent;


public class Snake {
	private Node head = null;
	private Node tail = null;
	private int size = 0;
	
	private Node n = new Node(20, 30, Dir.L);
	private Yard yard;

	public Snake(Yard y) {
		head = n;
		tail = n;
		size = 1;
		this.yard = y;
	}
	
	public void addToTail() {
		Node node = null;
		switch(tail.dir) {
		case L:
			node = new Node(tail.row, tail.col + 1, tail.dir);
			break;
		case U:
			node = new Node(tail.row + 1, tail.col, tail.dir);
			break;
		case R:
			node = new Node(tail.row, tail.col - 1, tail.dir);
			break;
		case D:
			node = new Node(tail.row - 1, tail.col, tail.dir);
			break;
		}
		tail.next = node;
		node.prev = tail;
		tail = node;
		size++;
	}
	
	public void addToHead() {
		Node node = null;
		switch(head.dir) {
		case L:
			node = new Node(head.row, head.col - 1, head.dir);
			break;
		case U:
			node = new Node(head.row - 1, head.col, head.dir);
			break;
		case R:
			node = new Node(head.row, head.col + 1, head.dir);
			break;
		case D:
			node = new Node(head.row + 1, head.col, head.dir);
			break;
		}
		node.next = head;
		head.prev = node;
		head = node;
		size++;
	}
	
	public void draw(Graphics g) {
		if (size <= 0) {
			return;
		}
		move();
		for (Node n = head; n != null; n = n.next) {
			n.draw(g);
		}
		
	}

	private void move() {
		//for snake move: 1. add a node to the head, and delete the last node;
		addToHead();
		deleteFromTail();
		checkDead();
	}

	private void checkDead() {
		if (head.row < 2 || head.col < 0 || head.row > Yard.ROWS || head.col > Yard.COLS) {
			yard.stop();
		}
		
		for (Node n = head.next; n != null; n = n.next) {
			if (head.row == n.row && head.col == n.col) {
				yard.stop();
			}
		}
		
	}

	private void deleteFromTail() {
		if (size <= 0) {
			return;
		}
		tail = tail.prev;
		tail.next = null;
		
	}
	
	public void eat(Egg e) {
		if (this.getRect().intersects(e.getRect())) {
			e.reAppear();
			this.addToHead();
			yard.setScore(yard.getScore() + 5);
		}
	}
	
	//detect the collision
	private Rectangle getRect() {
		return new Rectangle(Yard.BLOCK_SIZE * head.col, Yard.BLOCK_SIZE * head.row, head.w, head.h);
		
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_LEFT:
			if (head.dir != Dir.R) {
				head.dir = Dir.L;
			}
			break;
		case KeyEvent.VK_UP:
			if (head.dir != Dir.D) {
				head.dir = Dir.U;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (head.dir != Dir.L) {
				head.dir = Dir.R;
			}
			break;
		case KeyEvent.VK_DOWN:
			if (head.dir != Dir.U) {
				head.dir = Dir.D;
			}
			break;
		}
	}
	
	

}

class Node{
	int w = Yard.BLOCK_SIZE;
	int h = Yard.BLOCK_SIZE;
	int row, col;
	Dir dir = Dir.L;
	Node next = null;
	Node prev = null;
	
	Node (int row, int col, Dir dir) {
		this.row = row;
		this.col = col;
		this.dir = dir;
	}
	
	void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.fillRect(Yard.BLOCK_SIZE * col, Yard.BLOCK_SIZE * row, w, h);
		g.setColor(c);
	}
}
