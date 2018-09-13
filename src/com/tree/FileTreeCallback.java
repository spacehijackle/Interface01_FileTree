package com.tree;

import java.io.File;


/**
 * {@link FileTree} のコールバック定義
 *
 * @author t.yoshida
 */
public interface FileTreeCallback
{
	/**
	 * 対象フォルダ/ファイルをコールバックの通知対象とするか否かを返す。
	 *
	 * @param target 対象フォルダ/ファイル
	 * @return true: 通知対象
	 */
	boolean isNotifiable(File target);

	/**
	 * 走査開始通知
	 *
	 * @param root ルートフォルダ（走査開始フォルダ/ファイル）
	 */
	void onScanStarted(File root);

	/**
	 * フォルダ検出通知
	 *
	 * @param dir 検出フォルダ
	 * @param depth （ルートからの）深さ
	 */
	void onDirDetected(File dir, int depth);

	/**
	 * ファイル検出通知
	 *
	 * @param file 検出ファイル
	 * @param depth （ルートからの）深さ
	 */
	void onFileDetected(File file, int depth);

	/**
	 * 走査終了通知
	 *
	 * @param root ルートフォルダ（走査開始フォルダ/ファイル）
	 */
	void onScanFinished(File root);
}
