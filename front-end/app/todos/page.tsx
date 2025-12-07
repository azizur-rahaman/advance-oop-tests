'use client';

import { useState } from 'react';
import { DashboardLayout } from '@/components/layout/DashboardLayout';
import { useTodos } from '@/features/todos/hooks/useTodos';
import { TodoItem } from '@/features/todos/components/TodoItem';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Modal } from '@/components/ui/modal';
import { LoadingPage } from '@/components/ui/loading';
import { useToast } from '@/components/ui/toast';
import { Plus } from 'lucide-react';

export default function TodosPage() {
  const { todos, loading, error, createTodo, toggleTodo, deleteTodo } = useTodos();
  const [isCreateModalOpen, setIsCreateModalOpen] = useState(false);
  const [newTodo, setNewTodo] = useState({ title: '', userId: 1 });
  const { showToast, ToastComponent } = useToast();

  const handleCreate = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await createTodo({
        title: newTodo.title,
        user: { id: newTodo.userId },
        completed: false,
      });
      setIsCreateModalOpen(false);
      setNewTodo({ title: '', userId: 1 });
      showToast({ message: 'Todo created successfully', type: 'success' });
    } catch (err) {
      showToast({ message: 'Failed to create todo', type: 'error' });
    }
  };

  const handleToggle = async (id: number, completed: boolean) => {
    try {
      await toggleTodo(id, completed);
    } catch (err) {
      showToast({ message: 'Failed to update todo', type: 'error' });
    }
  };

  const handleDelete = async (id: number) => {
    try {
      await deleteTodo(id);
      showToast({ message: 'Todo deleted successfully', type: 'success' });
    } catch (err) {
      showToast({ message: 'Failed to delete todo', type: 'error' });
    }
  };

  if (loading && todos.length === 0) return <DashboardLayout><LoadingPage /></DashboardLayout>;

  return (
    <DashboardLayout>
      {ToastComponent}
      <div className="space-y-6">
        <div className="flex items-center justify-between">
          <div>
            <h1 className="text-3xl font-bold text-slate-900">Todos</h1>
            <p className="text-slate-500 mt-1">Manage your tasks</p>
          </div>
          <Button onClick={() => setIsCreateModalOpen(true)}>
            <Plus className="h-4 w-4 mr-2" />
            Add Todo
          </Button>
        </div>

        {error && (
          <div className="bg-red-50 text-red-900 p-4 rounded-lg">
            {error}
          </div>
        )}

        <div className="space-y-3">
          {todos.map((todo) => (
            <TodoItem
              key={todo.id}
              todo={todo}
              onToggle={handleToggle}
              onDelete={handleDelete}
            />
          ))}
        </div>

        {todos.length === 0 && !loading && (
          <div className="text-center py-12">
            <p className="text-slate-500">No todos found. Create one to get started.</p>
          </div>
        )}
      </div>

      {/* Create Modal */}
      <Modal
        isOpen={isCreateModalOpen}
        onClose={() => setIsCreateModalOpen(false)}
        title="Create New Todo"
        size="md"
      >
        <form onSubmit={handleCreate} className="space-y-4">
          <div>
            <label className="block text-sm font-medium text-slate-700 mb-1">User ID</label>
            <Input
              type="number"
              value={newTodo.userId}
              onChange={(e) => setNewTodo({ ...newTodo, userId: parseInt(e.target.value) })}
              required
            />
          </div>
          <div>
            <label className="block text-sm font-medium text-slate-700 mb-1">Title</label>
            <Input
              value={newTodo.title}
              onChange={(e) => setNewTodo({ ...newTodo, title: e.target.value })}
              placeholder="What needs to be done?"
              required
            />
          </div>
          <div className="flex gap-2 justify-end pt-4">
            <Button type="button" variant="outline" onClick={() => setIsCreateModalOpen(false)}>
              Cancel
            </Button>
            <Button type="submit" variant="primary">
              Create
            </Button>
          </div>
        </form>
      </Modal>
    </DashboardLayout>
  );
}
