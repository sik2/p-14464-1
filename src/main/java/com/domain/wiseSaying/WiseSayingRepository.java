package com.domain.wiseSaying;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private final List<WiseSaying> wiseSayings = new ArrayList<>();
    private int lastId = 0;

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


    public WiseSaying save(WiseSaying wiseSaying) {
        if (wiseSaying.isNew()) {
            wiseSaying.setId(++lastId);
            wiseSayings.add(wiseSaying);
        } else {
            wiseSaying.setModifiedDate(LocalDateTime.now());
        }

        return wiseSaying;
    }

    void delete(WiseSaying wiseSaying) {
        wiseSayings.remove(wiseSaying);
    }
}
