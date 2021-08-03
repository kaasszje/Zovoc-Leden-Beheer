package nl.fam_krijgsman.zovoc.model;

import nl.fam_krijgsman.zovoc.exception.LidAllreadyExistsException;
import nl.fam_krijgsman.zovoc.exception.LidNotFoundException;

import java.util.List;

public interface LidDao {
    List<Lid> getLeden();
    Lid findLid(String achterNaam, String voorNaam) throws LidNotFoundException;
    void addLid(Lid lid) throws LidAllreadyExistsException;
    void removeLid(Lid lid) throws LidNotFoundException;
    int aantalLeden();
}
