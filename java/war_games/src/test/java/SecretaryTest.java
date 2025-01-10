import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class SecretaryTest {
    private General general1;
    private General general2;
    private Secretary sekretarz;

    @Before
    public void setup() {
        general1 = new General("General 1", 1000);
        general2 = new General("General 2", 1000);
        sekretarz = new Secretary();
        general1.addObserver(sekretarz);
        general2.addObserver(sekretarz);
    }

    @Test
    public void testReportGeneration() {
        general1.buySoldiers(Rank.SZEREGOWY, 1);
        general1.makeActions(general1.getArmy().getSoldiers());

        String fullReport = sekretarz.generateFullReport();
        assertTrue("Raport powinien zawierać informacje o żołnierzach", fullReport.contains("żołnierz"));
        assertTrue("Raport powinien zawierać informacje o akcjach", fullReport.contains("akcje"));
    }

    @Test
    public void testBattleReports() {
        general1.buySoldiers(Rank.KAPITAN, 2);
        general2.buySoldiers(Rank.SZEREGOWY, 1);

        general1.attack(general2);

        List<String> reports = sekretarz.getBattleReports();
        assertFalse("Powinny istnieć raporty", reports.isEmpty());
        assertTrue("Raport powinien zawierać informację o wygranej",
                reports.stream().anyMatch(r -> r.contains("Wygrana bitwa") || r.contains("pokonany")));
    }

    @Test
    public void testAddObserver() {
        Secretary newSecretary = new Secretary();
        general1.addObserver(newSecretary);

        general1.buySoldiers(Rank.SZEREGOWY, 1);

        assertFalse(newSecretary.getArmyReports().isEmpty());
    }

    @Test
    public void testSecretaryReporting() {
        general1.buySoldiers(Rank.KAPITAN, 2);
        general2.buySoldiers(Rank.SZEREGOWY, 1);
        general1.attack(general2);

        String report = sekretarz.generateFullReport();

        assertTrue("Raport powinien zawierać informacje o bitwie",
                report.contains("bitwa") || report.contains("Wygrana"));
    }
}