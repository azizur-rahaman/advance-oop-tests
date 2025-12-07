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
            
            // Create 10 sample users
            User user1 = new User("Leanne Graham", "Bret", "leanne@example.com", "1-770-736-8031", "hildegard.org");
            User user2 = new User("Ervin Howell", "Antonette", "ervin@example.com", "010-692-6593", "anastasia.net");
            User user3 = new User("Clementine Bauch", "Samantha", "clementine@example.com", "1-463-123-4447", "ramiro.info");
            User user4 = new User("Patricia Lebsack", "Karianne", "patricia@example.com", "493-170-9623", "kale.biz");
            User user5 = new User("Chelsey Dietrich", "Kamren", "chelsey@example.com", "254-954-1289", "demarco.info");
            User user6 = new User("Mrs. Dennis Schulist", "Leopoldo", "dennis@example.com", "1-477-935-8478", "ola.org");
            User user7 = new User("Kurtis Weissnat", "Elwyn", "kurtis@example.com", "210-067-6132", "elvis.io");
            User user8 = new User("Nicholas Runolfsdottir", "Maxime", "nicholas@example.com", "586-493-6943", "jacynthe.com");
            User user9 = new User("Glenna Reichert", "Delphine", "glenna@example.com", "775-976-6794", "conrad.com");
            User user10 = new User("Clementina DuBuque", "Moriah", "clementina@example.com", "024-648-3804", "ambrose.net");
            
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            userRepository.save(user4);
            userRepository.save(user5);
            userRepository.save(user6);
            userRepository.save(user7);
            userRepository.save(user8);
            userRepository.save(user9);
            userRepository.save(user10);
            
            // Create 20 sample posts
            Post post1 = new Post(user1, "sunt aut facere repellat provident", 
                "quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveniet architecto");
            Post post2 = new Post(user1, "qui est esse", 
                "est rerum tempore vitae sequi sint nihil reprehenderit dolor beatae ea dolores neque fugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis");
            Post post3 = new Post(user1, "ea molestias quasi exercitationem", 
                "et iusto sed quo iure voluptatem occaecati omnis eligendi aut ad voluptatem doloribus vel accusantium quis pariatur");
            Post post4 = new Post(user2, "eum et est occaecati", 
                "ullam et saepe reiciendis voluptatem adipisci sit amet autem assumenda provident rerum culpa quis hic commodi nesciunt rem tenetur doloremque ipsam iure");
            Post post5 = new Post(user2, "nesciunt quas odio", 
                "repudiandae veniam quaerat sunt sed alias aut fugiat sit autem sed est voluptatem omnis possimus esse voluptatibus quis");
            Post post6 = new Post(user3, "dolorem eum magni eos", 
                "ut aspernatur corporis harum nihil quis provident sequi mollitia nobis aliquid molestiae perspiciatis et ea nemo ab reprehenderit");
            Post post7 = new Post(user3, "magnam facilis autem", 
                "dolore placeat quibusdam ea quo vitae magni quis enim qui quis quo nemo aut saepe quidem repellat excepturi ut quia");
            Post post8 = new Post(user4, "dolorem dolore est ipsam", 
                "dignissimos aperiam dolorem qui eum facilis quibusdam animi sint suscipit qui sint possimus cum quaerat magni maiores excepturi");
            Post post9 = new Post(user4, "nesciunt iure omnis dolorem", 
                "consectetur animi nesciunt iure dolore enim quia ad veniam autem ut quam aut nobis et est aut quod aut provident voluptas autem voluptas");
            Post post10 = new Post(user5, "optio molestias id quia eum", 
                "quo et expedita modi cum officia vel magni doloribus qui repudiandae vero nisi sit quos veniam quod sed accusamus veritatis error");
            Post post11 = new Post(user5, "et ea vero quia laudantium", 
                "delectus reiciendis molestiae occaecati non minima eveniet qui voluptatibus accusamus in eum beatae sit vel qui neque voluptates ut commodi");
            Post post12 = new Post(user6, "in quibusdam tempore odit", 
                "itaque id aut magnam praesentium quia et ea odit et ea voluptas et sapiente quia nihil amet occaecati quia id voluptatem");
            Post post13 = new Post(user6, "dolorum ut in voluptas", 
                "aut dicta possimus sint mollitia voluptas commodi quo doloremque iste corrupti reiciendis voluptatem eius rerum");
            Post post14 = new Post(user7, "voluptatem eligendi optio", 
                "fuga et accusamus dolorum perferendis illo voluptas non doloremque neque facere ad qui dolorum molestiae beatae");
            Post post15 = new Post(user7, "eveniet quod temporibus", 
                "reprehenderit quos placeat velit minima officia dolores impedit repudiandae molestiae nam voluptas recusandae quis");
            Post post16 = new Post(user8, "sint suscipit perspiciatis", 
                "suscipit nam nisi quo aperiam aut asperiores eos fugit maiores voluptatibus quia voluptatem quis ullam qui in alias quia est");
            Post post17 = new Post(user8, "fugit voluptas sed molestias", 
                "porro vel nihil minus maiores et maxime nisi enim qui cumque modi sint cum voluptate pariatur ducimus rerum");
            Post post18 = new Post(user9, "voluptate et itaque vero", 
                "eveniet quo quis laborum totam consequatur non dolor ut et est repudiandae est voluptatem vel debitis et magnam");
            Post post19 = new Post(user9, "adipisci placeat illum", 
                "illum quis cupiditate provident sit magnam ea sed aut omnis veniam maiores ullam consequatur atque");
            Post post20 = new Post(user10, "doloribus ad provident suscipit", 
                "qui consequuntur ducimus possimus quisquam amet similique suscipit porro ipsam amet eos veritatis officiis exercitationem vel fugit aut necessitatibus totam");
            
            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);
            postRepository.save(post4);
            postRepository.save(post5);
            postRepository.save(post6);
            postRepository.save(post7);
            postRepository.save(post8);
            postRepository.save(post9);
            postRepository.save(post10);
            postRepository.save(post11);
            postRepository.save(post12);
            postRepository.save(post13);
            postRepository.save(post14);
            postRepository.save(post15);
            postRepository.save(post16);
            postRepository.save(post17);
            postRepository.save(post18);
            postRepository.save(post19);
            postRepository.save(post20);
            
            // Create 30 sample comments
            Comment comment1 = new Comment(post1, "id labore ex et quam laborum", 
                "eliseo@example.com", "laudantium enim quasi est quidem magnam voluptate ipsam eos tempora quo necessitatibus dolor");
            Comment comment2 = new Comment(post1, "quo vero reiciendis velit", 
                "jayne@example.com", "est natus enim nihil est dolore omnis voluptatem numquam et omnis occaecati");
            Comment comment3 = new Comment(post1, "odio adipisci rerum", 
                "nikita@example.com", "quia molestiae reprehenderit quasi aspernatur aut expedita occaecati aliquam");
            Comment comment4 = new Comment(post2, "alias odio sit", 
                "lew@example.com", "non et atque occaecati deserunt quas accusantium unde odit nobis qui voluptatem");
            Comment comment5 = new Comment(post2, "vero eaque aliquid", 
                "hayden@example.com", "harum non quasi et ratione tempore iure ex voluptates in ratione");
            Comment comment6 = new Comment(post3, "et fugit eligendi", 
                "presley@example.com", "doloribus at sed quis culpa deserunt consectetur qui praesentium accusamus fugiat");
            Comment comment7 = new Comment(post3, "repellat consequatur", 
                "dallas@example.com", "maiores sed dolores similique labore et inventore et quasi temporibus esse");
            Comment comment8 = new Comment(post4, "et omnis dolorem", 
                "mallory@example.com", "ut voluptatem corrupti velit ad voluptatem maiores et nisi velit");
            Comment comment9 = new Comment(post4, "provident id voluptas", 
                "meghan@example.com", "sapiente assumenda molestiae atque adipisci laborum distinctio aperiam et ab");
            Comment comment10 = new Comment(post5, "eaque et deleniti", 
                "carmen@example.com", "voluptate iusto quis nobis reprehenderit ipsum amet nulla quia quas dolores");
            Comment comment11 = new Comment(post5, "fugit labore quia", 
                "veronica@example.com", "deserunt cupiditate qui consequatur sed commodi odio quia voluptate");
            Comment comment12 = new Comment(post6, "totam quia non", 
                "oswald@example.com", "perspiciatis et quod ea consequuntur ad unde voluptate rerum");
            Comment comment13 = new Comment(post6, "doloribus quibusdam", 
                "kariane@example.com", "et impedit natus eligendi cumque qui sint distinctio quasi");
            Comment comment14 = new Comment(post7, "sint amet debitis", 
                "nathan@example.com", "maiores beatae molestias dolor quas rem neque sapiente voluptate modi");
            Comment comment15 = new Comment(post7, "expedita maiores", 
                "marilyne@example.com", "qui molestiae voluptatibus velit iure debitis aut repellat et qui");
            Comment comment16 = new Comment(post8, "totam omnis et", 
                "emile@example.com", "ad iusto omnis odit dolor voluptatibus recusandae numquam necessitatibus");
            Comment comment17 = new Comment(post8, "iusto et id", 
                "polly@example.com", "aut quo modi neque nostrum ducimus totam corporis tempore");
            Comment comment18 = new Comment(post9, "a deserunt omnis", 
                "matteo@example.com", "voluptatem repellendus ab blanditiis voluptatem perferendis qui natus");
            Comment comment19 = new Comment(post9, "sed ab est est", 
                "dallas@example.com", "perspiciatis quo laboriosam mollitia voluptas nisi voluptatem");
            Comment comment20 = new Comment(post10, "aliquid rerum mollitia", 
                "cassandra@example.com", "deleniti quia facilis ut sed odit nam et quas qui");
            Comment comment21 = new Comment(post10, "velit id earum", 
                "bernhard@example.com", "doloribus quo dolore et reprehenderit eligendi maxime voluptatem");
            Comment comment22 = new Comment(post11, "fugit et totam", 
                "elliot@example.com", "eos quo dolorem expedita impedit suscipit eaque et rerum");
            Comment comment23 = new Comment(post11, "et praesentium aliquam", 
                "nicola@example.com", "error quo maiores et quis neque molestiae perspiciatis");
            Comment comment24 = new Comment(post12, "ipsam aperiam voluptates", 
                "chris@example.com", "velit aperiam nam assumenda vel porro esse omnis fugiat");
            Comment comment25 = new Comment(post12, "sed qui error", 
                "donnell@example.com", "iusto est nobis adipisci soluta molestias et rerum pariatur");
            Comment comment26 = new Comment(post13, "voluptate omnis consectetur", 
                "gustave@example.com", "aut assumenda sint et quasi consequatur fugit blanditiis");
            Comment comment27 = new Comment(post13, "autem illo facilis", 
                "january@example.com", "dolorem quidem facilis et et voluptate ratione harum");
            Comment comment28 = new Comment(post14, "debitis magnam hic", 
                "vinnie@example.com", "vel quae voluptas qui exercitationem voluptatibus unde sed");
            Comment comment29 = new Comment(post14, "at voluptatem voluptas", 
                "lavada@example.com", "aut dicta possimus exercitationem ad voluptatem dolore ratione");
            Comment comment30 = new Comment(post15, "quo neque eum", 
                "kayla@example.com", "omnis et fugit nisi blanditiis nulla quia beatae");
            
            commentRepository.save(comment1);
            commentRepository.save(comment2);
            commentRepository.save(comment3);
            commentRepository.save(comment4);
            commentRepository.save(comment5);
            commentRepository.save(comment6);
            commentRepository.save(comment7);
            commentRepository.save(comment8);
            commentRepository.save(comment9);
            commentRepository.save(comment10);
            commentRepository.save(comment11);
            commentRepository.save(comment12);
            commentRepository.save(comment13);
            commentRepository.save(comment14);
            commentRepository.save(comment15);
            commentRepository.save(comment16);
            commentRepository.save(comment17);
            commentRepository.save(comment18);
            commentRepository.save(comment19);
            commentRepository.save(comment20);
            commentRepository.save(comment21);
            commentRepository.save(comment22);
            commentRepository.save(comment23);
            commentRepository.save(comment24);
            commentRepository.save(comment25);
            commentRepository.save(comment26);
            commentRepository.save(comment27);
            commentRepository.save(comment28);
            commentRepository.save(comment29);
            commentRepository.save(comment30);
            
            // Create 20 sample todos
            Todo todo1 = new Todo(user1, "delectus aut autem", false);
            Todo todo2 = new Todo(user1, "quis ut nam facilis et officia qui", false);
            Todo todo3 = new Todo(user1, "fugiat veniam minus", false);
            Todo todo4 = new Todo(user1, "et porro tempora", true);
            Todo todo5 = new Todo(user2, "laboriosam mollitia et enim", false);
            Todo todo6 = new Todo(user2, "qui ullam ratione quibusdam", true);
            Todo todo7 = new Todo(user3, "illo expedita consequatur", false);
            Todo todo8 = new Todo(user3, "quo adipisci enim quam", true);
            Todo todo9 = new Todo(user4, "molestiae perspiciatis ipsa", false);
            Todo todo10 = new Todo(user4, "illo est ratione doloremque", true);
            Todo todo11 = new Todo(user5, "vero rerum temporibus", true);
            Todo todo12 = new Todo(user5, "ipsa repellendus fugit nisi", false);
            Todo todo13 = new Todo(user6, "et doloremque nulla", false);
            Todo todo14 = new Todo(user6, "repellendus sunt dolores", true);
            Todo todo15 = new Todo(user7, "ab quas odio", false);
            Todo todo16 = new Todo(user7, "accusamus eos facilis", true);
            Todo todo17 = new Todo(user8, "quo laboriosam deleniti", false);
            Todo todo18 = new Todo(user9, "molestiae ipsa aut", true);
            Todo todo19 = new Todo(user9, "ullam nobis libero", false);
            Todo todo20 = new Todo(user10, "suscipit repellat esse", false);
            
            todoRepository.save(todo1);
            todoRepository.save(todo2);
            todoRepository.save(todo3);
            todoRepository.save(todo4);
            todoRepository.save(todo5);
            todoRepository.save(todo6);
            todoRepository.save(todo7);
            todoRepository.save(todo8);
            todoRepository.save(todo9);
            todoRepository.save(todo10);
            todoRepository.save(todo11);
            todoRepository.save(todo12);
            todoRepository.save(todo13);
            todoRepository.save(todo14);
            todoRepository.save(todo15);
            todoRepository.save(todo16);
            todoRepository.save(todo17);
            todoRepository.save(todo18);
            todoRepository.save(todo19);
            todoRepository.save(todo20);
            
            // Create 10 sample albums
            Album album1 = new Album(user1, "quidem molestiae enim");
            Album album2 = new Album(user1, "sunt qui excepturi placeat culpa");
            Album album3 = new Album(user2, "omnis laborum odio");
            Album album4 = new Album(user2, "non esse culpa molestiae");
            Album album5 = new Album(user3, "eaque aut omnis a");
            Album album6 = new Album(user4, "voluptatem ut ipsam");
            Album album7 = new Album(user5, "sed qui sed");
            Album album8 = new Album(user6, "voluptate omnis minima");
            Album album9 = new Album(user7, "amet amet excepturi");
            Album album10 = new Album(user8, "beatae et provident");
            
            albumRepository.save(album1);
            albumRepository.save(album2);
            albumRepository.save(album3);
            albumRepository.save(album4);
            albumRepository.save(album5);
            albumRepository.save(album6);
            albumRepository.save(album7);
            albumRepository.save(album8);
            albumRepository.save(album9);
            albumRepository.save(album10);
            
            // Create 25 sample photos
            Photo photo1 = new Photo(album1, "accusamus beatae ad facilis cum similique qui sunt",
                "https://via.placeholder.com/600/92c952", "https://via.placeholder.com/150/92c952");
            Photo photo2 = new Photo(album1, "reprehenderit est deserunt velit ipsam",
                "https://via.placeholder.com/600/771796", "https://via.placeholder.com/150/771796");
            Photo photo3 = new Photo(album1, "officia porro iure quia iusto qui ipsa ut modi",
                "https://via.placeholder.com/600/24f355", "https://via.placeholder.com/150/24f355");
            Photo photo4 = new Photo(album2, "culpa odio esse rerum omnis laboriosam",
                "https://via.placeholder.com/600/d32776", "https://via.placeholder.com/150/d32776");
            Photo photo5 = new Photo(album2, "natus nisi omnis corporis facere molestiae",
                "https://via.placeholder.com/600/f66b97", "https://via.placeholder.com/150/f66b97");
            Photo photo6 = new Photo(album3, "accusamus ea aliquid et amet sequi",
                "https://via.placeholder.com/600/56a8c2", "https://via.placeholder.com/150/56a8c2");
            Photo photo7 = new Photo(album3, "officia delectus consequatur vero aut veniam",
                "https://via.placeholder.com/600/b0f7cc", "https://via.placeholder.com/150/b0f7cc");
            Photo photo8 = new Photo(album4, "aut porro officiis laborum odit ea",
                "https://via.placeholder.com/600/54176f", "https://via.placeholder.com/150/54176f");
            Photo photo9 = new Photo(album4, "qui eius qui autem sed",
                "https://via.placeholder.com/600/51aa97", "https://via.placeholder.com/150/51aa97");
            Photo photo10 = new Photo(album5, "beatae et provident et ut vel",
                "https://via.placeholder.com/600/810b14", "https://via.placeholder.com/150/810b14");
            Photo photo11 = new Photo(album5, "nihil at amet non hic quia qui",
                "https://via.placeholder.com/600/1ee8a4", "https://via.placeholder.com/150/1ee8a4");
            Photo photo12 = new Photo(album6, "mollitia soluta ut rerum eos aliquam",
                "https://via.placeholder.com/600/66b7d2", "https://via.placeholder.com/150/66b7d2");
            Photo photo13 = new Photo(album6, "repudiandae iusto deleniti rerum",
                "https://via.placeholder.com/600/197d29", "https://via.placeholder.com/150/197d29");
            Photo photo14 = new Photo(album7, "est necessitatibus architecto ut laborum",
                "https://via.placeholder.com/600/61a65", "https://via.placeholder.com/150/61a65");
            Photo photo15 = new Photo(album7, "harum dicta similique quis",
                "https://via.placeholder.com/600/f9cee5", "https://via.placeholder.com/150/f9cee5");
            Photo photo16 = new Photo(album8, "iusto sunt nobis quasi veritatis",
                "https://via.placeholder.com/600/fdf73e", "https://via.placeholder.com/150/fdf73e");
            Photo photo17 = new Photo(album8, "natus doloribus necessitatibus ipsa",
                "https://via.placeholder.com/600/9c184f", "https://via.placeholder.com/150/9c184f");
            Photo photo18 = new Photo(album9, "laboriosam odit nam necessitatibus",
                "https://via.placeholder.com/600/1fe46f", "https://via.placeholder.com/150/1fe46f");
            Photo photo19 = new Photo(album9, "perferendis nesciunt eveniet et optio",
                "https://via.placeholder.com/600/56acb2", "https://via.placeholder.com/150/56acb2");
            Photo photo20 = new Photo(album10, "assumenda voluptatem laboriosam enim",
                "https://via.placeholder.com/600/8985dc", "https://via.placeholder.com/150/8985dc");
            Photo photo21 = new Photo(album10, "voluptatem accusamus eveniet et omnis",
                "https://via.placeholder.com/600/5e12c6", "https://via.placeholder.com/150/5e12c6");
            Photo photo22 = new Photo(album1, "eveniet perspiciatis voluptate qui",
                "https://via.placeholder.com/600/9d1f00", "https://via.placeholder.com/150/9d1f00");
            Photo photo23 = new Photo(album2, "provident vel ut sit ratione",
                "https://via.placeholder.com/600/b19cd9", "https://via.placeholder.com/150/b19cd9");
            Photo photo24 = new Photo(album3, "veritatis sed repellendus modi officiis",
                "https://via.placeholder.com/600/1771b5", "https://via.placeholder.com/150/1771b5");
            Photo photo25 = new Photo(album4, "est adipisci blanditiis ea",
                "https://via.placeholder.com/600/e924e6", "https://via.placeholder.com/150/e924e6");
            
            photoRepository.save(photo1);
            photoRepository.save(photo2);
            photoRepository.save(photo3);
            photoRepository.save(photo4);
            photoRepository.save(photo5);
            photoRepository.save(photo6);
            photoRepository.save(photo7);
            photoRepository.save(photo8);
            photoRepository.save(photo9);
            photoRepository.save(photo10);
            photoRepository.save(photo11);
            photoRepository.save(photo12);
            photoRepository.save(photo13);
            photoRepository.save(photo14);
            photoRepository.save(photo15);
            photoRepository.save(photo16);
            photoRepository.save(photo17);
            photoRepository.save(photo18);
            photoRepository.save(photo19);
            photoRepository.save(photo20);
            photoRepository.save(photo21);
            photoRepository.save(photo22);
            photoRepository.save(photo23);
            photoRepository.save(photo24);
            photoRepository.save(photo25);
            
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
