package com.tree;

import java.io.File;
import java.util.Arrays;

import com.tree.util.FileTreeUtils;

/**
 * フォルダ走査を行う。
 *
 * @author t.yoshida
 */
public class FileTree
{
	// ルートフォルダ
	private File _root;

	// コールバック
	private FileTreeCallback _callback;

	/**
	 * FileTree を生成する。
	 *
	 * @param root ルートフォルダ（走査開始フォルダ）
	 */
	public FileTree(File root)
	{
		this(root, new DefaultFileTreeCallback());
	}

	/**
	 * FileTree を生成する。
	 *
	 * @param root ルートフォルダ（走査開始フォルダ）
	 * @param callback コールバック
	 */
	public FileTree(File root, FileTreeCallback callback)
	{
		if(!root.exists())
		{
			throw new IllegalArgumentException("File not Found: " + root.getAbsolutePath());
		}

		_root = root;
		_callback = callback;
	}

	/**
	 * ルートフォルダ以下の走査を行う。
	 */
	public void scan()
	{
		// 走査開始通知
		_callback.onScanStarted(_root);

		// 走査処理
		scan(_root);

		// 走査終了通知
		_callback.onScanFinished(_root);
	}

	/**
	 * 対象フォルダ/ファイル以下の走査を行う。
	 *
	 * @param target 対象フォルダ/ファイル
	 */
	private void scan(File target)
	{
		// ルートフォルダからの深さを取得
		int depth = depth(target);

		// 対象フォルダ/ファイルがコールバック対象か否かを取得
		boolean isNotifiable = _callback.isNotifiable(target);

		/*
		 * ターゲット種別処理
		 */
		if(target.isFile())
		{
			if(isNotifiable)
			{
				// ファイル検出通知
				_callback.onFileDetected(target, depth);
			}
		}
		else
		{
			if(isNotifiable)
			{
				// フォルダ検出通知
				_callback.onDirDetected(target, depth);
			}

			// 配下のフォルダ/ファイルの走査を続ける
			File[] children = target.listFiles();
			Arrays.sort(children, FileTreeUtils.DEFAULT_FILE_COMPARATOR);
			for(File child : children)
			{
				scan(child);
			}
		}
	}

	/**
	 * ルートフォルダからの深さを返す。
	 *
	 * @param target 対象フォルダ/ファイル
	 * @return 深さ（対象が走査開始フォルダの場合: 0）
	 */
	private int depth(File target)
	{
		int depth = 0;
		if(_root.equals(target))
		{
			return depth;
		}

		/*
		 * 対象フォルダ/ファイルからルートフォルダに向かってカウント
		 */
		depth++;
		while(true)
		{
			target = target.getParentFile();
			if(target == null || target.equals(_root))
			{
				break;
			}

			if(target.isDirectory())
			{
				depth++;
			}
		}

		return depth;
	}
}
