'use client';

import { useState, useEffect } from 'react';
import { Todo } from '@/core/types';
import { todoRepository } from '../data/todo.repository';

export const useTodos = (userId?: number) => {
  const [todos, setTodos] = useState<Todo[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const fetchTodos = async () => {
    setLoading(true);
    setError(null);
    try {
      const data = userId 
        ? await todoRepository.getByUserId(userId)
        : await todoRepository.getAll();
      setTodos(data);
    } catch (err) {
      setError('Failed to fetch todos');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const createTodo = async (todo: Omit<Todo, 'id'>) => {
    setLoading(true);
    setError(null);
    try {
      const newTodo = await todoRepository.create(todo);
      setTodos([...todos, newTodo]);
      return newTodo;
    } catch (err) {
      setError('Failed to create todo');
      console.error(err);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  const toggleTodo = async (id: number, completed: boolean) => {
    setLoading(true);
    setError(null);
    try {
      const updated = await todoRepository.partialUpdate(id, { completed });
      setTodos(todos.map(t => t.id === id ? updated : t));
      return updated;
    } catch (err) {
      setError('Failed to update todo');
      console.error(err);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  const deleteTodo = async (id: number) => {
    setLoading(true);
    setError(null);
    try {
      await todoRepository.delete(id);
      setTodos(todos.filter(t => t.id !== id));
    } catch (err) {
      setError('Failed to delete todo');
      console.error(err);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchTodos();
  }, [userId]);

  return { todos, loading, error, fetchTodos, createTodo, toggleTodo, deleteTodo };
};
