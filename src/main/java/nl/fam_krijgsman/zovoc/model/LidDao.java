package nl.fam_krijgsman.zovoc.model;

import java.util.List;

interface LidDao {
    List<Lid> getLeden();
    Lid findLid(String achternaam, String voornaam);
    boolean addLid(Lid lid);
    boolean removeLid(Lid lid);
    int aantalLeden();
}
