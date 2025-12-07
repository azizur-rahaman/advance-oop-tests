import { apiClient } from '@/core/infrastructure/api-client';
import { API_ENDPOINTS } from '@/core/config/api.config';
import { User } from '@/core/types';

export class UserRepository {
  async getAll(): Promise<User[]> {
    return apiClient.get<User[]>(API_ENDPOINTS.USERS);
  }

  async getById(id: number): Promise<User> {
    return apiClient.get<User>(`${API_ENDPOINTS.USERS}/${id}`);
  }

  async create(user: Omit<User, 'id'>): Promise<User> {
    return apiClient.post<User>(API_ENDPOINTS.USERS, user);
  }

  async update(id: number, user: Partial<User>): Promise<User> {
    return apiClient.put<User>(`${API_ENDPOINTS.USERS}/${id}`, user);
  }

  async partialUpdate(id: number, user: Partial<User>): Promise<User> {
    return apiClient.patch<User>(`${API_ENDPOINTS.USERS}/${id}`, user);
  }

  async delete(id: number): Promise<void> {
    return apiClient.delete<void>(`${API_ENDPOINTS.USERS}/${id}`);
  }
}

export const userRepository = new UserRepository();
