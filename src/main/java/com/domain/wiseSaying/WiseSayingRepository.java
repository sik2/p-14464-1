package com.domain.wiseSaying;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private final List<WiseSaying> wiseSayings = new ArrayList<>();
    private int lastId = 0;

    public WiseSaying write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(++lastId, content, author);
        wiseSayings.add(wiseSaying);

        return wiseSaying;
    }

    public List<WiseSaying> getWiseSayings() {
        return wiseSayings;
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

    void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
        wiseSaying.setModifiedDate(LocalDateTime.now());
    }

    void delete(WiseSaying wiseSaying) {
        wiseSayings.remove(wiseSaying);
    }
}
