package nl.fam_krijgsman.zovoc.mvc;

import nl.fam_krijgsman.zovoc.generic.Helper;
import nl.fam_krijgsman.zovoc.model.*;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

class BeheerModel extends Vereniging {
    private final TeamModel teamModel = new TeamModel();
    private final LedenModel ledenModel = new LedenModel();

    public BeheerModel() {
        super(Helper.getVerenigingNaam());

    }

    abstract class ModelHandler extends AbstractTableModel {
        private final String[] columnNames;
        private final Class[] columnClass;

        public ModelHandler(String[] columnNames, Class[] columnClass) {
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

    class TeamModel extends ModelHandler {

        public TeamModel() {
            super(new String[]{"Teamnaam", "Klasse", "Geslacht"}
                    , new Class[]{String.class, eKlasse.class, eGeslacht.class}
            );
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Team row = getTeams().get(rowIndex);
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
            Team row = getTeams().get(rowIndex);
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
            return getTeams().size();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        public boolean removeTeam(int index) {
            if (getTeams().get(index) != null) {
                getTeams().remove(index);
                return true;
            }
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
                row.setTelefoonNummer((String) aValue);
            } else if (columnIndex == 4) {
                row.setEmail((String) aValue);
            } else if (columnIndex == 5) {
                row.setGeboorteJaar((Integer) aValue);
            } else if (columnIndex == 6) {
                row.setGeslacht((eGeslacht) aValue);
            } else if (columnIndex == 7) {
                row.setTeam(findTeam((String) aValue));
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
