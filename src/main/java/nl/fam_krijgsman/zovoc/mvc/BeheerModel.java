package nl.fam_krijgsman.zovoc.mvc;

import nl.fam_krijgsman.zovoc.model.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

class BeheerModel extends Vereniging {
    private final static String VERENIGING_NAAM = "Zovoc";
    private final TeamModel teamModel = new TeamModel();
    private final LedenModel ledenModel = new LedenModel();

    public BeheerModel() {
        super(VERENIGING_NAAM); //Default naam
    }

    abstract class ModelHandler extends AbstractTableModel {
        private final String[] columnNames;
        private final Class<?>[] columnClass;

        public ModelHandler(String[] columnNames, Class<?>[] columnClass) {
            this.columnNames = columnNames;
            this.columnClass = columnClass;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return columnClass[columnIndex];
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }
    }

    public TeamModel getTeamModel() {
        return teamModel;
    }

    class TeamModel extends ModelHandler implements TeamDao {
        private ArrayList<Team> teams;

        public TeamModel() {
            super(new String[]{"Teamnaam", "Klasse", "Geslacht"}
                    , new Class[]{String.class, eKlasse.class, eGeslacht.class}
            );
            teams = new ArrayList<>();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Team row = this.teams.get(rowIndex);
            if (columnIndex == 0) {
                return row.getNaam();
            } else if (columnIndex == 1) {
                return row.getKlasse();
            } else if (columnIndex == 2) {
                return row.getGeslacht();
            }
            return null;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Team row = this.teams.get(rowIndex);
            if (columnIndex == 0) {
                row.setNaam((String) aValue);
            } else if (columnIndex == 1) {
                row.setKlasse((eKlasse) aValue);
            } else if (columnIndex == 2) {
                row.setGeslacht((eGeslacht) aValue);
            }
        }

        @Override
        public int getRowCount() {
            return this.teams.size();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        public boolean removeTeam(int index) {
            try {
                this.removeTeam(teams.get(index));
                return true;
            } catch (IndexOutOfBoundsException e) {
                return false;
            }
        }

        @Override
        public List<Team> getTeams() {
            return this.teams;
        }

        @Override
        public Team findTeam(String naam) {
            for (Team team: teams) {
                if (team.getNaam().equals(naam)) {
                    return team;
                }
            }
            return null;
        }

        private Team findTeam(Team team) {
            return this.findTeam(team.getNaam());
        }

        @Override
        public boolean addTeam(Team team) {
            //controlleer of team al bestaat
            if (findTeam(team) == null) {
                teams.add(team);
                return true;
            }
            // team bestond all
            return false;
        }

        @Override
        public boolean removeTeam(Team team) {
            //controlleer of te verwijderen team voorkomt
            if (findTeam(team) != null) {
                teams.remove(team);
                return true;
            }
            // team kwam niet voor
            return false;
        }
    }

    public LedenModel getLedenModel() {
        return ledenModel;
    }

    class LedenModel extends ModelHandler {

        public LedenModel() {
            super(new String[]{"Achternaam", "Voornaam", "TussenVoegsel", "TelefoonNummer", "e-mail", "GeboorteJaar", "Geslacht", "Team"}
                    , new Class[]{String.class, String.class, String.class, String.class, String.class, Integer.class, eGeslacht.class, Team.class}
            );
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Lid row = getLeden().get(rowIndex);
            if (columnIndex == 0) {
                return row.getAchterNaam();
            } else if (columnIndex == 1) {
                return row.getVoorNaam();
            } else if (columnIndex == 2) {
                return row.getTussenVoegsel();
            } else if (columnIndex == 3) {
                return row.getTelefoonNummer();
            } else if (columnIndex == 4) {
                return row.getEmail();
            } else if (columnIndex == 5) {
                return row.getGeboorteJaar();
            } else if (columnIndex == 6) {
                return row.getGeslacht();
            } else if (columnIndex == 7) {
                Team team = row.getTeam();
                if (team == null) {
                    return "";
                }
                return row.getTeam().getNaam();
            }
            return null;
        }

        @Override
        public int getRowCount() {
            return getLeden().size();
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Lid row = getLeden().get(rowIndex);
            if (columnIndex == 2) {
                row.setTussenVoegsel((String) aValue);
            } else if (columnIndex == 3) {
                try {
                    row.setTelefoonNummer((String) aValue);
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Dit is geen valide telefoonnummer.");
                }
            } else if (columnIndex == 4) {
                try {
                    row.setEmail((String) aValue);
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Dit is geen valide email adres.");
                }
            } else if (columnIndex == 5) {
                    row.setGeboorteJaar((Integer) aValue);
            } else if (columnIndex == 6) {
                row.setGeslacht((eGeslacht) aValue);
            } else if (columnIndex == 7) {
                try {
                    row.setTeam(findTeam((String) aValue));
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Dit is geen team voor een " + row.getGeslacht() + " uit " + row.getGeboorteJaar() + ".");
                }
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return (columnIndex > 1);
        }

        public boolean removeLid(int index) {
            if (getLeden().get(index) != null) {
                getLeden().remove(index);
                return true;
            }
            return false;
        }
    }

    //Als een team verwijderd wordt moet het team leeg gezet worden bij de leden uit het team
    public void removeTeamFromLid(Team team) {
        for (Lid lid : getLeden()) {
            try {
                if (lid.getTeam().equals(team)) {
                    lid.setTeam(null);
                }
            } catch (NullPointerException e) {
                // als een lid geen team meer heeft
            }

        }
    }
}
