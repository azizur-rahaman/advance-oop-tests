import { apiClient } from '@/core/infrastructure/api-client';
import { API_ENDPOINTS } from '@/core/config/api.config';
import { Todo } from '@/core/types';

export class TodoRepository {
  async getAll(): Promise<Todo[]> {
    return apiClient.get<Todo[]>(API_ENDPOINTS.TODOS);
  }

  async getById(id: number): Promise<Todo> {
    return apiClient.get<Todo>(`${API_ENDPOINTS.TODOS}/${id}`);
  }

  async getByUserId(userId: number): Promise<Todo[]> {
    return apiClient.get<Todo[]>(API_ENDPOINTS.TODOS, { userId });
  }

  async create(todo: Omit<Todo, 'id'>): Promise<Todo> {
    return apiClient.post<Todo>(API_ENDPOINTS.TODOS, todo);
  }

  async update(id: number, todo: Partial<Todo>): Promise<Todo> {
    return apiClient.put<Todo>(`${API_ENDPOINTS.TODOS}/${id}`, todo);
  }

  async partialUpdate(id: number, todo: Partial<Todo>): Promise<Todo> {
    return apiClient.patch<Todo>(`${API_ENDPOINTS.TODOS}/${id}`, todo);
  }

  async delete(id: number): Promise<void> {
    return apiClient.delete<void>(`${API_ENDPOINTS.TODOS}/${id}`);
  }
}

export const todoRepository = new TodoRepository();
