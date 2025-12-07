'use client';

import { useState, useEffect } from 'react';
import { Photo } from '@/core/types';
import { photoRepository } from '../data/photo.repository';

export const usePhotos = (albumId?: number) => {
  const [photos, setPhotos] = useState<Photo[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const fetchPhotos = async () => {
    setLoading(true);
    setError(null);
    try {
      const data = albumId 
        ? await photoRepository.getByAlbumId(albumId)
        : await photoRepository.getAll();
      setPhotos(data);
    } catch (err) {
      setError('Failed to fetch photos');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const createPhoto = async (photo: Omit<Photo, 'id'>) => {
    setLoading(true);
    setError(null);
    try {
      const newPhoto = await photoRepository.create(photo);
      setPhotos([...photos, newPhoto]);
      return newPhoto;
    } catch (err) {
      setError('Failed to create photo');
      console.error(err);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  const deletePhoto = async (id: number) => {
    setLoading(true);
    setError(null);
    try {
      await photoRepository.delete(id);
      setPhotos(photos.filter(p => p.id !== id));
    } catch (err) {
      setError('Failed to delete photo');
      console.error(err);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchPhotos();
  }, [albumId]);

  return { photos, loading, error, fetchPhotos, createPhoto, deletePhoto };
};
