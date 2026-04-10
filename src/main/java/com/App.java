package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner scanner = new Scanner(System.in);
    List<WiseSaying> wiseSayings = new ArrayList<>();
    int lastId = 0;

    void run () {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            Rq rq = new Rq(cmd);

            switch (rq.getActionName()) {
                case "종료":
                    System.out.println("프로그램을 종료합니다.");
                    return;
                case "등록":
                    actionWrite();
                    break;
                case "목록":
                    actionList();
                    break;
                case "삭제":
                    actionDelete(rq);
                    break;
                case "수정":
                    actionModify(rq);
                    break;
            }
        }
    }

    void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        if (id == -1) {
            System.out.println("숫자를 입력해주세요.");
            return;
        }

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

    void actionDelete(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        if (id == -1) {
            System.out.println("숫자를 입력해주세요.");
            return;
        }

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
    }


     void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
    }

    void delete(WiseSaying wiseSaying) {
        wiseSayings.remove(wiseSaying);
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
    }
}
