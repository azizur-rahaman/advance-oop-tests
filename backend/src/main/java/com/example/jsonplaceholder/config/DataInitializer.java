package com.example.jsonplaceholder.config;

import com.example.jsonplaceholder.model.*;
import com.example.jsonplaceholder.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    
    @Bean
    CommandLineRunner initDatabase(
            UserRepository userRepository,
            PostRepository postRepository,
            CommentRepository commentRepository,
            TodoRepository todoRepository,
            AlbumRepository albumRepository,
            PhotoRepository photoRepository) {
        
        return args -> {
            // Check if data already exists
            if (userRepository.count() > 0) {
                System.out.println("Database already has data. Skipping initialization.");
                return;
            }
            
            System.out.println("Initializing database with sample data...");
            
            // Create sample users
            User user1 = new User("Leanne Graham", "Bret", "leanne@example.com", "1-770-736-8031", "hildegard.org");
            User user2 = new User("Ervin Howell", "Antonette", "ervin@example.com", "010-692-6593", "anastasia.net");
            User user3 = new User("Clementine Bauch", "Samantha", "clementine@example.com", "1-463-123-4447", "ramiro.info");
            
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            
            // Create sample posts
            Post post1 = new Post(user1, "sunt aut facere repellat provident", 
                "quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut ut quas totam");
            Post post2 = new Post(user1, "qui est esse", 
                "est rerum tempore vitae sequi sint nihil reprehenderit dolor beatae ea dolores neque");
            Post post3 = new Post(user2, "ea molestias quasi exercitationem", 
                "et iusto sed quo iure voluptatem occaecati omnis eligendi aut ad");
            
            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);
            
            // Create sample comments
            Comment comment1 = new Comment(post1, "id labore ex et quam laborum", 
                "eliseo@example.com", "laudantium enim quasi est quidem magnam voluptate");
            Comment comment2 = new Comment(post1, "quo vero reiciendis velit", 
                "jayne@example.com", "est natus enim nihil est dolore omnis voluptatem");
            Comment comment3 = new Comment(post2, "odio adipisci rerum", 
                "nikita@example.com", "quia molestiae reprehenderit quasi aspernatur");
            
            commentRepository.save(comment1);
            commentRepository.save(comment2);
            commentRepository.save(comment3);
            
            // Create sample todos
            Todo todo1 = new Todo(user1, "delectus aut autem", false);
            Todo todo2 = new Todo(user1, "quis ut nam facilis et officia qui", false);
            Todo todo3 = new Todo(user1, "fugiat veniam minus", false);
            Todo todo4 = new Todo(user2, "et porro tempora", true);
            
            todoRepository.save(todo1);
            todoRepository.save(todo2);
            todoRepository.save(todo3);
            todoRepository.save(todo4);
            
            // Create sample albums
            Album album1 = new Album(user1, "quidem molestiae enim");
            Album album2 = new Album(user1, "sunt qui excepturi placeat culpa");
            Album album3 = new Album(user2, "omnis laborum odio");
            
            albumRepository.save(album1);
            albumRepository.save(album2);
            albumRepository.save(album3);
            
            // Create sample photos
            Photo photo1 = new Photo(album1, "accusamus beatae ad facilis cum similique qui sunt",
                "https://via.placeholder.com/600/92c952", "https://via.placeholder.com/150/92c952");
            Photo photo2 = new Photo(album1, "reprehenderit est deserunt velit ipsam",
                "https://via.placeholder.com/600/771796", "https://via.placeholder.com/150/771796");
            Photo photo3 = new Photo(album2, "officia porro iure quia iusto qui ipsa ut modi",
                "https://via.placeholder.com/600/24f355", "https://via.placeholder.com/150/24f355");
            
            photoRepository.save(photo1);
            photoRepository.save(photo2);
            photoRepository.save(photo3);
            
            System.out.println("✅ Database initialized with sample data!");
            System.out.println("   - Users: " + userRepository.count());
            System.out.println("   - Posts: " + postRepository.count());
            System.out.println("   - Comments: " + commentRepository.count());
            System.out.println("   - Todos: " + todoRepository.count());
            System.out.println("   - Albums: " + albumRepository.count());
            System.out.println("   - Photos: " + photoRepository.count());
        };
    }
}
