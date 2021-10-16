package com.jb.newsdao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jb.newsmodel.News;

public class MainApp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		int opcion = 0;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		News news;
		EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

		while (opcion!=5) {
			System.out.println("1. Crear Noticia");
			System.out.println("2. Buscar Noticia");
			System.out.println("3. Actualizar Noticia");
			System.out.println("4. Eliminar Noticia");
			System.out.println("5. Salir");
			System.out.println("Elija una opción:");

			opcion = scanner.nextInt();

			switch (opcion) {

			case 1:
				System.out.println("Digite el titulo de la noticia:");
				news = new News();
				news.setNews_id(null);
				scanner.nextLine();
				news.setTitle(scanner.nextLine());
				System.out.println("Digite la noticia:");
				news.setNews(scanner.nextLine());
				System.out.println(news);
				entity.getTransaction().begin();
				entity.persist(news);
				entity.getTransaction().commit();
				System.out.println("Noticia registrada...");
				System.out.println();
				break;

			case 2:
				System.out.println("Digite el id del producto a buscar:");
				news = new News();
				news = entity.find(News.class, scanner.nextInt());
				if (news != null) {
					System.out.println(news);
					System.out.println();
				} else {
					System.out.println();
					System.out.println("Noticia no encontrada... Lista de noticias completa");
					List<News> listNews = new ArrayList<>();
					Query query = entity.createQuery("SELECT n FROM News n");
					listNews = query.getResultList();
					for (News n : listNews) {
						System.out.println(n);
					}					
					System.out.println();
				}
				break;

			case 3:
				System.out.println("Digite el id de la noticia a actualizar:");
				news = new News();
				news = entity.find(News.class, scanner.nextInt());
				if (news != null) {
					System.out.println(news);
					System.out.println("Digite el titulo de la noticia:");
					scanner.nextLine();
					news.setTitle(scanner.nextLine());
					System.out.println("Digite la noticia:");
					news.setNews(scanner.nextLine());
					entity.getTransaction().begin();
					entity.merge(news);
					entity.getTransaction().commit();
					System.out.println("Noticia actualizada...");
					System.out.println();
				} else {
					System.out.println("Noticia no encontrada...");
					System.out.println();
				}
				break;
				
			case 4:
				System.out.println("Digite el id de la noticia a eliminar:");
				news = new News();
				news = entity.find(News.class, scanner.nextInt());
				if (news != null) {
					System.out.println(news);
					entity.getTransaction().begin();
					entity.remove(news);
					entity.getTransaction().commit();
					System.out.println("Noticia eliminada...");
				} else {
					System.out.println("Noticia no encontrada...");
				}
				break;
				
			case 5:
				entity.close();
				JPAUtil.shutdown();
			break;

			default:
				System.out.println("Opción no válida\n");
				break;
				
			}
			
		}
		
	}

}