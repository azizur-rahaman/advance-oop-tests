import { apiClient } from '@/core/infrastructure/api-client';
import { API_ENDPOINTS } from '@/core/config/api.config';
import { Post } from '@/core/types';

export class PostRepository {
  async getAll(): Promise<Post[]> {
    return apiClient.get<Post[]>(API_ENDPOINTS.POSTS);
  }

  async getById(id: number): Promise<Post> {
    return apiClient.get<Post>(`${API_ENDPOINTS.POSTS}/${id}`);
  }

  async getByUserId(userId: number): Promise<Post[]> {
    return apiClient.get<Post[]>(API_ENDPOINTS.POSTS, { userId });
  }

  async create(post: Omit<Post, 'id'>): Promise<Post> {
    return apiClient.post<Post>(API_ENDPOINTS.POSTS, post);
  }

  async update(id: number, post: Partial<Post>): Promise<Post> {
    return apiClient.put<Post>(`${API_ENDPOINTS.POSTS}/${id}`, post);
  }

  async partialUpdate(id: number, post: Partial<Post>): Promise<Post> {
    return apiClient.patch<Post>(`${API_ENDPOINTS.POSTS}/${id}`, post);
  }

  async delete(id: number): Promise<void> {
    return apiClient.delete<void>(`${API_ENDPOINTS.POSTS}/${id}`);
  }
}

export const postRepository = new PostRepository();
