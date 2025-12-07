# 🎨 UI Design System (v1)

_For JSONPlaceholder-like frontend (React, Vue, or Angular compatible)_

---

## 1️⃣ Brand & Visual Language

**Goal:** Developer-focused, minimal distraction, fast data management.

**Brand attributes:**  
🟢 Modern ⚪ Minimal 🔵 Reliable 🟣 Technical

### 🎨 Color Palette

| Purpose | Color |
|----------|--------|
| Primary | `#2563EB` (blue-600) |
| Secondary | `#64748B` (slate-500) |
| Accent | `#22C55E` (green-500) |
| Danger | `#EF4444` (red-500) |
| Background | `#F8FAFC` (slate-50) |
| Surface | `#FFFFFF` |
| Border | `#E2E8F0` |
| Text Primary | `#1E293B` |
| Text Secondary | `#64748B` |

---

## 2️⃣ Typography

| Element | Font | Size | Weight |
|----------|------|------|--------|
| Title / Headings | Inter / Poppins | 24–32px | 600–700 |
| Subtitle | Inter | 18px | 500 |
| Body | Inter | 14–16px | 400 |
| Code / JSON View | JetBrains Mono | 13px | 400 |

---

## 3️⃣ Components Library

All components follow **Atomic Design** (atoms → molecules → organisms).  
Implement easily in **React (Next.js)** using **Tailwind + shadcn/ui**.

### 🔹 Atoms
- Button — variants: `primary`, `secondary`, `outline`, `danger`
- Input / Textarea
- Select / Dropdown
- Badge — for status (active, completed)
- Avatar
- Loading Spinner
- Card — rounded `2xl`, shadow-sm, white background
- Modal/Dialog
- Toast/Alert (success, error, info)

### 🔹 Molecules
- Resource Card — shows title, short body, and actions (edit, delete)
- Form Group — input + label + error text
- Toolbar — actions + filters (Search, Sort, Add)
- Confirm Dialog — for delete confirmation
- Table Row / List Item — compact, with icons

### 🔹 Organisms
- **Resource Table / List View**
  - Columns: ID, Title, Owner, Actions  
  - Pagination + Sorting + Search Bar  
- **Resource Form (Create / Edit)**
  - Text fields for title/body, etc.  
  - Buttons: Save, Cancel  
  - Inline validation  
- **Details View**
  - Shows full JSON (pretty-printed)  
  - “Copy JSON” button  
  - Related resources (e.g., comments under post)  
- **Dashboard Layout**
  - Left sidebar: Navigation (Users, Posts, Comments, Todos, Albums, Photos)  
  - Topbar: App name + theme toggle + user avatar  
  - Main area: Page content  
  - Global floating “+” button for quick add  

---

## 4️⃣ Layout System

**Grid:**
- 12-column responsive grid  
- Gaps: `1.5rem`  
- Breakpoints:  
  - `sm`: 640px  
  - `md`: 768px  
  - `lg`: 1024px  
  - `xl`: 1280px  

**Card-based Layout:**
- Use cards to show list items or JSON previews  
- Avoid full-width tables — keep content boxed (~1200px max width)

---

## 5️⃣ Interaction Patterns

- Hover: subtle shadow / scale effect (`transition-all 200ms`)
- Click: ripple or background tint
- Form validation: inline error messages (`text-red-500 text-sm`)
- Deletion: confirmation dialog
- Toasts: appear top-right, auto-dismiss in 3s
- Dark mode toggle (background `#0F172A`, text `#E2E8F0`)

---

## 6️⃣ Example Screen Flow

| Screen | Description |
|---------|--------------|
| **Dashboard** | Summary stats (total users, posts, todos) + quick links |
| **Users List** | Table with CRUD actions |
| **User Details** | Info + related posts/albums |
| **Posts List** | List + search bar + filter by user |
| **Post Details** | Full post + comments + edit/delete |
| **Create Post** | Form for new post |
| **Todos** | Checklist-style list |
| **Albums & Photos** | Masonry grid with images |
