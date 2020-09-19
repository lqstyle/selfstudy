package com.example.demo1.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * FileUtils$
 *
 * @author shuai
 * @date 2020/4/8$
 */
@Slf4j
public class FileUtils {

  public static final String SUFFIX = ".csv";

  private FileUtils() {
  }

  public static void mkdir(String path) {
    if (StringUtils.isBlank(path)) {
      log.error("参数为空");
      return;
    }
    Path paths = Paths.get(path);
    try {
      Files.createDirectories(paths);
    } catch (IOException e) {
      log.error(e.getMessage());
    }

  }

  public static class FindJavaVisitor extends SimpleFileVisitor<Path> {

    @Getter
    private List<Path> result;

    public FindJavaVisitor(List<Path> result) {
      this.result = result;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
      if (file.toString().endsWith(SUFFIX)) {
        result.add(file.getFileName());
      }
      return FileVisitResult.CONTINUE;
    }
  }

  public static void getFiles(List<File> fileList, String path) {
    try {
      File file = new File(path);
      if (file.isDirectory()) {
        File[] files = file.listFiles();
        if (null != files) {
          for (File fileIndex : files) {
            //如果这个文件是目录，则进行递归搜索
            if (fileIndex.isDirectory()) {
              getFiles(fileList, fileIndex.getPath());
            } else {
              //如果文件是普通文件，则将文件句柄放入集合中
              fileList.add(fileIndex);
            }
          }
        }
      }
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }
}