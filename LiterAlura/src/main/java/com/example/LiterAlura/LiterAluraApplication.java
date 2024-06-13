package com.example.LiterAlura;

import java.util.List;
import java.util.Scanner;
import java.util.Optional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
    public void run(String... args) {
		LiterAlura literAlura = new LiterAlura();
		Scanner sc = new Scanner(System.in);
        int option = 0;
		
		do {
			System.out.println("\n************************************************************");
			System.out.println("1. Buscar libro por titulo");
			System.out.println("2. Listar libros registrados");
			System.out.println("3. Listar autores registrados");
			System.out.println("4. Listar autores vivos en un determinado año");
			System.out.println("5. Listar libros por idioma");
			System.out.println("6. Salir");

			System.out.print("\nDigite una opción: ");
			option = sc.nextInt();
			sc.nextLine();

			switch(option) {
				case 1:
					System.out.print("Ingrese el titulo del libro: ");
					String title = sc.nextLine();
					Optional<Book> book = literAlura.getBookByTitle(title);
					if (book.isPresent()) {
						System.out.println(book.get().toString());
					} else {
						System.out.println("Libro no encontrado");
					}
					break;
				case 2:
					List<Book> books = literAlura.allBooks();
					books.forEach(System.out::println);
					break;
				case 3:
					List<Author> authors = literAlura.allAuthors();
					authors.forEach(System.out::println);
					break;
				case 4:
					System.out.print("Ingrese el año: ");
					int year = sc.nextInt();
					List<Author> authorsAliveByYear = literAlura.allAuthorsAliveByYear(year);
					authorsAliveByYear.forEach(System.out::println);
					break;
				case 5:
					System.out.print("Ingrese el idioma: ");
					String language = sc.nextLine();
					List<Book> booksByLanguage = literAlura.allBooksByLanguage(language);
					booksByLanguage.forEach(System.out::println);
					break;
				case 6:
					System.out.println("Programa terminado.");
					break;
				default:
					System.out.println("Opción invalida, intente nuevamente.");
					break;
			}
		} while (option != 6);
		
		sc.close();
    }

}
