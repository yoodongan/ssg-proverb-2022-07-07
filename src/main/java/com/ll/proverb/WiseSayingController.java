package com.ll.proverb;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private Scanner sc;      // run() 안에 있던 변수들을 App 클래스 변수로 빼낸다. -> 함수들에서 공통으로 사용하는 변수들이므로.
    private List<WiseSaying> wiseSayings;    // WiseSayings 객체를 리스트 형태로 저장.
    private int wiseSayingLastId;    // 가장 마지막 명언글의 번호를 말한다.

    public WiseSayingController(Scanner sc){
        wiseSayings = new ArrayList<>();
        wiseSayingLastId = 0;
        this.sc = sc;

    }

    public void list(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------");
        for (int i = wiseSayings.size() - 1; i >= 0; i--) {    // 큰수부터 내림차순으로 출력한다.
            WiseSaying wiseSaying_ = wiseSayings.get(i);  // List에서 WiseSaying 객체 빼내오기.
            System.out.printf("%d / %s / %s\n", wiseSaying_.id, wiseSaying_.content, wiseSaying_.author);
        }
    }
    public void write(Rq rq) {
        System.out.printf("명언 : ");
        String content = sc.nextLine().trim();
        System.out.printf("작가 : ");
        String author = sc.nextLine().trim();
        int id = ++wiseSayingLastId; // 명언 글 번호 증가

        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        wiseSayings.add(wiseSaying);

        System.out.printf("%d번 명언이 등록되었습니다.\n", id);

    }
    public void remove(Rq rq) {
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
    public void modify(Rq rq) {
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

        System.out.printf("명언(기존) : %s\n", foundWiseSaying.content);
        System.out.printf("명언 : ");
        foundWiseSaying.content = sc.nextLine();
        System.out.printf("작가(기존) : %s\n", foundWiseSaying.author);
        System.out.printf("작가 : ");
        foundWiseSaying.author = sc.nextLine();

        System.out.printf("%d번 명언이 수정되었습니다!\n", paramId);

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
