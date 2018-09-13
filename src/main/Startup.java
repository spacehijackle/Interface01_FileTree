package main;

import java.io.File;

import com.tree.FileTree;

/**
 * フォルダ構成の走査呼び出し処理
 *
 * @author t.yoshida
 */
public class Startup
{
	public static void main(String[] args)
	{
		// 走査開始フォルダ
		File root = new File("../FileTree");

		// フォルダ構成の走査
		FileTree tree = new FileTree(root);
		tree.scan();
	}
}
