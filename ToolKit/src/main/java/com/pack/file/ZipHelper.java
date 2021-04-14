package com.pack.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * zip工具助手
 * 
 * @author ltl
 *
 */
public class ZipHelper {

	private static ZipHelper zh = null;

	public static ZipHelper init() {
		if (zh == null) {
			synchronized (ZipHelper.class) {
				if (zh == null) {
					zh = new ZipHelper();
				}
			}
		}
		return zh;
	}

	private ZipHelper() {
	}

	private static final int BUFFER_SIZE = 2 * 1024;
	/**
	 * 是否保留原来的目录结构 true: 保留目录结构; false: 所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
	 */
	private static final boolean KeepDirStructure = true;

	/**
	 * zip压缩
	 * 
	 * @param srcDir       源路径
	 * @param outPathFile  结果路径
	 * @param isDelSrcFile 是否删除源文件：压缩前文件
	 * @throws Exception
	 */
	public void zip(String srcDir, String outPathFile, boolean isDelSrcFile) throws Exception {
		FileOutputStream out = null;
		ZipOutputStream zos = null;
		try {
			out = new FileOutputStream(new File(outPathFile));
			zos = new ZipOutputStream(out);
			File sourceFile = new File(srcDir);
			if (!sourceFile.exists()) {
				throw new Exception("需压缩文件或者文件夹不存在");
			}
			compress(sourceFile, zos, sourceFile.getName());
			if (isDelSrcFile) {
				delDir(srcDir);
			}
		} catch (Exception e) {
			throw new Exception("zip error from ZipUtils");
		} finally {
			try {
				if (zos != null) {
					zos.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 递归压缩方法
	 * 
	 * @param sourceFile 源文件
	 * @param zos        zip输出流
	 * @param name       压缩后的名称
	 */
	private void compress(File sourceFile, ZipOutputStream zos, String name) throws Exception {
		byte[] buf = new byte[BUFFER_SIZE];
		if (sourceFile.isFile()) {
			zos.putNextEntry(new ZipEntry(name));
			int len;
			FileInputStream in = new FileInputStream(sourceFile);
			while ((len = in.read(buf)) != -1) {
				zos.write(buf, 0, len);
			}
			zos.closeEntry();
			in.close();
		} else {
			File[] listFiles = sourceFile.listFiles();
			if (listFiles == null || listFiles.length == 0) {
				if (KeepDirStructure) {
					zos.putNextEntry(new ZipEntry(name + Matcher.quoteReplacement(File.separator)));
					zos.closeEntry();
				}
			} else {
				for (File file : listFiles) {
					if (KeepDirStructure) {
						compress(file, zos, name + Matcher.quoteReplacement(File.separator) + file.getName());
					} else {
						compress(file, zos, file.getName());
					}
				}
			}
		}
	}

	/**
	 * 解压到指定目录
	 * 
	 * @param zipPath 压缩包路径
	 * @param descDir 解压存放路径
	 * @throws IOException
	 */
	public void unzip(String zipPath, String descDir) throws IOException {
		ZipFile zip = null;
		InputStream in = null;
		OutputStream out = null;
		try {
			File zipFile = new File(zipPath);
			if (!zipFile.exists()) {
				throw new IOException("需解压文件不存在.");
			}
			File pathFile = new File(descDir);
			if (!pathFile.exists()) {
				pathFile.mkdirs();
			}
			zip = new ZipFile(zipFile, Charset.forName("GBK"));
			for (Enumeration<?> entries = zip.entries(); entries.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String zipEntryName = entry.getName();
				in = zip.getInputStream(entry);
				String outPath = FileUtility.init()
						.StandardPath((descDir + Matcher.quoteReplacement(File.separator) + zipEntryName));
				// 判断路径是否存在,不存在则创建文件路径
				File file = new File(outPath.substring(0, outPath.lastIndexOf(File.separator)));
				if (!file.exists()) {
					file.mkdirs();
				}
				// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
				if (new File(outPath).isDirectory()) {
					continue;
				}
				// 输出文件路径信息
				out = new FileOutputStream(outPath);
				try {
					byte[] buf1 = new byte[1024];
					int len;
					while ((len = in.read(buf1)) > 0) {
						out.write(buf1, 0, len);
					}
				} finally {
					try {
						in.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					try {
						out.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			throw new IOException(e);
		} finally {
			try {
				zip.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	/**
	 * 删除文件夹
	 * 
	 * @param dirPath
	 * @throws IOException
	 */
	private void delDir(String dirPath) throws IOException {
		try {
			File dirFile = new File(dirPath);
			if (!dirFile.exists()) {
				return;
			}
			if (dirFile.isFile()) {
				dirFile.delete();
				return;
			}
			File[] files = dirFile.listFiles();
			if (files == null) {
				return;
			}
			for (int i = 0; i < files.length; i++) {
				delDir(files[i].toString());
			}
			dirFile.delete();
		} catch (Exception e) {
			throw new IOException("删除文件异常.");
		}
	}
}
