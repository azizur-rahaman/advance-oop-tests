'use client';

import { useState, useEffect } from 'react';
import { Album } from '@/core/types';
import { albumRepository } from '../data/album.repository';

export const useAlbums = (userId?: number) => {
  const [albums, setAlbums] = useState<Album[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const fetchAlbums = async () => {
    setLoading(true);
    setError(null);
    try {
      const data = userId 
        ? await albumRepository.getByUserId(userId)
        : await albumRepository.getAll();
      setAlbums(data);
    } catch (err) {
      setError('Failed to fetch albums');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const createAlbum = async (album: Omit<Album, 'id'>) => {
    setLoading(true);
    setError(null);
    try {
      const newAlbum = await albumRepository.create(album);
      setAlbums([...albums, newAlbum]);
      return newAlbum;
    } catch (err) {
      setError('Failed to create album');
      console.error(err);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  const deleteAlbum = async (id: number) => {
    setLoading(true);
    setError(null);
    try {
      await albumRepository.delete(id);
      setAlbums(albums.filter(a => a.id !== id));
    } catch (err) {
      setError('Failed to delete album');
      console.error(err);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchAlbums();
  }, [userId]);

  return { albums, loading, error, fetchAlbums, createAlbum, deleteAlbum };
};
