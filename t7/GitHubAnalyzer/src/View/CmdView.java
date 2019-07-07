package View;

import Controller.CommitAnalyzerController;
import Controller.UrlFileLoaderController;

import java.io.IOException;
import java.util.Scanner;

public class CmdView implements View {

    private final char CMD_FILE = '1';
    private final char INSERT_FILE = '2';
    private final char EXIT = '9';

    private final char LIST_REPOS = '1';
    private final char GITHUB_ANALYZER = '2';

    private UrlFileLoaderController urlFileLoaderController;
    private CommitAnalyzerController commitAnalyzerController;

    @Override
    public void init(String[] args) {
        Scanner scanner = new Scanner(System.in);
        urlFileLoaderController = new UrlFileLoaderController();
        commitAnalyzerController = new CommitAnalyzerController();
        String path = "";
        System.out.println("Olá, bem vindo ao GitHubAnalyzerCmd!!");
        if (args != null && args.length > 0) {
            System.out.println("Para usar o arquivo fornecido na linha de comando, digite: " + CMD_FILE);
            path = args[0];
        }
        System.out.println("Para inserir o nome do arquivo, digite: " + INSERT_FILE);
        System.out.println("Para sair do programa, digite: " + EXIT);
        char option = scanner.next().charAt(0);

        switch (option) {
            case CMD_FILE:
                try {
                    urlFileLoaderController.load(path);
                    run();
                } catch (IOException e) {
                    System.out.println("Erro na leitura do arquivo");
                }
                break;
            case INSERT_FILE:
                System.out.println("Insira o nome do arquivo: \n");
                path = scanner.next();
                try {
                    urlFileLoaderController.load(path);
                    run();
                } catch (IOException e) {
                    System.out.println("Erro na leitura do arquivo");
                }
                break;
            case EXIT:
                System.out.println("Até mais!!");
                break;
        }
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        commitAnalyzerController.setInititalRepositories(urlFileLoaderController.getUrlList());

        char option;
        do {
            System.out.println("Para listar os repositórios, digite: " + LIST_REPOS);
            System.out.println("Para ativar o GitHubAnalyzer, digite: " + GITHUB_ANALYZER);
            System.out.println("Para sair do programa, digite: " + EXIT);

            option = scanner.next().charAt(0);

            switch (option) {
                case LIST_REPOS:
                    commitAnalyzerController.printRepositories();
                    commitAnalyzerController.printRepositoriesInfo();
                    break;
                case GITHUB_ANALYZER:
                    Thread t = new Thread(() -> {
                        try {
                            commitAnalyzerController.getCommitsFromRepository();
                        } catch (Exception e) {
                            System.out.println("Erro ao buscar os commits dos repositórios");
                        }
                    });
                    t.start();
                    break;
                case EXIT:
                    System.out.println("Até mais!!");
                    break;
            }
        } while (option != EXIT);

    }
}