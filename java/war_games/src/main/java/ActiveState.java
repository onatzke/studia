import java.util.*;

public class ActiveState implements State {
    @Override
    public void Actions(General general, List<Soldier> participants) {
        int totalCost = participants.stream()
                .mapToInt(s -> s.getRank().getValue())
                .sum();

        if (general.getGold() >= totalCost) {
            general.setGold(general.getGold() - totalCost);
            for (Soldier soldier : participants) {
                soldier.gainExp();
            }
            general.informObserver("Przeprowadzono akcje dla " + participants.size() + " żołnierzy");
        } else {
            general.informObserver("Niewystarczająca ilość złota");
        }
    }

    public void Attack(General attacker, General defender) {
        int attackerPower = attacker.getArmy().getFullPower();
        int defenderPower = defender.getArmy().getFullPower();

        if (attackerPower > defenderPower) {
            BattleOutcome(attacker, defender, true);
            attacker.informObserver("Wygrana bitwa z " + defender.getName());
            checkDefeat(defender);
        } else if (defenderPower > attackerPower) {
            BattleOutcome(defender, attacker, true);
            defender.informObserver("Wygrana bitwa z " + attacker.getName());
            checkDefeat(attacker);
        } else {
            BattleDraw(attacker, defender);
            attacker.informObserver("Remis w bitwie");
            defender.informObserver("Remis w bitwie");
        }

        checkBankruptcy(attacker);
        checkBankruptcy(defender);
    }

    private void checkDefeat(General general) {
        if (general.getArmy().getFullPower() == 0) {
            general.setState(new DefeatedState());
            general.informObserver("Generał został pokonany");
        }
    }

    private void checkBankruptcy(General general) {
        if (general.getGold() < 100) {
            general.setState(new BankruptState());
            general.informObserver("Generał zbankrutował");
        }
    }

    public void BattleOutcome(General winner, General loser, boolean transferGold) {
        if (transferGold) {
            int goldTransfer = loser.getGold() / 10;
            loser.setGold(loser.getGold() - goldTransfer);
            winner.setGold(winner.getGold() + goldTransfer);
        }

        winner.getArmy().getSoldiers().forEach(s -> {
            s.gainExp();
            winner.informObserver("Żołnierz ID:" + s.getId() + " zyskał doświadczenie");
            winner.informObserver("Żołnierz ID:" + s.getId() + " awansował na " + s.getRank());
        });

        loser.getArmy().getSoldiers().forEach(s -> {
            s.loseExp();
            if (s.isDead()) {
                loser.informObserver("Żołnierz ID:" + s.getId() + " poległ w bitwie");
            } else {
                loser.informObserver("Żołnierz ID:" + s.getId() + " stracił doświadczenie");
            }
        });

        winner.getArmy().removeDeadSoldiers();
        loser.getArmy().removeDeadSoldiers();
    }

    private void BattleDraw(General general1, General general2) {
        Soldier soldier1 = general1.getArmy().killOneSoldier();
        Soldier soldier2 = general2.getArmy().killOneSoldier();

        if (soldier1 != null) {
            general1.getArmy().removeSoldier(soldier1);
        }
        if (soldier2 != null) {
            general2.getArmy().removeSoldier(soldier2);
        }
    }

    @Override
    public void BuySoldiers(General general, Rank rank, int count) {
        int totalCost = 10 * rank.getValue() * count;

        if (general.getGold() >= totalCost) {
            general.setGold(general.getGold() - totalCost);
            for (int i = 0; i < count; i++) {
                Soldier newSoldier = new Soldier(rank);
                general.getArmy().addSoldier(newSoldier);
                general.informObserver("Zakupiono żołnierza ID:" + newSoldier.getId() + " rangi " + rank);
            }
            general.informObserver("Zakupiono " + count + " żołnierzy rangi " + rank);
        } else {
            general.informObserver("Niewystarczająca ilość złota na zakup żołnierzy");
        }
    }
}