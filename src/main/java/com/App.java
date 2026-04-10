package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class App {
    Scanner scanner = new Scanner(System.in);
    List<WiseSaying> wiseSayings = new ArrayList<>();
    int lastId = 0;

    void run () {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                actionWrite();
            } else if (cmd.equals("목록")) {
                actionList();
            } else if (cmd.startsWith("삭제")) {
                actionDelete(cmd);
            } else if (cmd.startsWith("수정")) {
                actionModify(cmd);
            }
        }
        scanner.close();
    }

    void actionModify(String cmd) {
        int id = splitCmd(cmd);

        WiseSaying wiseSaying = findById(id);

        if (wiseSaying == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
        }

        System.out.println("명언(기존): %s".formatted(wiseSaying.getContent()));
        System.out.print("명언: ");
        String content = scanner.nextLine().trim();

        System.out.println( "작가(기존): %s".formatted(wiseSaying.getAuthor()));
        System.out.print("작가: ");
        String author = scanner.nextLine().trim();

        modify(wiseSaying, content, author);

        System.out.println("%d번 명언이 수정되었습니다.".formatted(id));
    }

    void actionDelete(String cmd) {
        int id = splitCmd(cmd);

        WiseSaying wiseSaying = findById(id);

        if (wiseSaying == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
        }

        delete(wiseSaying);

        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    void actionWrite() {
        System.out.print("명언: ");
        String content = scanner.nextLine().trim();
        System.out.print("작가: ");
        String author = scanner.nextLine().trim();

        WiseSaying wiseSaying = new WiseSaying(++lastId, content, author);
        wiseSayings.add(wiseSaying);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId()));
    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (int i = wiseSayings.size() - 1; i >= 0; i--) {
            WiseSaying wiseSaying = wiseSayings.get(i);
            System.out.println("%d / %s / %s".formatted(wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent()));
        }


        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        IntStream.range(0, wiseSayings.size())
                .map(i -> wiseSayings.size() - 1 - i)
                .mapToObj(wiseSayings::get)
                .forEach(
                        wiseSaying ->  System.out.println("%d / %s / %s".formatted(wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent()))
                );
//
//        System.out.println("번호 / 작가 / 명언");
//        System.out.println("----------------------");
//        wiseSayings.reversed()
//                .stream()
//                .map(wiseSaying -> "%d / %s / %s".formatted(wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent()))
//                .forEach(System.out::println);
    }


     void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
    }

    void delete(WiseSaying wiseSaying) {
        wiseSayings.remove(wiseSaying);

//        wiseSayings.removeIf(ws -> ws.getId() == id);
    }


    int splitCmd(String cmd) {
        String[] cmdBits = cmd.split("=");
        return Integer.parseInt(cmdBits[1]);

//        return Arrays.stream(cmd.split("="))
//                .skip(1)
//                .findFirst()
//                .filter(s -> !s.isEmpty())
//                .map(Integer::parseInt)
//                .orElse(-1);
    }

    WiseSaying findById(int id) {
        WiseSaying wiseSaying = null;
        for (int i = 0; i < wiseSayings.size(); i++) {
            if (wiseSayings.get(i).getId() == id) {
                wiseSaying = wiseSayings.get(i);
                break;
            }
        }
        return wiseSaying;

//        return wiseSayings.stream()
//                .filter(ws -> ws.getId() == id)
//                .findFirst()
//                .orElse(null);
    }
}
