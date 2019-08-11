package com.wyjsusan.snake;

import java.awt.*;
import java.awt.event.KeyEvent;


public class Snake {
	private Node head = null;
	private Node tail = null;
	private int size = 0;
	
	private Node n = new Node(20, 30, Dir.L);

	public Snake() {
		head = n;
		tail = n;
		size = 1;
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
			node = new Node(tail.row, tail.col - 1, tail.dir);
			break;
		case U:
			node = new Node(tail.row - 1, tail.col, tail.dir);
			break;
		case R:
			node = new Node(tail.row, tail.col + 1, tail.dir);
			break;
		case D:
			node = new Node(tail.row + 1, tail.col, tail.dir);
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
			head.dir = Dir.L;
			break;
		case KeyEvent.VK_UP:
			head.dir = Dir.U;
			break;
		case KeyEvent.VK_RIGHT:
			head.dir = Dir.R;
			break;
		case KeyEvent.VK_DOWN:
			head.dir = Dir.D;
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
