# Quick Command Reference

## Development Commands

```bash
# Install dependencies
npm install

# Run development server
npm run dev

# Build for production
npm run build

# Start production server
npm start

# Run linter
npm run lint
```

## Environment Setup

```bash
# Copy environment template
cp .env.local.example .env.local

# Edit environment variables
nano .env.local
# or
code .env.local
```

## Testing the API Integration

### Start Backend First
```bash
# In backend directory
./mvnw spring-boot:run
# Backend should be running on http://localhost:8080
```

### Start Frontend
```bash
# In front-end directory
npm run dev
# Frontend will be on http://localhost:3000
```

## Useful Development Tips

### Check TypeScript Types
```bash
npx tsc --noEmit
```

### Format Code (if prettier is added)
```bash
npx prettier --write .
```

### Clean Build
```bash
rm -rf .next
npm run build
```

## File Creation Patterns

### Adding a New Feature

1. **Create Types** (if needed)
```typescript
// core/types/index.ts
export interface MyEntity {
  id: number;
  name: string;
}
```

2. **Add Endpoint**
```typescript
// core/config/api.config.ts
export const API_ENDPOINTS = {
  // ... existing
  MY_ENTITY: '/my-entity',
}
```

3. **Create Repository**
```typescript
// features/my-entity/data/my-entity.repository.ts
export class MyEntityRepository {
  async getAll() {
    return apiClient.get(API_ENDPOINTS.MY_ENTITY);
  }
}
```

4. **Create Hook**
```typescript
// features/my-entity/hooks/useMyEntity.ts
export const useMyEntity = () => {
  const [data, setData] = useState([]);
  // ... implementation
  return { data, loading, error };
};
```

5. **Create Component**
```typescript
// features/my-entity/components/MyEntityCard.tsx
export function MyEntityCard({ entity }) {
  return <Card>...</Card>;
}
```

6. **Create Page**
```typescript
// app/my-entity/page.tsx
export default function MyEntityPage() {
  const { data } = useMyEntity();
  return <DashboardLayout>...</DashboardLayout>;
}
```

## Debugging

### Check API Connection
```bash
# Test backend is running
curl http://localhost:8080/users

# Check frontend environment
cat .env.local
```

### View Network Requests
Open Chrome DevTools → Network tab → Filter by XHR

### View Console Errors
Open Chrome DevTools → Console tab

## Common Issues

### Port Already in Use
```bash
# Kill process on port 3000
lsof -ti:3000 | xargs kill -9

# Or use different port
PORT=3001 npm run dev
```

### Module Not Found
```bash
# Clear cache and reinstall
rm -rf node_modules package-lock.json
npm install
```

### TypeScript Errors
```bash
# Restart TypeScript server in VS Code
Cmd+Shift+P → "TypeScript: Restart TS Server"
```

## Git Commands

```bash
# Check status
git status

# Stage changes
git add .

# Commit
git commit -m "feat: implement clean architecture"

# Push
git push origin main
```

## Project Structure Quick Reference

```
Key Files:
├── .env.local                    # Environment config
├── package.json                  # Dependencies
├── tsconfig.json                 # TypeScript config
├── next.config.ts                # Next.js config
├── tailwind.config.js            # Tailwind config
│
Entry Points:
├── app/layout.tsx                # Root layout
├── app/page.tsx                  # Dashboard
├── core/infrastructure/api-client.ts  # API setup
│
Feature Template:
└── features/example/
    ├── data/example.repository.ts     # Data access
    ├── hooks/useExample.ts            # Business logic
    └── components/ExampleCard.tsx     # UI component
```

## VS Code Shortcuts

```
Cmd+P                 - Quick file open
Cmd+Shift+P          - Command palette
Cmd+B                - Toggle sidebar
Cmd+`                - Toggle terminal
Cmd+Shift+F          - Global search
Cmd+Click            - Go to definition
F2                   - Rename symbol
```

## Helpful URLs

```
Frontend:        http://localhost:3000
Backend API:     http://localhost:8080
API Docs:        See API_GUIDE.md
Architecture:    See CLEAN_ARCHITECTURE.md
```

## Production Build

```bash
# Build optimized production bundle
npm run build

# Test production build locally
npm start

# Environment for production
NEXT_PUBLIC_API_BASE_URL=https://api.production.com npm run build
```

## Performance

```bash
# Analyze bundle size
npm run build
# Check .next/build-manifest.json

# Check for unused dependencies
npx depcheck
```

---

Keep this file handy for quick reference! 🚀
