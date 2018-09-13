package com.tree;

import java.io.File;

import com.tree.util.FileTreeUtils;

/**
 * {@link FileTreeCallback} のデフォルト実装。<br>
 * {@link FileTree} の走査処理を基にファルダ構成を標準出力に表示する。
 *
 * <pre>
 * 【出力例】
 *
 * 📂 proj/
 *     📂 account/
 *         📄 Account.java
 *         📄 ATM.java
 *         📄 ATMCallback.java
 *     📂 guide/
 *         📄 Guidance.java
 *         📄 GuidanceCallback.java
 *         📄 GuidanceManager.java
 *     📂 main/
 *         📄 TextConsole.java
 * </pre>
 *
 * @author t.yoshida
 */
public class DefaultFileTreeCallback implements FileTreeCallback
{
	public DefaultFileTreeCallback()
	{

	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * 全てのフォルダ/ファイルを通知対象とする。
	 * </p>
	 */
	@Override
	public boolean isNotifiable(File target)
	{
		return true;
	}

	@Override
	public void onScanStarted(File root) { }

	@Override
	public void onDirDetected(File dir, int depth)
	{
		// フォルダ表示
		System.out.println(FileTreeUtils.indent(depth) + "📂 " + dir.getName() + "/");
	}

	@Override
	public void onFileDetected(File file, int depth)
	{
		// ファイル表示
		System.out.println(FileTreeUtils.indent(depth) + "📄 " + file.getName());
	}

	@Override
	public void onScanFinished(File root) { }
}
