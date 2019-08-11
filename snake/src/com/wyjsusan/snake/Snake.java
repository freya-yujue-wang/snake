package com.wyjsusan.snake;

import java.awt.*;

class Node{
	int w = Yard.BLOCK_SIZE;
	int h = Yard.BLOCK_SIZE;
	int row, col;
	Dir dir = Dir.L;
	Node next = null;
	
	Node (int row, int col, Dir dir) {
		this.row = row;
		this.col = col;
		this.dir = dir;
	}
	
	void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.fillRect(Yard.BLOCK_SIZE * row, Yard.BLOCK_SIZE * col, w, h);
		g.setColor(c);
	}
}
public class Snake {
	private Node head = null;
	private Node tail = null;
	private int size = 0;
	
	private Node n = new Node(20, 20, Dir.L);

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
		case U:
			node = new Node(tail.row + 1, tail.col, tail.dir);
		case R:
			node = new Node(tail.row, tail.col - 1, tail.dir);
		case D:
			node = new Node(tail.row - 1, tail.col, tail.dir);
		}
		tail.next = node;
		tail = node;
		size++;
	}
	
	public void addToHead() {
		Node node = null;
		switch(head.dir) {
		case L:
			node = new Node(tail.row, tail.col - 1, tail.dir);
		case U:
			node = new Node(tail.row - 1, tail.col, tail.dir);
		case R:
			node = new Node(tail.row, tail.col + 1, tail.dir);
		case D:
			node = new Node(tail.row + 1, tail.col, tail.dir);
		}
		node.next = head;
		head = node;
		size++;
	}
	
	public void draw(Graphics g) {
		if (size <= 0) {
			return;
		}
		for (Node n = head; n!= null; n = n.next) {
			n.draw(g);
		}
	}
	

}
