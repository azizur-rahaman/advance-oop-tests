'use client';

import { Todo } from '@/core/types';
import { Card, CardContent } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { Badge } from '@/components/ui/badge';
import { Trash2, Check, X } from 'lucide-react';

interface TodoItemProps {
  todo: Todo;
  onToggle: (id: number, completed: boolean) => void;
  onDelete: (id: number) => void;
}

export function TodoItem({ todo, onToggle, onDelete }: TodoItemProps) {
  return (
    <Card className={`transition-all ${todo.completed ? 'opacity-60' : ''}`}>
      <CardContent className="flex items-center gap-3 p-4">
        <button
          onClick={() => onToggle(todo.id, !todo.completed)}
          className={`flex-shrink-0 h-5 w-5 rounded border-2 transition-all ${
            todo.completed
              ? 'bg-green-500 border-green-500'
              : 'border-slate-300 hover:border-blue-600'
          }`}
        >
          {todo.completed && <Check className="h-4 w-4 text-white" />}
        </button>
        
        <div className="flex-1">
          <p className={`text-sm ${todo.completed ? 'line-through text-slate-500' : 'text-slate-900'}`}>
            {todo.title}
          </p>
          <p className="text-xs text-slate-400 mt-1">User ID: {todo.user.id}</p>
        </div>

        <Badge variant={todo.completed ? 'success' : 'default'}>
          {todo.completed ? 'Completed' : 'Pending'}
        </Badge>

        <Button
          variant="danger"
          size="sm"
          onClick={() => onDelete(todo.id)}
        >
          <Trash2 className="h-4 w-4" />
        </Button>
      </CardContent>
    </Card>
  );
}
