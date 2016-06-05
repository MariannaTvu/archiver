package ua.kiev.prog;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("/")
public class MyController {
    private Map<String, byte[]> files = new HashMap<String, byte[]>();

    @RequestMapping("/")
    public String onIndex() {
        return "index";
    }

    @RequestMapping("/add")
    public String groupFiles(Model model, @RequestParam MultipartFile file1,
                             @RequestParam MultipartFile file2, @RequestParam MultipartFile file3) {
        if (file1.isEmpty() || file2.isEmpty() || file3.isEmpty()) {
            throw new FileErrorException();
        }
        try {
            files.put(file1.getOriginalFilename(), file1.getBytes());
            files.put(file2.getOriginalFilename(), file2.getBytes());
            files.put(file3.getOriginalFilename(), file3.getBytes());
        } catch (IOException e) {
            throw new FileErrorException();
        }
        model.addAttribute("file_name", files.keySet());
        return "result";
    }

    @RequestMapping("/clear")
    public String index() {
        files.clear();
        return "index";
    }

    @RequestMapping(value = "/zip", produces = "application/zip")
    public ResponseEntity<byte[]> getZippedFiles(HttpServletResponse response) throws IOException {
        Map<String, byte[]> zipFiles = files;
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Content-Disposition", "attachment; filename=\"files.zip\"");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);

        for (Map.Entry<String, byte[]> entry : zipFiles.entrySet()) {
            String name = entry.getKey();
            byte[] file = entry.getValue();
            zipFile(file, name, zipOutputStream);
        }

        zipOutputStream.closeEntry();

        if (zipOutputStream != null) {
            zipOutputStream.finish();
            zipOutputStream.flush();
            IOUtils.closeQuietly(zipOutputStream);
        }
        IOUtils.closeQuietly(bufferedOutputStream);
        IOUtils.closeQuietly(byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return new ResponseEntity<byte[]>(bytes, HttpStatus.OK);
    }

    private void zipFile(byte[] file, String name, ZipOutputStream zipOutputStream) throws IOException {
        zipOutputStream.putNextEntry(new ZipEntry(name));
        ByteArrayInputStream fileInputStream = new ByteArrayInputStream(file);
        IOUtils.copy(fileInputStream, zipOutputStream);
        fileInputStream.close();
    }
}
