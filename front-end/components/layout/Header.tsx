'use client';

import { useState } from 'react';
import { Moon, Sun, User } from 'lucide-react';
import { Button } from '@/components/ui/button';

export function Header() {
  const [isDark, setIsDark] = useState(false);

  return (
    <header className="h-16 border-b border-slate-200 bg-white px-6 flex items-center justify-between">
      <div>
        <h2 className="text-lg font-semibold text-slate-900">Welcome back</h2>
        <p className="text-sm text-slate-500">Manage your data efficiently</p>
      </div>

      <div className="flex items-center gap-3">
        <Button
          variant="outline"
          size="sm"
          onClick={() => setIsDark(!isDark)}
        >
          {isDark ? <Sun className="h-4 w-4" /> : <Moon className="h-4 w-4" />}
        </Button>
        
        <div className="flex items-center gap-2 px-3 py-1.5 rounded-lg bg-slate-100">
          <div className="h-8 w-8 rounded-full bg-blue-600 flex items-center justify-center">
            <User className="h-4 w-4 text-white" />
          </div>
          <span className="text-sm font-medium text-slate-700">Admin</span>
        </div>
      </div>
    </header>
  );
}
