import { apiClient } from '@/core/infrastructure/api-client';
import { API_ENDPOINTS } from '@/core/config/api.config';
import { Photo } from '@/core/types';

export class PhotoRepository {
  async getAll(): Promise<Photo[]> {
    return apiClient.get<Photo[]>(API_ENDPOINTS.PHOTOS);
  }

  async getById(id: number): Promise<Photo> {
    return apiClient.get<Photo>(`${API_ENDPOINTS.PHOTOS}/${id}`);
  }

  async getByAlbumId(albumId: number): Promise<Photo[]> {
    return apiClient.get<Photo[]>(API_ENDPOINTS.PHOTOS, { albumId });
  }

  async create(photo: Omit<Photo, 'id'>): Promise<Photo> {
    return apiClient.post<Photo>(API_ENDPOINTS.PHOTOS, photo);
  }

  async update(id: number, photo: Partial<Photo>): Promise<Photo> {
    return apiClient.put<Photo>(`${API_ENDPOINTS.PHOTOS}/${id}`, photo);
  }

  async partialUpdate(id: number, photo: Partial<Photo>): Promise<Photo> {
    return apiClient.patch<Photo>(`${API_ENDPOINTS.PHOTOS}/${id}`, photo);
  }

  async delete(id: number): Promise<void> {
    return apiClient.delete<void>(`${API_ENDPOINTS.PHOTOS}/${id}`);
  }
}

export const photoRepository = new PhotoRepository();
