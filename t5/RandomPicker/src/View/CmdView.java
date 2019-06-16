package View;

import Controller.NameListController;
import Controller.OfflinePickController;
import Controller.OnlinePickController;

import java.util.Scanner;

public class CmdView {


    private final char CMD_FILE = '1';
    private final char DEFAULT_FILE = '2';
    private final char EXIT = '9';

    private final char SHUFFLE_FILE = '1';
    private final char PICK_ITEM = '2';
    private final char OFFLINE_SHUFFLE = '3';
    private final char ONLINE_SHUFFLE = '4';

    private final String DEFAULT_FILE_PATH = "./names.txt";

    private NameListController controller;

    public void init(String path) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Olá, bem vindo ao RandomPickerCmd!!");
        System.out.println("Para usar o arquivo fornecido na linha de comando, digite: " + CMD_FILE);
        System.out.println("Para usar o arquivo padrão, digite: " + DEFAULT_FILE);
        System.out.println("Para sair do programa, digite: " + EXIT);
        char option = scanner.next().charAt(0);

        controller = new NameListController(new OfflinePickController());
        switch (option) {
            case CMD_FILE:
                if (controller.initNameList(path)) run();
                break;
            case DEFAULT_FILE:
                if (controller.initNameList(DEFAULT_FILE_PATH)) run();
                break;
            case EXIT:
                System.out.println("Até mais!!");
                break;
        }
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        char option;
        do {
            System.out.println("Para embaralhar o arquivo, digite: " + SHUFFLE_FILE);
            if (controller.isFileShuffled) {
                System.out.println("Para pegar um nome, digite: " + PICK_ITEM);
            }
            System.out.println("Para mudar o método de embaralhar para OFFLINE, digite: " + OFFLINE_SHUFFLE);
            System.out.println("Para mudar o método de embaralhar para ONLINE, digite: " + ONLINE_SHUFFLE);
            System.out.println("Para sair do programa, digite: " + EXIT);

            option = scanner.next().charAt(0);

            switch (option) {
                case SHUFFLE_FILE:
                    controller.shuffle();
                    if(!controller.isFileShuffled) System.out.println("Não foi possivel embaralhar o arquivo");
                    break;
                case PICK_ITEM:
                    System.out.println(controller.pick());
                    break;
                case OFFLINE_SHUFFLE:
                    controller.setController(new OfflinePickController());
                    break;
                case ONLINE_SHUFFLE:
                    controller.setController(new OnlinePickController());
                    break;
                case EXIT:
                    System.out.println("Até mais!!");
                    break;
            }
        } while (option != EXIT);

    }
}
