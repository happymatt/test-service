package com.jhqc.pxsj.tree;

import java.util.Arrays;

public class Main2 {

	public static void main(String[] args) throws Exception {
		int[] preSort = { 1, 2, 4, 7, 3, 5, 6, 8 };
		int[] miSort = new int[] { 4, 7, 2, 1, 5, 3, 8, 6 };
		BinaryNode root = startBuildTree(preSort, miSort);
		afterSort(root);
	}
	
	private static void afterSort(BinaryNode root) {
		if (root != null) {
			afterSort(root.leftNode);
			afterSort(root.rightNode);
			System.out.print(root.getValue() + ",");
		}
	}

	// 01递归生成树
	private static BinaryNode startBuildTree(int[] preSort, int[] miSort) throws Exception {
		// 异常判断
		if (preSort == null || miSort == null) {
			return null;
		}
		if (preSort.length != miSort.length) {
			throw new Exception("不满足条件的非法输入！");
		}

		BinaryNode root = null;
		for (int i = 0; i < miSort.length; i++) {
			if (miSort[i] == preSort[0]) {
				root = new BinaryNode(preSort[0]);
				System.out.println(preSort[0]);

				root.leftNode = startBuildTree(
						Arrays.copyOfRange(preSort, 1, i + 1),
						Arrays.copyOfRange(miSort, 0, i));
				root.rightNode = startBuildTree(
						Arrays.copyOfRange(preSort, i + 1, preSort.length),
						Arrays.copyOfRange(miSort, i + 1, miSort.length));
			}
		}

		return root;
	}
	
	public static class  BinaryNode {  
		private int value;
		private BinaryNode leftNode;
		private BinaryNode rightNode;

		BinaryNode(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public BinaryNode getLeftNode() {
			return leftNode;
		}

		public void setLeftNode(BinaryNode leftNode) {
			this.leftNode = leftNode;
		}

		public BinaryNode getRightNode() {
			return rightNode;
		}

		public void setRightNode(BinaryNode rightNode) {
			this.rightNode = rightNode;
		}

	}
	

}


