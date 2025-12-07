'use client';

import { useState, useEffect } from 'react';
import { Post } from '@/core/types';
import { postRepository } from '../data/post.repository';

export const usePosts = (userId?: number) => {
  const [posts, setPosts] = useState<Post[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const fetchPosts = async () => {
    setLoading(true);
    setError(null);
    try {
      const data = userId 
        ? await postRepository.getByUserId(userId)
        : await postRepository.getAll();
      setPosts(data);
    } catch (err) {
      setError('Failed to fetch posts');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const createPost = async (post: Omit<Post, 'id'>) => {
    setLoading(true);
    setError(null);
    try {
      const newPost = await postRepository.create(post);
      setPosts([...posts, newPost]);
      return newPost;
    } catch (err) {
      setError('Failed to create post');
      console.error(err);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  const updatePost = async (id: number, post: Partial<Post>) => {
    setLoading(true);
    setError(null);
    try {
      const updated = await postRepository.partialUpdate(id, post);
      setPosts(posts.map(p => p.id === id ? updated : p));
      return updated;
    } catch (err) {
      setError('Failed to update post');
      console.error(err);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  const deletePost = async (id: number) => {
    setLoading(true);
    setError(null);
    try {
      await postRepository.delete(id);
      setPosts(posts.filter(p => p.id !== id));
    } catch (err) {
      setError('Failed to delete post');
      console.error(err);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchPosts();
  }, [userId]);

  return { posts, loading, error, fetchPosts, createPost, updatePost, deletePost };
};

export const usePost = (id: number) => {
  const [post, setPost] = useState<Post | null>(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const fetchPost = async () => {
    setLoading(true);
    setError(null);
    try {
      const data = await postRepository.getById(id);
      setPost(data);
    } catch (err) {
      setError('Failed to fetch post');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (id) {
      fetchPost();
    }
  }, [id]);

  return { post, loading, error, refetch: fetchPost };
};
