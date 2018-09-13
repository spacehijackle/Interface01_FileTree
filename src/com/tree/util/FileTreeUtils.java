package com.tree.util;

import java.io.File;
import java.util.Comparator;

/**
 * ユーティリティクラス
 *
 * @author t.yoshida
 */
public class FileTreeUtils
{
	/**
	 * フォルダ、ファイルのデフォルト並び替え
	 * <pre>
	 * {@link File#listFiles()} で返されるフォルダ、ファイルの順番を並び替える。
	 * 優先順位：フォルダ、ファイルの順（フォルダ同士、ファイル同士の場合、アルファベット順）
	 * </pre>
	 */
	public static final Comparator<File> DEFAULT_FILE_COMPARATOR;

	static
	{
		DEFAULT_FILE_COMPARATOR = new Comparator<File>()
		{
			@Override
			public int compare(File comp1, File comp2)
			{
				/*
				 * フォルダ、ファイルの優先順位決定
				 * 優先度：フォルダ＝1, ファイル＝2
				 */
				int priority1 = (comp1.isDirectory() ? 1 : 2);
				int priority2 = (comp2.isDirectory() ? 1 : 2);

				int compared = priority1 - priority2;
				if(compared != 0)
				{
					// 比較両者がフォルダとファイルの場合、差はゼロ以外となり、
					// 優先順位が決定 → 値の返却
					return compared;
				}

				/*
				 * フォルダ or ファイル同士の比較（アルファベット順）
				 */
				compared = comp1.getName().compareTo(comp2.getName());

				return compared;
			}
		};
	}

	/**
	 * 指定された深さに応じたインデントを返す。
	 *
	 * @param depth 深さ
	 * @return インデント
	 */
	public static String indent(int depth)
	{
		String indent = "";
		for(int i=0; i<depth; i++)
		{
			//indent += "\t";
			indent += "    ";
		}

		return indent;
	}
}
