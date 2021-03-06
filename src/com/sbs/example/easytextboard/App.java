package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {
	int lastArticleId = 0;
	int articlesSize = 0;
	Article[] articles = new Article[100];

	int maxArticlesSize = articles.length;

	public int articlesSize() {
		return articlesSize;
	}

	public Article getArticle(int id) {
		int index = getIndexOf(id);
		if (index == -1) {
			return null;
		}
		return articles[index];
	}

	private void remove(int id) {
		int index = getIndexOf(id);
		if (index == -1) {
			return;
		}
		for (int i = index + 1; i < articlesSize(); i++) {
			articles[i - 1] = articles[i];
		}
		articlesSize--;
	}

	private int getIndexOf(int id) {
		for (int i = 0; i < articlesSize(); i++) {
			if (articles[i].id == id) {
				return i;
			}
		}
		return -1;
	}

	public void run() {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine();

			if (command.equals("article add")) {
				int articleId = lastArticleId + 1;
				if (articlesSize() >= maxArticlesSize) {
					System.out.println("더이상 등록할 수 없습니다.");
					continue;
				}
				lastArticleId = articleId;

				System.out.println("== 게시물 등록 ==");

				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article();

				article.id = articleId;
				article.title = title;
				article.body = body;

				System.out.printf("%d번 게시물이 등록되었습니다.%n", articleId);

				articles[articlesSize] = article;

				articlesSize++;

			} else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");

				if (articlesSize() == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}
				System.out.println("번호 / 제목");

				for (int i = 0; i < articlesSize(); i++) {
					Article article = articles[i];
					System.out.printf("%d / %s%n", article.id, article.title);

				}

			} else if (command.startsWith("article detail ")) {
				int inputId = Integer.parseInt(command.split(" ")[2]);

				System.out.println("== 게시물 상세 ==");
				Article article = getArticle(inputId);

				if (article == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.%n", inputId);
					continue;

				}
				System.out.printf("번호 : %d%n", article.id);
				System.out.printf("번호 : %s%n", article.title);
				System.out.printf("번호 : %s%n", article.body);

			} else if (command.startsWith("article delete ")) {
				int inputId = Integer.parseInt(command.split(" ")[2]);

				System.out.println("== 게시물 삭세 ==");
				Article article = getArticle(inputId);

				if (article == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.%n", inputId);
					continue;

				}
				remove(inputId);
				System.out.printf("%d번 게시물이 삭제되었습니다.%n", inputId);

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
