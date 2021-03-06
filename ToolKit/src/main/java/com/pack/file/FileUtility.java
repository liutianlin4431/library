package com.pack.file;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;

import com.pack.console.LocalExecute;
import com.pack.str.StrUtility;

public class FileUtility {
	private static FileUtility fu = null;

	public static FileUtility init() {
		if (fu == null) {
			synchronized (FileUtility.class) {
				if (fu == null) {
					fu = new FileUtility();
				}
			}
		}
		return fu;
	}

	private FileUtility() {
	}

	private final String os_name = System.getProperty("os.name");

	/**
	 * 规范路径斜杠（全部为“/”，兼容Linxu路径）
	 * 
	 * @param path 地址
	 * @return
	 */
	public String StandardPath(String path) {
		path = path.replaceAll("\\\\{1,}", Matcher.quoteReplacement(File.separator));
		path = path.replaceAll("/{1,}", Matcher.quoteReplacement(File.separator));
		return path;
	}

	/**
	 * 删除文件
	 * 
	 * @param path 地址
	 */
	public void delPath(String path) {
		if (path != null) {
			if (StrUtility.init().Contains(os_name, "Windows")) {
				File file = new File(path);
				if (file.isDirectory()) {
					File[] files = file.listFiles();
					for (File son : files) {
						if (son.isDirectory()) {
							delPath(son.getAbsolutePath());
							son.delete();
						} else {
							son.delete();
						}
					}
					file.delete();
				} else {
					file.delete();
				}
			} else {
				LocalExecute.init().runCommand("rm -rf " + path);
			}
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param response HttpServletResponse
	 * @param filePath 文件存放地址
	 */
	public void download(HttpServletResponse response, String filePath) {

		FileInputStream fis = null;
		OutputStream os = null;
		try {
			// path是指欲下载的文件的路径。
			File file = new File(filePath);
			if (!file.exists() && !file.isDirectory()) {
				System.out.println("//不存在");
				file.mkdir();
			}

			// 取得文件名。
			String filename = file.getName();
			fis = null;
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename=" + new String(filename.getBytes()));
			response.setHeader("Content-Length", file.length() + "");
			fis = new FileInputStream(filePath);
			os = response.getOutputStream();
			byte[] buffer = new byte[2048];
			int count = 0;
			while ((count = fis.read(buffer)) != -1) {
				if (count != 0) {
					os.write(buffer, 0, count);
				} else {
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
				if (os != null) {
					os.flush();
					os.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	/**
	 * 创建压缩包
	 * 
	 * @param sourcePath 源路径
	 * @param zipPath    压缩路径
	 */
	public void createZip(String sourcePath, String zipPath) {
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			fos = new FileOutputStream(zipPath);
			zos = new ZipOutputStream(fos);
			writeZip(new File(sourcePath), "", zos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (zos != null) {
					zos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 重载createZip 多文件下载
	 * 
	 * @param plist   多个文件路径
	 * @param zipPath 压缩路径
	 */
	public void createZip(List<String> plist, String zipPath) {
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			fos = new FileOutputStream(zipPath);
			zos = new ZipOutputStream(fos);
			for (String sourcePath : plist) {
				writeZip(new File(sourcePath), "", zos);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (zos != null) {
					zos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public void writeZip(File file, String parentPath, ZipOutputStream zos) {
		if (file.exists()) {
			if (file.isDirectory()) {// 处理文件夹
				parentPath += file.getName() + File.separator;
				File[] files = file.listFiles();
				if (files.length != 0) {
					for (File f : files) {
						writeZip(f, parentPath, zos);
					}
				} else { // 空目录则创建当前目录
					try {
						zos.putNextEntry(new ZipEntry(parentPath));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(file);
					ZipEntry ze = new ZipEntry(parentPath + file.getName());
					zos.putNextEntry(ze);
					byte[] content = new byte[1024];
					int len;
					while ((len = fis.read(content)) != -1) {
						zos.write(content, 0, len);
						zos.flush();
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (fis != null) {
							fis.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 文件转byte[]
	 * 
	 * @param filePath 文件地址
	 * @return
	 */
	public byte[] toByteArray(String filePath) {
		File f = new File(filePath);
		if (!f.exists()) {
			return null;
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(f));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bos.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	/**
	 * 判断是否是二进制文件
	 * 
	 * @param path 地址
	 * @return
	 */
	public boolean isBinary(String path) {
		File file = new File(path);
		boolean isBinary = false;
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(file);
			long len = file.length();
			for (int j = 0; j < (int) len; j++) {
				int t = fin.read();
				if (t < 32 && t != 9 && t != 10 && t != 13) {
					isBinary = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fin != null) {
				try {
					fin.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return isBinary;
	}

	/**
	 * 获取当前文件夹下的内容
	 * 
	 * @param Path
	 * @return
	 */
	public List<Map<String, String>> findFolder(String Path) {
		File folder = new File(Path);
		File[] fileArray = folder.listFiles();
		List<Map<String, String>> fL = new ArrayList<Map<String, String>>();
		if (fileArray != null) {
			for (File file : fileArray) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", UUID.randomUUID().toString());
				map.put("text", file.getName());
				map.put("path", file.getAbsolutePath());
				if (file.isDirectory()) {
					// 文件夹
					map.put("state", "closed");
				} else {
					// 文件
					map.put("state", null);
				}
				fL.add(map);
			}
		}
		return fL;
	}

	/**
	 * 获取类资源目录下的文件内容
	 * 
	 * @param path
	 * @return
	 */
	public String ClassPathResourceFile(String path) {
		StringBuilder sb = new StringBuilder();
		// 开始解析配置文件信息
		ClassPathResource resource = new ClassPathResource(path);
		try {
			InputStream is = resource.getInputStream();
			byte[] b = new byte[is.available()];
			while (is.read(b) != -1) {
				sb.append(new String(b));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
