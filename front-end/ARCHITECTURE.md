# DataHub Frontend - Clean Architecture Implementation

A modern Next.js frontend application following clean architecture principles and the JSONPlaceholder API design.

## 🏗️ Architecture Overview

This project implements **Clean Architecture** with clear separation of concerns:

```
/app                    # Next.js 15 App Router pages
/components            # Reusable UI components
  /ui                  # Atomic design components (Button, Input, Card, etc.)
  /layout              # Layout components (Sidebar, Header, DashboardLayout)
/core                  # Core domain layer
  /config              # Configuration files (API endpoints, settings)
  /infrastructure      # External service implementations (API client)
  /types               # Domain entities and interfaces
/features              # Feature modules (bounded contexts)
  /users
    /data              # Data layer (repositories)
    /hooks             # React hooks (use cases/application layer)
    /components        # Feature-specific components
  /posts
  /comments
  /todos
  /albums
  /photos
```

## 🎯 Design Principles

### Clean Architecture Layers

1. **Domain Layer** (`/core/types`)
   - Pure TypeScript interfaces
   - No dependencies on external libraries
   - Represents business entities

2. **Infrastructure Layer** (`/core/infrastructure`)
   - API client implementation using Axios
   - External service integrations
   - Framework-specific code

3. **Data Layer** (`/features/*/data`)
   - Repository pattern implementation
   - Data access abstraction
   - API endpoint mappings

4. **Application Layer** (`/features/*/hooks`)
   - Use cases as React hooks
   - Business logic coordination
   - State management

5. **Presentation Layer** (`/features/*/components`, `/app`)
   - React components
   - UI logic only
   - Framework-specific (Next.js)

## 🎨 UI Design System

Following the design system specification:

### Color Palette
- **Primary**: `#2563EB` (blue-600)
- **Secondary**: `#64748B` (slate-500)
- **Accent**: `#22C55E` (green-500)
- **Danger**: `#EF4444` (red-500)
- **Background**: `#F8FAFC` (slate-50)

### Typography
- **Font Family**: Inter (system font)
- **Headings**: 24-32px, 600-700 weight
- **Body**: 14-16px, 400 weight

### Components
All components follow Tailwind CSS styling with:
- Rounded corners (`rounded-lg`, `rounded-2xl`)
- Subtle shadows (`shadow-sm`)
- Smooth transitions (`transition-all duration-200`)
- Hover effects

## 📁 Project Structure

### Core Layer

**`/core/config/api.config.ts`**
- API base URL configuration
- Endpoint constants
- Default headers

**`/core/infrastructure/api-client.ts`**
- Axios instance setup
- Request/response interceptors
- Generic HTTP methods (GET, POST, PUT, PATCH, DELETE)
- Error handling

**`/core/types/index.ts`**
- Domain entities (User, Post, Comment, Todo, Album, Photo)
- API response types
- Error types

### Feature Modules

Each feature follows the same structure:

**Data Layer** (`/features/{feature}/data/`)
- Repository classes
- CRUD operations
- API integration

**Application Layer** (`/features/{feature}/hooks/`)
- Custom React hooks
- State management
- Use case orchestration

**Presentation Layer** (`/features/{feature}/components/`)
- Feature-specific components
- Forms, cards, lists

### UI Components

**Atomic Components** (`/components/ui/`)
- `Button` - Multiple variants (primary, secondary, outline, danger)
- `Input` - With error state support
- `Textarea` - Multi-line input
- `Card` - Container with header, content, footer
- `Badge` - Status indicators
- `Modal` - Dialog system with backdrop
- `Toast` - Notification system
- `Loading` - Spinner components

**Layout Components** (`/components/layout/`)
- `Sidebar` - Navigation menu
- `Header` - Top bar with user info
- `DashboardLayout` - Main layout wrapper

## 🚀 Getting Started

### Prerequisites
- Node.js 18+
- npm or yarn

### Installation

1. Install dependencies:
```bash
npm install
```

2. Create environment file:
```bash
# .env.local
NEXT_PUBLIC_API_BASE_URL=http://localhost:8080
```

3. Run development server:
```bash
npm run dev
```

4. Open [http://localhost:3000](http://localhost:3000)

## 🔌 API Integration

The application connects to a backend API (Spring Boot) with the following endpoints:

- `/users` - User management
- `/posts` - Blog posts
- `/comments` - Post comments
- `/todos` - Task management
- `/albums` - Photo albums
- `/photos` - Photo management

### Environment Variables

- `NEXT_PUBLIC_API_BASE_URL` - Backend API URL (default: http://localhost:8080)

## 📦 Dependencies

### Core
- **Next.js 15** - React framework with App Router
- **React 19** - UI library
- **TypeScript** - Type safety
- **Axios** - HTTP client

### UI
- **Tailwind CSS 4** - Utility-first styling
- **lucide-react** - Icon library
- **class-variance-authority** - Component variants
- **clsx** & **tailwind-merge** - Class name utilities

## 🧪 Features

### Users
- List all users
- Create new user
- Edit user details
- Delete user
- View user cards with contact info

### Posts
- List all posts
- Create new post
- Edit post
- Delete post
- Filter by user

### Comments
- List all comments
- Delete comment
- View by post

### Todos
- List all todos
- Create new todo
- Toggle completion status
- Delete todo
- Filter by user

### Albums
- List all albums
- Delete album
- Filter by user

### Photos
- Grid view of photos
- Delete photo
- Filter by album

## 🎯 Key Features

### Clean Code Practices
- Single Responsibility Principle
- Dependency Inversion
- Repository Pattern
- Custom Hooks for Use Cases
- Component Composition

### User Experience
- Loading states
- Error handling
- Toast notifications
- Confirmation dialogs
- Responsive design
- Smooth animations

### Developer Experience
- TypeScript for type safety
- ESLint for code quality
- Hot reload
- Clear folder structure
- Reusable components

## 🔄 Data Flow

1. **User Action** → Component event handler
2. **Hook/Use Case** → Business logic execution
3. **Repository** → API call via ApiClient
4. **ApiClient** → HTTP request with Axios
5. **Backend API** → Process request
6. **Response** → Flow back through layers
7. **State Update** → Component re-render

## 🛠️ Build & Deploy

```bash
# Build for production
npm run build

# Start production server
npm start

# Lint code
npm run lint
```

## 📝 Best Practices

1. **Keep components small and focused**
2. **Use custom hooks for business logic**
3. **Keep API calls in repositories**
4. **Use TypeScript for type safety**
5. **Follow the design system**
6. **Handle errors gracefully**
7. **Provide loading states**
8. **Use environment variables for config**

## 🔐 Security Considerations

- Environment variables for sensitive config
- Client-side validation
- Error message sanitization
- CORS handling in API client

## 📚 Additional Resources

- [Next.js Documentation](https://nextjs.org/docs)
- [Tailwind CSS](https://tailwindcss.com)
- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Repository Pattern](https://martinfowler.com/eaaCatalog/repository.html)

## 📄 License

MIT License - feel free to use this project for learning and development.
