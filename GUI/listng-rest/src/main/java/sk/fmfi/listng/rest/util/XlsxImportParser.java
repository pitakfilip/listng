package sk.fmfi.listng.rest.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import sk.fmfi.listng.rest.dto.ImportDto;
import sk.fmfi.listng.user.dto.UserDto;

public class XlsxImportParser {

    private final static String BASE_PATH = "";
    
    public static Map<Integer, String> getHeaderLabels(MultipartFile file, int sheetNumber, int rowNumber) {
        Path path = Paths.get(BASE_PATH).resolve(file.getOriginalFilename());
        try {
            delete(path);
            Files.copy(file.getInputStream(), path);
        } catch (Exception e) {
            delete(path);
            return null;
        }
        
        Map<Integer, String> labels = new HashMap<>();
        try (Workbook workbook = new XSSFWorkbook(path.toFile())){
            Sheet sheet = workbook.getSheetAt(sheetNumber - 1);
            Row row = sheet.getRow(rowNumber - 1);
            int i = 0;
            for (Cell cell : row) {
                labels.put(i++, cell.toString());
            }
            
        } catch (Exception e) {
            labels = null;
        }
        
        delete(path);
        return labels;
    }
    
    public static List<UserDto> parseUsers(MultipartFile file, ImportDto config) {
        Path path = Paths.get(BASE_PATH).resolve(file.getOriginalFilename());
        try {
            delete(path);
            Files.copy(file.getInputStream(), path);
        } catch (Exception e) {
            delete(path);
            return null;
        }
        
        List<UserDto> users = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(path.toFile())){
            Sheet sheet = workbook.getSheetAt(config.sheet - 1);
            
            int rowNum = 0;
            for (Row row : sheet) {
                if (config.rowFrom <= rowNum && rowNum <= config.rowTo) {
                    UserDto user = new UserDto();
                    if (config.fullName != null) {
                        user.name = row.getCell(config.fullName).toString();
                    } else {
                        user.name = row.getCell(config.name).toString() + " " + row.getCell(config.surname).toString();
                    }
                    user.email = row.getCell(config.email).toString();
                    users.add(user);
                }
                rowNum++;
            }
        } catch (Exception e) {
            users = null;
        }
        delete(path);
        return users;
    }

    private static void delete(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (Exception e) {
            try {
                FileUtils.forceDelete(path.toFile());
            } catch (IOException ex) {
                System.err.println("Couldnt delete file caused by exception: " + ex.getMessage());
                ex.printStackTrace();
            }
            
        }
    }
}
