# DataHub Frontend

A modern Next.js application built with **Clean Architecture** principles, TypeScript, and Tailwind CSS.

## 🚀 Quick Start

```bash
# Install dependencies
npm install

# Set up environment
cp .env.local.example .env.local
# Edit .env.local and set NEXT_PUBLIC_API_BASE_URL=http://localhost:8080

# Run development server
npm run dev
```

Open [http://localhost:3000](http://localhost:3000) in your browser.

## 📋 Prerequisites

- Node.js 18+
- Backend API running on port 8080 (see backend folder)
- npm or yarn

## 🏗️ Architecture

This project implements **Clean Architecture** with feature-based modules:

```
📁 Project Structure
├── /app                  # Next.js pages (Presentation)
├── /core                 # Core domain & infrastructure
│   ├── /config          # API configuration
│   ├── /infrastructure  # API client (Axios)
│   └── /types           # Domain entities
├── /features            # Feature modules
│   ├── /users           # User management
│   │   ├── /data       # Repository (Data Access)
│   │   ├── /hooks      # Use Cases (Business Logic)
│   │   └── /components # UI Components
│   ├── /posts
│   ├── /comments
│   ├── /todos
│   ├── /albums
│   └── /photos
└── /components          # Shared UI components
    ├── /ui             # Design system components
    └── /layout         # Layout components
```

### Key Principles

✅ **Separation of Concerns** - Each layer has a single responsibility  
✅ **Dependency Inversion** - Core doesn't depend on infrastructure  
✅ **Repository Pattern** - Data access abstraction  
✅ **Custom Hooks** - Encapsulated business logic  
✅ **Atomic Design** - Reusable UI components  

## 🎨 Design System

Following the design system specification in `/docs/design-system.md`:

- **Colors**: Blue primary, Slate secondary, semantic colors
- **Typography**: Inter font family, consistent sizing
- **Components**: Button, Input, Card, Modal, Toast, etc.
- **Responsive**: Mobile-first, 12-column grid

## 📦 Features

### Implemented Pages

- **Dashboard** - Overview with statistics
- **Users** - CRUD operations for users
- **Posts** - Create, edit, delete blog posts
- **Comments** - View and manage comments
- **Todos** - Task management with completion toggle
- **Albums** - Photo album management
- **Photos** - Photo gallery with grid view

### UI Components

- Sidebar navigation with active state
- Toast notifications (success/error)
- Confirmation modals for destructive actions
- Loading states and error handling
- Responsive cards and forms

## 🔌 API Integration

Uses Axios with clean repository pattern:

```typescript
// Direct repository usage
import { userRepository } from '@/features/users/data/user.repository';
const users = await userRepository.getAll();

// React hook usage (recommended)
import { useUsers } from '@/features/users/hooks/useUsers';
const { users, loading, createUser, deleteUser } = useUsers();
```

See [API_GUIDE.md](./API_GUIDE.md) for detailed documentation.

## 🛠️ Tech Stack

- **Framework**: Next.js 15 (App Router)
- **Language**: TypeScript
- **Styling**: Tailwind CSS 4
- **HTTP Client**: Axios
- **Icons**: Lucide React
- **Utilities**: clsx, class-variance-authority

## 📚 Documentation

- [ARCHITECTURE.md](./ARCHITECTURE.md) - Detailed architecture documentation
- [API_GUIDE.md](./API_GUIDE.md) - API integration guide
- [docs/design-system.md](./docs/design-system.md) - UI design system

## 🧪 Development

```bash
# Run development server
npm run dev

# Build for production
npm run build

# Start production server
npm start

# Lint code
npm run lint
```

## 🌍 Environment Variables

```bash
# .env.local
NEXT_PUBLIC_API_BASE_URL=http://localhost:8080
```

## 📝 Code Examples

### Creating a New Feature

1. Add types to `/core/types/index.ts`
2. Create repository in `/features/myfeature/data/`
3. Create hook in `/features/myfeature/hooks/`
4. Create components in `/features/myfeature/components/`
5. Create page in `/app/myfeature/page.tsx`

### Using the API

```typescript
'use client';

import { useUsers } from '@/features/users/hooks/useUsers';
import { UserCard } from '@/features/users/components/UserCard';
import { LoadingPage } from '@/components/ui/loading';

export default function UsersPage() {
  const { users, loading, error, deleteUser } = useUsers();

  if (loading) return <LoadingPage />;
  if (error) return <div>Error: {error}</div>;

  return (
    <div className="grid grid-cols-3 gap-6">
      {users.map(user => (
        <UserCard key={user.id} user={user} onDelete={deleteUser} />
      ))}
    </div>
  );
}
```

## 🤝 Contributing

1. Follow the existing architecture patterns
2. Use TypeScript for type safety
3. Follow the design system
4. Add proper error handling
5. Include loading states

## 📄 License

MIT License

---

**Built with Clean Architecture & Modern Web Technologies**

