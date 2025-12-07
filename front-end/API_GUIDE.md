# API Integration Guide

## Base Configuration

The API client is configured in `/core/infrastructure/api-client.ts` and uses environment variables.

### Environment Setup
```bash
# .env.local
NEXT_PUBLIC_API_BASE_URL=http://localhost:8080
```

## Usage Examples

### Using Repositories Directly

```typescript
import { userRepository } from '@/features/users/data/user.repository';

// Get all users
const users = await userRepository.getAll();

// Get user by ID
const user = await userRepository.getById(1);

// Create user
const newUser = await userRepository.create({
  name: 'John Doe',
  username: 'johndoe',
  email: 'john@example.com',
  phone: '123-456-7890',
  website: 'johndoe.com'
});

// Update user (partial)
const updated = await userRepository.partialUpdate(1, {
  email: 'newemail@example.com'
});

// Delete user
await userRepository.delete(1);
```

### Using React Hooks

```typescript
import { useUsers } from '@/features/users/hooks/useUsers';

function MyComponent() {
  const { users, loading, error, createUser, updateUser, deleteUser } = useUsers();

  // Users are automatically fetched on mount
  // State is managed for you
  
  if (loading) return <LoadingSpinner />;
  if (error) return <Error message={error} />;
  
  return (
    <div>
      {users.map(user => (
        <UserCard key={user.id} user={user} />
      ))}
    </div>
  );
}
```

## Available Endpoints

### Users API

```typescript
// GET /users
userRepository.getAll(): Promise<User[]>

// GET /users/{id}
userRepository.getById(id: number): Promise<User>

// POST /users
userRepository.create(user: Omit<User, 'id'>): Promise<User>

// PUT /users/{id}
userRepository.update(id: number, user: Partial<User>): Promise<User>

// PATCH /users/{id}
userRepository.partialUpdate(id: number, user: Partial<User>): Promise<User>

// DELETE /users/{id}
userRepository.delete(id: number): Promise<void>
```

### Posts API

```typescript
// GET /posts
postRepository.getAll(): Promise<Post[]>

// GET /posts?userId={userId}
postRepository.getByUserId(userId: number): Promise<Post[]>

// GET /posts/{id}
postRepository.getById(id: number): Promise<Post>

// POST /posts
postRepository.create(post: Omit<Post, 'id'>): Promise<Post>

// PATCH /posts/{id}
postRepository.partialUpdate(id: number, post: Partial<Post>): Promise<Post>

// DELETE /posts/{id}
postRepository.delete(id: number): Promise<void>
```

### Comments API

```typescript
// GET /comments
commentRepository.getAll(): Promise<Comment[]>

// GET /comments?postId={postId}
commentRepository.getByPostId(postId: number): Promise<Comment[]>

// POST /comments
commentRepository.create(comment: Omit<Comment, 'id'>): Promise<Comment>

// DELETE /comments/{id}
commentRepository.delete(id: number): Promise<void>
```

### Todos API

```typescript
// GET /todos
todoRepository.getAll(): Promise<Todo[]>

// GET /todos?userId={userId}
todoRepository.getByUserId(userId: number): Promise<Todo[]>

// POST /todos
todoRepository.create(todo: Omit<Todo, 'id'>): Promise<Todo>

// PATCH /todos/{id}
todoRepository.partialUpdate(id: number, todo: Partial<Todo>): Promise<Todo>

// DELETE /todos/{id}
todoRepository.delete(id: number): Promise<void>
```

### Albums API

```typescript
// GET /albums
albumRepository.getAll(): Promise<Album[]>

// GET /albums?userId={userId}
albumRepository.getByUserId(userId: number): Promise<Album[]>

// DELETE /albums/{id}
albumRepository.delete(id: number): Promise<void>
```

### Photos API

```typescript
// GET /photos
photoRepository.getAll(): Promise<Photo[]>

// GET /photos?albumId={albumId}
photoRepository.getByAlbumId(albumId: number): Promise<Photo[]>

// DELETE /photos/{id}
photoRepository.delete(id: number): Promise<void>
```

## Error Handling

The API client automatically handles errors with interceptors:

```typescript
try {
  const user = await userRepository.getById(1);
} catch (error) {
  // Error is logged to console
  // Handle in your component
  console.error('Failed to fetch user:', error);
}
```

## TypeScript Types

All domain types are defined in `/core/types/index.ts`:

```typescript
interface User {
  id: number;
  name: string;
  username: string;
  email: string;
  phone: string;
  website: string;
}

interface Post {
  id: number;
  user: { id: number };
  title: string;
  body: string;
}

interface Comment {
  id: number;
  post: { id: number };
  name: string;
  email: string;
  body: string;
}

interface Todo {
  id: number;
  user: { id: number };
  title: string;
  completed: boolean;
}

interface Album {
  id: number;
  user: { id: number };
  title: string;
}

interface Photo {
  id: number;
  album: { id: number };
  title: string;
  url: string;
  thumbnailUrl: string;
}
```

## Adding New Endpoints

1. Add type to `/core/types/index.ts`
2. Add endpoint to `/core/config/api.config.ts`
3. Create repository in `/features/{feature}/data/`
4. Create hook in `/features/{feature}/hooks/`
5. Create components in `/features/{feature}/components/`
6. Create page in `/app/{feature}/page.tsx`
