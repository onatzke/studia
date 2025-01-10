import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.ArrayList;


public class FileSavingTest {
    private General general1;
    private General general2;
    private Secretary sekretarz;
    private String folder_raportu;
    private final String raport = "raporty.txt";

    @Before
    public void setup() {
        general1 = new General("General 1", 1000);
        general2 = new General("General 2", 1000);
        sekretarz = new Secretary();
        general1.addObserver(sekretarz);
        general2.addObserver(sekretarz);
        folder_raportu = sekretarz.getReportsDirectory();
    }

    @Test
    public void testSingleFileCreation() {
        general1.buySoldiers(Rank.SZEREGOWY, 1);
        File reportFile = new File(folder_raportu + raport);
        assertTrue("Plik raportów powinien istnieć", reportFile.exists());
    }

    @Test
    public void testMultipleReportsInSingleFile() throws IOException {
        general1.buySoldiers(Rank.SZEREGOWY, 1);
        general1.makeActions(general1.getArmy().getSoldiers());
        general1.attack(general2);

        Path reportPath = Paths.get(folder_raportu + raport);
        List<String> lines = Files.readAllLines(reportPath);

        assertTrue("Plik powinien zawierać raport armii",
                lines.stream().anyMatch(line -> line.contains("Armia:")));
        assertTrue("Plik powinien zawierać raport akcji",
                lines.stream().anyMatch(line -> line.contains("Akcja:")));
        assertTrue("Plik powinien zawierać raport bitwy",
                lines.stream().anyMatch(line -> line.contains("Bitwa:")));
    }

    @Test
    public void testReportCategorization() throws IOException {
        general1.buySoldiers(Rank.SZEREGOWY, 1);
        general1.makeActions(general1.getArmy().getSoldiers());

        Path reportPath = Paths.get(folder_raportu + raport);
        List<String> lines = Files.readAllLines(reportPath);

        long recruitmentCount = lines.stream()
                .filter(line -> line.startsWith("Armia:"))
                .count();
        long actionCount = lines.stream()
                .filter(line -> line.startsWith("Akcja:"))
                .count();

        assertTrue("Powinien być co najmniej jeden raport armii", recruitmentCount > 0);
        assertTrue("Powinien być co najmniej jeden raport akcji", actionCount > 0);
    }

    @Test
    public void testFullReport() throws IOException {
        general1.buySoldiers(Rank.SZEREGOWY, 1);
        general1.attack(general2);

        sekretarz.saveFullReport();

        Path reportPath = Paths.get(folder_raportu + raport);
        String content = new String(Files.readAllBytes(reportPath));

        assertTrue("Pełny raport powinien zawierać nagłówek",
                content.contains(" PEŁNY RAPORT"));
        assertTrue("Pełny raport powinien zawierać sekcję bitew",
                content.contains("Bitwy:"));
        assertTrue("Pełny raport powinien zawierać sekcję armii",
                content.contains("Armia:"));
    }


    @Test
    public void testReportConsistency() throws IOException {
        general1.buySoldiers(Rank.SZEREGOWY, 1);
        general1.makeActions(general1.getArmy().getSoldiers());
        general1.attack(general2);

        Path reportPath = Paths.get(folder_raportu + raport);
        List<String> fileLines = Files.readAllLines(reportPath);

        List<String> memoryReports = new ArrayList<>();
        memoryReports.addAll(sekretarz.getArmyReports());
        memoryReports.addAll(sekretarz.getActionReports());
        memoryReports.addAll(sekretarz.getBattleReports());

        for (String memoryReport : memoryReports) {
            final String report = memoryReport;
            assertTrue("Każdy raport z pamięci powinien być w pliku",
                    fileLines.stream().anyMatch(line -> line.contains(report.substring(report.indexOf("]") + 1).trim())));
        }
    }
}