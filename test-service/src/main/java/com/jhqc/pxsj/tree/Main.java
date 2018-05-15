package com.jhqc.pxsj.tree;

public class Main {
	
	
	/**
	 * 前序遍历
	 */
	private static String temp1 = "FDXEAG"; 
	
	/**
	 * 中序遍历
	 */
	private static String temp2 = "XDEFAG";
	
	
	/**
	 * 根据一个二叉树的前序和中序遍历输出其后序遍历
	 * @param args
	 */
	public static void main(String[] args) {
		//先根据前序和后序把树构造出来？
		BinaryTree root = new BinaryTree();
		root.setData(temp1.substring(0,1));
		
	}

}

class BinaryTree {
	private String data;
	private BinaryTree left;
	private BinaryTree right;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public BinaryTree getLeft() {
		return left;
	}
	public void setLeft(BinaryTree left) {
		this.left = left;
	}
	public BinaryTree getRight() {
		return right;
	}
	public void setRight(BinaryTree right) {
		this.right = right;
	}
}
