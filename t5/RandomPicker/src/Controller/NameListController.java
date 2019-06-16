package Controller;


import Model.NameList;

import java.util.ArrayList;
import java.util.Arrays;

public class NameListController {

    private final int MAX_SIZE_OFFLINE = 5;
    private Controller controller;
    private NameList nameList;
    public boolean isFileShuffled = false;


    public NameListController(Controller controller) {
        this.controller = controller;
        this.nameList = new NameList("");
    }

    public boolean initNameList(String path) {
        this.nameList = new NameList(path);
        return this.nameList.load();
    }

    public String pick() {
        String str = null;
        try {
            if (nameList.isEmpty()) {
                return null;
            }
            str = controller.pick(nameList);
        } catch (NullPointerException e) {
            System.err.println("Controller não foi definido");
        }
        return str;
    }

    public void shuffle() {
        try {
            if (nameList.getNames().size() > MAX_SIZE_OFFLINE) {
                setController(new OnlinePickController());
            } else {
                setController(new OfflinePickController());
            }

            isFileShuffled = controller.shuffle(nameList);
        } catch (NullPointerException e) {
            System.err.println("Controller não foi definido");
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public String getNames() {
        return nameList.getNamesAsString();
    }

    public void updateNames(String names) {
        String[] namesList = names.split("\n");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(namesList));
        nameList.setNames(list);
    }
}
