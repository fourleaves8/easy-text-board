package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {
	public void run() {

		int lastArticleId = 0;

		Article[] articles = new Article[3];
		for (int i = 0; i < articles.length; i++) {
			articles[i] = new Article();
		}

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine();

			if (command.equals("article add")) {

				int articleId = lastArticleId + 1;
				if (articleId > articles.length) {
					System.out.println("더이상 등록할 수 없습니다.");
					continue;
				}

				System.out.println("== 게시물 등록 ==");

				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				articles[lastArticleId].id = articleId;
				articles[lastArticleId].title = title;
				articles[lastArticleId].body = body;

				System.out.printf("%d번 게시물이 등록되었습니다.%n", articleId);

				lastArticleId = articleId;

			} else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");
				if (lastArticleId == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}
				System.out.println("번호 / 제목");

				for (int i = 0; i < lastArticleId; i++) {
					System.out.printf("%d / %s%n", articles[i].id, articles[i].title);

				}

			} else if (command.startsWith("article detail ")) {
				int inputId = Integer.parseInt(command.split(" ")[2]);

				System.out.println("== 게시물 상세 ==");

				if (lastArticleId == 0 || inputId > lastArticleId) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.%n", inputId);
					continue;
				}
				System.out.printf("번호 : %d%n", inputId);
				System.out.printf("번호 : %s%n", articles[inputId-1].title);
				System.out.printf("번호 : %s%n", articles[inputId-1].body);
				
			} else if (command.startsWith("article delete ")) {
				System.out.println("== 게시물 삭제 ==");

			} else if (command.endsWith("system exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;

			} else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}
		sc.close();
	}
}
