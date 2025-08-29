package versions;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileInputStream;
import java.io.IOException;

class CountLines {
    public static void main(String[] args) throws IOException {
        try (var doc = new XWPFDocument(new FileInputStream("/Users/jn93pw/Downloads/Leetcode solutions.docx"))) {
            String text = doc.getParagraphs().stream()
                    .map(XWPFParagraph::getText)
                    .reduce("", (a, b) -> a + "\n" + b);

            long count = text.lines()
                    .filter(line -> !line.isBlank())
                    .count();

            System.out.println("Non-empty lines: " + count);
        }
    }
}
