package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner scanner = new Scanner(System.in);
    int lastId = 0;
    List<WiseSaying> wiseSayings = new ArrayList<>();

    void run () {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            if (cmd.equals("종료")) {
                System.out.println("프로그램을 종료합니다.");
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

    void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();

        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        WiseSaying wiseSaying = write(content, author);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId()));
    }

    WiseSaying write (String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(++lastId, content, author);
        wiseSayings.add(wiseSaying);

        return wiseSaying;
    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        for (int i = wiseSayings.size() - 1; i >= 0; i--) {
            WiseSaying wiseSaying = wiseSayings.get(i);
            if (wiseSaying == null) {
                continue;
            }
            System.out.println("%d / %s / %s".formatted(wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent()));
        }
    }

    void actionDelete(String cmd) {
        String[] cmdBits = cmd.split("=");

        if (cmdBits.length < 2 || cmdBits[1].isEmpty()) {
            System.out.println("id 확인해주세요.");
            return;
        }

        int id = Integer.parseInt(cmdBits[1]);

        // 삭제?id=1 인경우 id 가 1 wiseSaying 객체를 찾아서 삭제한다.
        WiseSaying wiseSaying = delete(id);

        if (wiseSaying == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    WiseSaying delete(int id) {
        WiseSaying wiseSaying = null;
        for (int i = 0; i < wiseSayings.size(); i++) {
            if (wiseSayings.get(i).getId() == id) {
                wiseSaying = wiseSayings.get(i);
            }
        }

        wiseSayings.remove(wiseSaying);

        return wiseSaying;
    }

    void actionModify(String cmd) {
        String[] cmdBits = cmd.split("=");

        if (cmdBits.length < 2 || cmdBits[1].isEmpty()) {
            System.out.println("id 확인해주세요.");
            return;
        }

        int id = Integer.parseInt(cmdBits[1]);

        modify(id);

        System.out.println("%d번 명언이 수정되었습니다.".formatted(id));
    }

    void modify(int id) {
        WiseSaying wiseSaying = null;
        for (int i = 0; i < wiseSayings.size(); i++) {
            if (wiseSayings.get(i).getId() == id) {
                wiseSaying = wiseSayings.get(i);
            }
        }

        if (wiseSaying == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("명언(기존) : %s".formatted(wiseSaying.getContent()));
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();
        wiseSaying.setContent(content);

        System.out.println("작가(기존) : %s".formatted(wiseSaying.getAuthor()));
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();
        wiseSaying.setAuthor(author);
    }
}
