import { apiClient } from '@/core/infrastructure/api-client';
import { API_ENDPOINTS } from '@/core/config/api.config';
import { Album } from '@/core/types';

export class AlbumRepository {
  async getAll(): Promise<Album[]> {
    return apiClient.get<Album[]>(API_ENDPOINTS.ALBUMS);
  }

  async getById(id: number): Promise<Album> {
    return apiClient.get<Album>(`${API_ENDPOINTS.ALBUMS}/${id}`);
  }

  async getByUserId(userId: number): Promise<Album[]> {
    return apiClient.get<Album[]>(API_ENDPOINTS.ALBUMS, { userId });
  }

  async create(album: Omit<Album, 'id'>): Promise<Album> {
    return apiClient.post<Album>(API_ENDPOINTS.ALBUMS, album);
  }

  async update(id: number, album: Partial<Album>): Promise<Album> {
    return apiClient.put<Album>(`${API_ENDPOINTS.ALBUMS}/${id}`, album);
  }

  async partialUpdate(id: number, album: Partial<Album>): Promise<Album> {
    return apiClient.patch<Album>(`${API_ENDPOINTS.ALBUMS}/${id}`, album);
  }

  async delete(id: number): Promise<void> {
    return apiClient.delete<void>(`${API_ENDPOINTS.ALBUMS}/${id}`);
  }
}

export const albumRepository = new AlbumRepository();
