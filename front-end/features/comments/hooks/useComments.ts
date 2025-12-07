'use client';

import { useState, useEffect } from 'react';
import { Comment } from '@/core/types';
import { commentRepository } from '../data/comment.repository';

export const useComments = (postId?: number) => {
  const [comments, setComments] = useState<Comment[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const fetchComments = async () => {
    setLoading(true);
    setError(null);
    try {
      const data = postId 
        ? await commentRepository.getByPostId(postId)
        : await commentRepository.getAll();
      setComments(data);
    } catch (err) {
      setError('Failed to fetch comments');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const createComment = async (comment: Omit<Comment, 'id'>) => {
    setLoading(true);
    setError(null);
    try {
      const newComment = await commentRepository.create(comment);
      setComments([...comments, newComment]);
      return newComment;
    } catch (err) {
      setError('Failed to create comment');
      console.error(err);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  const deleteComment = async (id: number) => {
    setLoading(true);
    setError(null);
    try {
      await commentRepository.delete(id);
      setComments(comments.filter(c => c.id !== id));
    } catch (err) {
      setError('Failed to delete comment');
      console.error(err);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchComments();
  }, [postId]);

  return { comments, loading, error, fetchComments, createComment, deleteComment };
};
