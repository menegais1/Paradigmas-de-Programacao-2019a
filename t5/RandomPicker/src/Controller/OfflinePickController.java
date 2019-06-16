package Controller;

import Model.NameList;

import java.util.Collections;
import java.util.Random;

public class OfflinePickController implements Controller {

    @Override
    public String pick(NameList nameList) {
        return nameList.pick();
    }

    @Override
    public boolean shuffle(NameList nameList) {
        if(nameList.isEmpty()) return false;
        Collections.shuffle(nameList.getNames(), new Random((int) (Math.random() * 100)));
        return true;
    }
}
