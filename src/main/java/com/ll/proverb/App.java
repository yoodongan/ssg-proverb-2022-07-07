package com.ll.proverb;

import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class App {
    private Scanner sc;      // run() 안에 있던 변수들을 App 클래스 변수로 빼낸다. -> 함수들에서 공통으로 사용하는 변수들이므로.
    private List<WiseSaying> wiseSayings;    // WiseSayings 객체를 리스트 형태로 저장.
    private int wiseSayingLastId;    // 가장 마지막 명언글의 번호를 말한다.

    public App() {
        sc = new Scanner(System.in);
        wiseSayings = new ArrayList<>();
        wiseSayingLastId = 0;
    }

    public void run() {
        System.out.println("=== 명언 SSG ===");

        outer:
        while (true) {
            System.out.printf("명령) ");
            String cmd = sc.nextLine().trim();

            Rq rq = new Rq(cmd);

            switch (rq.getPath()) {
                case "등록":
                    write(rq);   // rq 인자는 공유하는 대상이 아니다. 따라서, 인자로 넣어줘야 한다.
                    break;
                case "삭제":
                    remove(rq);
                    break;
                case "목록":
                    list(rq);
                    break;
                case "종료":
                    break outer;
            }
        }

        sc.close();

    }
    private void list(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------");
        for (int i = wiseSayings.size() - 1; i >= 0; i--) {    // 큰수부터 내림차순으로 출력한다.
            WiseSaying wiseSaying_ = wiseSayings.get(i);  // List에서 WiseSaying 객체 빼내오기.
            System.out.printf("%d / %s / %s\n", wiseSaying_.id, wiseSaying_.content, wiseSaying_.author);
        }
    }
    private void write(Rq rq) {
        System.out.printf("명언 : ");
        String content = sc.nextLine().trim();
        System.out.printf("작가 : ");
        String author = sc.nextLine().trim();
        int id = ++wiseSayingLastId; // 명언 글 번호 증가

        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        wiseSayings.add(wiseSaying);

        System.out.printf("%d번 명언이 등록되었습니다.\n", id);

    }
    private void remove(Rq rq) {
        int paramId = rq.getIntParam("id", 0);

        if (paramId == 0) {
            System.out.println("id 를 입력해주세요.");
            return;    // continue 가 아니라, 이제는 함수를 빠져나오면 된다.
        }

        WiseSaying foundWiseSaying = findById(paramId);

        if (foundWiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", paramId);
            return;
        }
        wiseSayings.remove(foundWiseSaying);
        System.out.printf("%d번 명언이 삭제되었습니다!\n", paramId);

    }
    private WiseSaying findById(int paramId) {
        for (WiseSaying wiseSaying : wiseSayings) {
            if (wiseSaying.id == paramId) {
                return wiseSaying;
            }
        }
        return null;
    }



}
