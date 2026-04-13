package com;

import com.domain.system.SystemController;
import com.domain.wiseSaying.WiseSayingController;
import com.domain.wiseSaying.WiseSayingRepository;
import com.domain.wiseSaying.WiseSayingService;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AppContext {
    public static final Scanner scanner;
    public static final DateTimeFormatter forPrintDateTimeFormatter;
    public static final SystemController systemController;
    public static final WiseSayingRepository wiseSayingRepository;
    public static final WiseSayingService wiseSayingService;
    public static final WiseSayingController wiseSayingController;

    static {
        scanner = new Scanner(System.in);
        forPrintDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        systemController = new SystemController();
        wiseSayingRepository = new WiseSayingRepository();
        wiseSayingService = new WiseSayingService();
        wiseSayingController = new WiseSayingController();
    }
}



