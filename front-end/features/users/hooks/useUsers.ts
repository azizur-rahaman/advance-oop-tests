'use client';

import { useState, useEffect } from 'react';
import { User } from '@/core/types';
import { userRepository } from '../data/user.repository';

export const useUsers = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const fetchUsers = async () => {
    setLoading(true);
    setError(null);
    try {
      const data = await userRepository.getAll();
      setUsers(data);
    } catch (err) {
      setError('Failed to fetch users');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const createUser = async (user: Omit<User, 'id'>) => {
    setLoading(true);
    setError(null);
    try {
      const newUser = await userRepository.create(user);
      setUsers([...users, newUser]);
      return newUser;
    } catch (err) {
      setError('Failed to create user');
      console.error(err);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  const updateUser = async (id: number, user: Partial<User>) => {
    setLoading(true);
    setError(null);
    try {
      const updated = await userRepository.partialUpdate(id, user);
      setUsers(users.map(u => u.id === id ? updated : u));
      return updated;
    } catch (err) {
      setError('Failed to update user');
      console.error(err);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  const deleteUser = async (id: number) => {
    setLoading(true);
    setError(null);
    try {
      await userRepository.delete(id);
      setUsers(users.filter(u => u.id !== id));
    } catch (err) {
      setError('Failed to delete user');
      console.error(err);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  return { users, loading, error, fetchUsers, createUser, updateUser, deleteUser };
};

export const useUser = (id: number) => {
  const [user, setUser] = useState<User | null>(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const fetchUser = async () => {
    setLoading(true);
    setError(null);
    try {
      const data = await userRepository.getById(id);
      setUser(data);
    } catch (err) {
      setError('Failed to fetch user');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (id) {
      fetchUser();
    }
  }, [id]);

  return { user, loading, error, refetch: fetchUser };
};
