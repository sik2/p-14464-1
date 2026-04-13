package com.domain.wiseSaying;

import java.util.List;

public class WiseSayingService {
    private final WiseSayingRepository wiseSayingRepository;

    public WiseSayingService() {
        this.wiseSayingRepository = new WiseSayingRepository();
    }

    public WiseSaying write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(content, author);

        return wiseSayingRepository.save(wiseSaying);
    }

    public List<WiseSaying> getWiseSayings() {
        return wiseSayingRepository.getWiseSayings();
    }

    public WiseSaying findById(int id) {
        return wiseSayingRepository.findById(id);
    }

    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
        wiseSayingRepository.save(wiseSaying);
    }

    public void delete(WiseSaying wiseSaying) {
        wiseSayingRepository.delete(wiseSaying);
    }

}
