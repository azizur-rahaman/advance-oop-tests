import { apiClient } from '@/core/infrastructure/api-client';
import { API_ENDPOINTS } from '@/core/config/api.config';
import { Comment } from '@/core/types';

export class CommentRepository {
  async getAll(): Promise<Comment[]> {
    return apiClient.get<Comment[]>(API_ENDPOINTS.COMMENTS);
  }

  async getById(id: number): Promise<Comment> {
    return apiClient.get<Comment>(`${API_ENDPOINTS.COMMENTS}/${id}`);
  }

  async getByPostId(postId: number): Promise<Comment[]> {
    return apiClient.get<Comment[]>(API_ENDPOINTS.COMMENTS, { postId });
  }

  async create(comment: Omit<Comment, 'id'>): Promise<Comment> {
    return apiClient.post<Comment>(API_ENDPOINTS.COMMENTS, comment);
  }

  async update(id: number, comment: Partial<Comment>): Promise<Comment> {
    return apiClient.put<Comment>(`${API_ENDPOINTS.COMMENTS}/${id}`, comment);
  }

  async partialUpdate(id: number, comment: Partial<Comment>): Promise<Comment> {
    return apiClient.patch<Comment>(`${API_ENDPOINTS.COMMENTS}/${id}`, comment);
  }

  async delete(id: number): Promise<void> {
    return apiClient.delete<void>(`${API_ENDPOINTS.COMMENTS}/${id}`);
  }
}

export const commentRepository = new CommentRepository();
