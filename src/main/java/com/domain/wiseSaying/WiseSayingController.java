package com.domain.wiseSaying;

import com.AppContext;
import com.Rq;

import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private final Scanner scanner = AppContext.scanner;
    private final WiseSayingService wiseSayingService = AppContext.wiseSayingService;

    public void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        if (id == -1) {
            System.out.println("숫자를 입력해주세요.");
            return;
        }

        WiseSaying wiseSaying = wiseSayingService.findById(id);

        if (wiseSaying == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
        }

        System.out.println("명언(기존): %s".formatted(wiseSaying.getContent()));
        System.out.print("명언: ");
        String content = scanner.nextLine().trim();

        System.out.println( "작가(기존): %s".formatted(wiseSaying.getAuthor()));
        System.out.print("작가: ");
        String author = scanner.nextLine().trim();

        wiseSayingService.modify(wiseSaying, content, author);

        System.out.println("%d번 명언이 수정되었습니다.".formatted(id));
    }

    public void actionDelete(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        if (id == -1) {
            System.out.println("숫자를 입력해주세요.");
            return;
        }

        WiseSaying wiseSaying = wiseSayingService.findById(id);

        if (wiseSaying == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
        }

        wiseSayingService.delete(wiseSaying);

        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    public void actionWrite() {
        System.out.print("명언: ");
        String content = scanner.nextLine().trim();
        System.out.print("작가: ");
        String author = scanner.nextLine().trim();

        WiseSaying wiseSaying = wiseSayingService.write(content, author);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId()));
    }

    public void actionList() {
        List<WiseSaying> wiseSayings =  wiseSayingService.getWiseSayings();

        System.out.println("번호 / 작가 / 명언 / 작성 / 수정");
        System.out.println("----------------------");
        for (int i = wiseSayings.size() - 1; i >= 0; i--) {
            WiseSaying wiseSaying = wiseSayings.get(i);
            System.out.println("%d / %s / %s / %s / %s".formatted(
                            wiseSaying.getId(),
                            wiseSaying.getAuthor(),
                            wiseSaying.getContent(),
                            wiseSaying.getCreatedDate(),
                            wiseSaying.getModifiedDate()
                    )
            );
        }
    }
}
