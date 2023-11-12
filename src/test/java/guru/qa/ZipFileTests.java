package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipFile;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class ZipFileTests {
    ClassLoader classLoader = ZipFileTests.class.getClassLoader();

    @Test
    void pdfTest() throws Exception {
        InputStream pdfFileStream = getFile("desktop.zip", "tinkoff.pdf");
        PDF pdf = new PDF(pdfFileStream);
        assertThat(pdf.text).contains("Tinkoff Black");
        closeInputStream(pdfFileStream);
    }

    @Test
    void xlsxFileTest() throws Exception {
        InputStream xlsFileStream = getFile("desktop.zip", "hospitals.xlsx");
        XLS xls = new XLS(xlsFileStream);
        assertThat(
                xls.excel
                        .getSheetAt(0)
                        .getRow(1)
                        .getCell(3)
                        .getStringCellValue()).contains("420073");
        closeInputStream(xlsFileStream);
    }

    @Test
    void csvTest() throws Exception {
        InputStream csvFileStream = getFile("desktop.zip", "people.csv");
        CSVReader csvReader = new CSVReader(new InputStreamReader(csvFileStream, UTF_8));
        List<String[]> csv = csvReader.readAll();
        assertThat(csv).contains(
                new String[]{"Index","User Id","First Name","Last Name","Sex","Email","Phone","Date of birth","Job Title"});
        closeInputStream(csvFileStream);
    }

    private InputStream getFile(String archiveName, String fileName) throws Exception {
        URL url = classLoader.getResource(archiveName);
        File file = new File(url.toURI());
        ZipFile zipFile = new ZipFile(file);
        return zipFile.getInputStream(zipFile.getEntry(fileName));
    }

    private void closeInputStream(InputStream inputStream) throws IOException {
        if (inputStream != null)
            inputStream.close();
    }

}