import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class Secretary implements Observer {
    private List<String> battleReports = new ArrayList<>();
    private List<String> actionReports = new ArrayList<>();
    private List<String> armyReports = new ArrayList<>();
    private static final String FOLDER_RAPORTOW = "raporty/";
    private static final String PLIK_RAPORTOW = "raporty.txt";

    public Secretary() {
        createReportsDirectory();
    }

    private void createReportsDirectory() {
        File directory = new File(FOLDER_RAPORTOW);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    @Override
    public void update(String message) {
        String timestamp = String.format("[%s]", new Date());
        String formattedMessage = timestamp + " " + message;

        if (message.toLowerCase().contains("bitwa") || message.toLowerCase().contains("pokonany") ||
                message.toLowerCase().contains("wygrana") || message.toLowerCase().contains("remis")) {
            battleReports.add(formattedMessage);
            saveReportToFile("Bitwa: " + formattedMessage);
        } else if (message.toLowerCase().contains("akcje") || message.toLowerCase().contains("doświadczenie") ||
                message.toLowerCase().contains("awansował")) {
            actionReports.add(formattedMessage);
            saveReportToFile("Akcja: " + formattedMessage);
        } else if (message.toLowerCase().contains("kupowanie") || message.toLowerCase().contains("żołnierzy")) {
            armyReports.add(formattedMessage);
            saveReportToFile("Armia: " + formattedMessage);
        }

        System.out.println(formattedMessage);
    }

    private void saveReportToFile(String message) {
        try {
            File file = new File(FOLDER_RAPORTOW + PLIK_RAPORTOW);
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("Błąd zapisu: " + e.getMessage());
        }
    }

    public void saveFullReport() {
        String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
        String fullReport = generateFullReport();

        try {
            File file = new File(FOLDER_RAPORTOW + PLIK_RAPORTOW);
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write("\n PEŁNY RAPORT " + timestamp + "\n");
            bufferedWriter.write(fullReport);
            bufferedWriter.write(" \n\n");
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("Błąd zapisu: " + e.getMessage());
        }
    }

    public String generateFullReport() {
        StringBuilder report = new StringBuilder();

        report.append("Bitwy:\n");
        battleReports.forEach(r -> report.append("- ").append(r).append("\n"));

        report.append("\nAkcje:\n");
        actionReports.forEach(r -> report.append("- ").append(r).append("\n"));

        report.append("\nArmia:\n");
        armyReports.forEach(r -> report.append("- ").append(r).append("\n"));

        return report.toString();
    }

    public List<String> getBattleReports() {
        return new ArrayList<>(battleReports);
    }

    public List<String> getActionReports() {
        return new ArrayList<>(actionReports);
    }

    public List<String> getArmyReports() {
        return new ArrayList<>(armyReports);
    }

    public void clearReports() {
        battleReports.clear();
        actionReports.clear();
        armyReports.clear();
    }

    public String getReportsDirectory() {
        return FOLDER_RAPORTOW;
    }
}